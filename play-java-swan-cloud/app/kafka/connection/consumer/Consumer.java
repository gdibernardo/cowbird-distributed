package kafka.connection.consumer;

import cowbird.flink.common.messages.result.ResultMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.*;

public class Consumer {

    static private Consumer consumer = new Consumer();

    private int DEFAULT_NUMBER_OF_CONSUMERS = 1;

    static private long DEFAULT_TERMINATION_TIMEOUT = 5000; //   ms

    private ArrayList<ConsumerLoop> consumers;

    private ExecutorService executorService;

    static public Consumer sharedConsumer() {
        return consumer;
    }

    private HashMap<String, ConcurrentLinkedQueue<ResultMessage>> resultHashMap;

    public Consumer() {
        consumers = new ArrayList<>();
        executorService = Executors.newFixedThreadPool(DEFAULT_NUMBER_OF_CONSUMERS);
        resultHashMap = new HashMap<>();

        for(int index = 0; index < DEFAULT_NUMBER_OF_CONSUMERS; index++) {
            ConsumerLoop loop = new ConsumerLoop(String.valueOf(index), this);
            consumers.add(loop);
            executorService.submit(loop);
        }

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                for(ConsumerLoop loop : consumers) {
                    loop.destroy();
                }
                executorService.shutdown();
                try {
                    executorService.awaitTermination(DEFAULT_TERMINATION_TIMEOUT, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                   e.printStackTrace();
                }
            }
        });
    }


    void appendResult(ResultMessage resultMessage) {
        String id = resultMessage.getExpressionId();
        if(resultHashMap.containsKey(id)) {
            resultHashMap.get(id).add(resultMessage);
        }
    }


    public void addEntry(String expressionId) {
        if (resultHashMap.containsKey(expressionId))
            return;
        ConcurrentLinkedQueue<ResultMessage> queue = new ConcurrentLinkedQueue<>();
        resultHashMap.put(expressionId, queue);
    }


    public void removeEntry(String expressionId) {
        if(resultHashMap.containsKey(expressionId))
            resultHashMap.remove(expressionId);
    }


    public ResultMessage get(String expressionId) {
        return resultHashMap.get(expressionId).poll();
    }
}
