package distributed;

import interdroid.swancore.swansong.Expression;
import interdroid.swancore.swansong.TriStateExpression;
import interdroid.swancore.swansong.ValueExpression;

import java.io.Serializable;

/**
 * Created by gdibernardo on 05/07/2017.
 */


public class Work implements Serializable {

    private Expression expression;

    private ExpressionType type;

    private String identifier;


    /*  TO-DO: evaluate load expression.    -   ??  */
    public Work(Expression expression, ExpressionType type) {
        this.expression = expression;
        this.type = type;
    }


    public void setExpression(Expression expression) {
        this.expression = expression;
    }


    public Expression getExpression() {
        return expression;
    }


    public ExpressionType getType() {
        return type;
    }


    public static Work tristateExpressionWork(TriStateExpression expression) {
        return new Work(expression, ExpressionType.TRI_STATE_EXPRESSION);
    }


    public static Work valueExpressionWork(ValueExpression expression) {
        return new Work(expression, ExpressionType.VALUE_EXPRESSION);
    }


    public void setIdentifier(String id) {
        this.identifier = id;
    }


    public String getIdentifier() {
        return identifier;
    }
}
