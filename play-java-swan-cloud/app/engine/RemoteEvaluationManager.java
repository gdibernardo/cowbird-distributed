package engine;

import cowbird.flink.common.messages.control.ComplexCompareControlMessage;
import cowbird.flink.common.messages.control.ConstantCompareControlMessage;
import cowbird.flink.common.messages.control.ControlMessage;
import cowbird.flink.common.messages.result.ResultMessage;

import interdroid.swancore.swansong.*;

import kafka.connection.consumer.Consumer;
import kafka.connection.producer.Producer;

import sensors.base.SensorFactory;
import sensors.base.SensorInterface;

import java.util.HashSet;
import java.util.Set;


public class RemoteEvaluationManager extends EvaluationManager {

    /*  Minimum interval for offloading expression evaluation to Flink. */
    /*  Interval is expressed in ms.    */
    private static long MINIMUM_INTERVAL_FOR_REMOTE_EVALUATION = 3600000;

    private static RemoteEvaluationManager instance = new RemoteEvaluationManager();

    public static RemoteEvaluationManager sharedInstance() {
        return instance;
    }

    private final Set<String> remotelyEvaluatedExpressions = new HashSet<>();


    private static ComplexCompareControlMessage complexCompareControlMessage(String id, ComparisonExpression expression) {
        Expression left = expression.getLeft();
        Expression right = expression.getRight();

        if(left instanceof SensorValueExpression && right instanceof SensorValueExpression) {

            SensorValueExpression leftSVE = (SensorValueExpression) left;
            SensorValueExpression rightSVE = (SensorValueExpression) right;

            final String leftId = id + Expression.LEFT_SUFFIX;
            final String rightId = id + Expression.RIGHT_SUFFIX;

            return new ComplexCompareControlMessage(id,
                    leftId,
                    rightId,
                    leftSVE.getHistoryReductionMode().convert(),
                    leftSVE.getHistoryLength(),
                    rightSVE.getHistoryReductionMode().convert(),
                    rightSVE.getHistoryLength(),
                    expression.getComparator().convert());
        }

        return null;
    }

    private static ConstantCompareControlMessage constantCompareControlMessage(String id, ComparisonExpression expression) {
        Expression left = expression.getLeft();
        Expression right = expression.getRight();

        if((left instanceof SensorValueExpression && right instanceof ConstantValueExpression) || (right instanceof  ConstantValueExpression && right instanceof  SensorValueExpression)) {
            ConstantCompareControlMessage controlMessage = new ConstantCompareControlMessage(id);

            SensorValueExpression sve;
            ConstantValueExpression cve;

            if(right instanceof SensorValueExpression) {
                controlMessage.isExpressionLeft = false;
                sve = (SensorValueExpression) right;
                cve = (ConstantValueExpression) left;
            } else {
                controlMessage.isExpressionLeft = true;
                sve = (SensorValueExpression) left;
                cve = (ConstantValueExpression) right;
            }

            controlMessage.setComparator(expression.getComparator().convert());
            controlMessage.setHistoryLength(sve.getHistoryLength());
            // controlMessage.setConstantValue((double) cve.getResult().getValues()[0].getValue());
            controlMessage.setConstantValue(cve.getResult().getValues()[0].getValue());
            controlMessage.setHistoryReductionMode(sve.getHistoryReductionMode().convert());

            return controlMessage;
        }

        return null;
    }


    private static ControlMessage controlMessage(String id, SensorValueExpression expression) {

        ControlMessage controlMessage = new ControlMessage(id);

        controlMessage.setHistoryLength(expression.getHistoryLength());
        controlMessage.setHistoryReductionMode(expression.getHistoryReductionMode().convert());

        return controlMessage;
    }


    /*  Evaluation Logic methods.   */
    private boolean expressionHasAnyOrAllReduction(SensorValueExpression expression) {
        return (expression.getHistoryReductionMode() == HistoryReductionMode.ANY || expression.getHistoryReductionMode() == HistoryReductionMode.ALL);
    }


    private boolean expressionHasRemoteTimingRequirements(SensorValueExpression expression) {
        SensorInterface sensor = SensorFactory.getSensor(expression.getEntity());

        // return sensor.isHighFrequency() || expression.getHistoryLength() >= MINIMUM_INTERVAL_FOR_REMOTE_EVALUATION;

        return sensor.isHighFrequency();
    }


    private boolean isSensorValueExpressionEligibleForRemoteComparison(SensorValueExpression expression) {
        return expressionHasRemoteTimingRequirements(expression) && expressionHasAnyOrAllReduction(expression);
    }


    private boolean isSensorValueExpressionEligibleForRemoteEvaluation(SensorValueExpression expression) {
        return expressionHasRemoteTimingRequirements(expression) && (expression.getHistoryReductionMode() != HistoryReductionMode.ANY && expression.getHistoryReductionMode() != HistoryReductionMode.ALL);
    }


    private boolean isComplexComparisonExpression(Expression left, Expression right) {
        if(left instanceof SensorValueExpression && right instanceof SensorValueExpression) {
            if(isSensorValueExpressionEligibleForRemoteComparison((SensorValueExpression) left) && expressionHasRemoteTimingRequirements((SensorValueExpression) right))
                return true;
            return isSensorValueExpressionEligibleForRemoteComparison((SensorValueExpression) right) && expressionHasRemoteTimingRequirements((SensorValueExpression) left);
        }

        return false;
    }


    private boolean isConstantComparisonExpression(Expression left, Expression right) {
        if(left instanceof SensorValueExpression) {
            return isSensorValueExpressionEligibleForRemoteComparison((SensorValueExpression) left) && right instanceof ConstantValueExpression;
        }

        return false;
    }


    private boolean isComparisonExpressionEligibleForRemoteEvaluation(ComparisonExpression expression) {

        Expression left = expression.getLeft();
        Expression right = expression.getRight();

        if(isConstantComparisonExpression(left, right) || isConstantComparisonExpression(right, left))
            return true;

        return isComplexComparisonExpression(left, right);
    }


    public boolean isExpressionEligibleForRemoteEvaluation(Expression expression) {
        if(expression instanceof ComparisonExpression)
            return isComparisonExpressionEligibleForRemoteEvaluation((ComparisonExpression) expression);

        if(expression instanceof SensorValueExpression)
            return isSensorValueExpressionEligibleForRemoteEvaluation((SensorValueExpression) expression);

        return false;
    }


    private void initializeRemoteSensorValueExpression(final String id, final SensorValueExpression expression) throws SensorConfigurationException, SensorSetupFailedException {

        ControlMessage controlMessage = controlMessage(id, expression);

        Producer.sharedProducer().send(controlMessage);

        bindToSensor(id, expression, false);
    }


    private void initializeRemoteComparisonExpression(String id, ComparisonExpression expression) throws SensorConfigurationException, SensorSetupFailedException {
        Expression left = expression.getLeft();
        Expression right = expression.getRight();

        if(left instanceof SensorValueExpression && right instanceof SensorValueExpression) {
            /*  Init complex comparison expression.    */
            ComplexCompareControlMessage controlMessage = complexCompareControlMessage(id, expression);

            System.out.println("COMPLEX");
            Producer.sharedProducer().send(controlMessage);

            bindToSensor(controlMessage.getLeftExpressionId(), (SensorValueExpression) left, false);
            bindToSensor(controlMessage.getRightExpressionId(), (SensorValueExpression) right, false);
        } else {

            ConstantCompareControlMessage controlMessage = constantCompareControlMessage(id, expression);

            Producer.sharedProducer().send(controlMessage);

            String bindingId = (controlMessage.isExpressionLeft) ? id + Expression.LEFT_SUFFIX : Expression.RIGHT_SUFFIX;

            SensorValueExpression sve = controlMessage.isExpressionLeft ? (SensorValueExpression) left : (SensorValueExpression) right;
            bindToSensor(bindingId, sve, false);
        }
    }


    /*  Get from Kafka the latest available result. */
    private Result getSVEFromKafka(String id, SensorValueExpression expression, long now) {

        ResultMessage resultMessage = Consumer.sharedConsumer().get(id);
        if(resultMessage == null) {
            /*  Copied from getFromSensor().    */
            Result result = new Result(new TimestampedValue[]{}, 0);
            // TODO make this a constant (configurable?)
            result.setDeferUntil(now + 1000);
            result.setDeferUntilGuaranteed(false);
            //Log.d(TAG, "Deferred until: " + (now + 1000));
            return result;
        }

        TimestampedValue [] reduced = new TimestampedValue[] {new TimestampedValue(resultMessage.getValue(), resultMessage.getTimestamp())};
        Result result = new Result(reduced, resultMessage.getTimestamp());

//        TimestampedValue [] reduced = new TimestampedValue[] {new TimestampedValue(resultMessage.getValue(), resultMessage.getLeftLatestTimestamp())};
//        Result result = new Result({})
        if(expression.getHistoryLength() == 0) {
            /*  Copied from getFromSensor().    */
            // we cannot defer based on values, new values will be retrieved
            // when they arrive
            result.setDeferUntil(Long.MAX_VALUE);
            result.setDeferUntilGuaranteed(false);
        } else {
            /*  Probably we should defer from now?!.    */
            result.setDeferUntil(resultMessage.getTimestamp() + expression.getHistoryLength());
            //result.setDeferUntil(now + expression.getHistoryLength());
            result.setDeferUntilGuaranteed(false);
        }

        return result;
    }


    /*  Initialize the evaluation on the streaming data pipeline.   */
    public void initializeRemotely(String id, Expression expression) throws SensorConfigurationException, SensorSetupFailedException {

        if(remotelyEvaluatedExpressions.contains(id)) {
            /*  Raise an exception. */
            System.out.println("Expression already registered remotely.");
            return;
        }

        remotelyEvaluatedExpressions.add(id);

        System.out.println("init remotely. ");
        if(expression instanceof ComparisonExpression)
            initializeRemoteComparisonExpression(id, (ComparisonExpression) expression);

        if(expression instanceof SensorValueExpression)
            initializeRemoteSensorValueExpression(id, (SensorValueExpression) expression);
    }


    /*  Do remote comparison.   */
    public Result doRemoteCompare(String id, ComparisonExpression expression, long now) {

        ResultMessage resultMessage = Consumer.sharedConsumer().get(id);

        System.out.println("REMOTE COMPARE");
        if(resultMessage == null) {
            Result result = new Result(now, TriState.UNDEFINED);
            result.setDeferUntil(Long.MAX_VALUE);
            result.setDeferUntilGuaranteed(false);
            return result;
        }

        TriState tristateResult = TriState.fromCode((Integer) resultMessage.getValue());

        System.out.println("Result " + tristateResult);

        DeferUntilResult leftDefer = remainsValidUntil(expression.getLeft(),
                System.currentTimeMillis(),
                resultMessage.getLeftOldestTimestamp(),
                expression.getComparator(),
                tristateResult,
                true);

        DeferUntilResult rightDefer = remainsValidUntil(expression.getRight(),
                resultMessage.getRightLatestTimestamp(),
                resultMessage.getRightOldestTimestamp(),
                expression.getComparator(),
                tristateResult,
                false);

        Result result = new Result(now, tristateResult);
        /*  Fixed a bug.    */
        result.setDeferUntil(Math.min(leftDefer.deferUntil, rightDefer.deferUntil));
        result.setDeferUntilGuaranteed(leftDefer.guaranteed && rightDefer.guaranteed);

        return result;
    }


    private void stopRemoteSensorValueExpression(String id, SensorValueExpression expression) {
        ControlMessage controlMessage = controlMessage(id, expression);

        Producer.sharedProducer().send(controlMessage);

        unbindFromSensor(id);
    }


    private void stopRemoteComparisonExpression(String id, ComparisonExpression expression) {
        Expression left = expression.getLeft();
        Expression right = expression.getRight();

        if(left instanceof SensorValueExpression && right instanceof SensorValueExpression) {
            /*  Init complex comparison expression.    */
            ComplexCompareControlMessage controlMessage = complexCompareControlMessage(id, expression);


            //  Producer.sharedProducer().send(controlMessage);
            unbindFromSensor(controlMessage.getLeftExpressionId());
            unbindFromSensor(controlMessage.getRightExpressionId());
        } else {

            ConstantCompareControlMessage controlMessage = constantCompareControlMessage(id, expression);

            Producer.sharedProducer().send(controlMessage);

            String bindingId = (controlMessage.isExpressionLeft) ? id + Expression.LEFT_SUFFIX : Expression.RIGHT_SUFFIX;
            unbindFromSensor(bindingId);
        }
    }


    public void stopRemotely(String id, Expression expression) {
        if(!remotelyEvaluatedExpressions.contains(id)) {
            /*  Exception should probably be raised.    */
            System.out.println("Expression not registered remotely. ");
            return;
        }

        if(expression instanceof ComparisonExpression)
            stopRemoteComparisonExpression(id, (ComparisonExpression) expression);

        if(expression instanceof SensorValueExpression)
            stopRemoteSensorValueExpression(id, (SensorValueExpression) expression);

        remotelyEvaluatedExpressions.remove(id);
    }


    public Result getFromRemote(String id, SensorValueExpression expression, long now) {
        return getSVEFromKafka(id, expression, now);
    }


    public boolean isEvaluatingExpression(String expressionId) {
        return remotelyEvaluatedExpressions.contains(expressionId);
    }


    public boolean isPollingFromSensor(String id) {
        return mSensors.containsKey(id);
    }
}