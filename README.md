# cowbird-distributed
# Requirements
* Java 1.7+
* sbt
* Kafka 0.11 (+ Zookeeper)
* Flink 1.3.2

# Installation
## Compile the cowbird-flink-common library
From the /cowbird-flink-common directory run:

`sbt assembly`

`sbt publishLocal`

# Setup the Fog layer
The Akka configuration parameters should be set in order to execute the Fog layer. It can be done through the application.conf file in the /play-java-swan-cloud/conf/ directory.

The seed-nodes list should contain the address (and port) of the Cowbird manager.

## Run the Cowbird Manager
`sbt "runMain distributed.manager.CowbirdManagerApp 2551"`

## Run the Cowbird Mode
` sbt "runMain distributed.node.CowbirdNodeApp $port"`

## Run the Play frontend 
` sbt run`

## Register Expression from the Play frontend
```Java
String myExpression = "self@test:value{MEAN,1000}";
try {
    ValueExpression expression = (ValueExpression) ExpressionFactory.parse(myExpression);
    identifier = FrontendManager.sharedInstance().registerValueExpression(expression, new ValueExpressionListener() {
     @Override
     public void onNewValues(String id, TimestampedValue[] newValues) {
            if(newValues!=null && newValues.length>0) {
                System.out.println("Test Sensor (Value):" + newValues[newValues.length-1].toString());
            }
        }
    });
} catch (ExpressionParseException e) {
        e.printStackTrace();
}
```


## Unregister Expression from the Play frontend
```Java
FrontendManager.sharedInstance().unregisterExpression(identifier);
```

# Run the Streams Flink Job
The Flink application can be compiled using sbt:

` sbt assembly`

On a local deployment start the Flink session using the **start-local.sh** script that can be found in the Flink directory.
Run the Flink job using the flink script:

`./flink-1.3.2/bin/flink run ../path/to/distributed-cowbird/cowbird-flink/target/scala-2.11/cowbird-flink-assembly-1.0.jar`

## Streaming-oriented mode
The streaming-oriented evalaution mode can be enabled using the --light-mode parameter when launching the Flink application.

`./flink-1.3.2/bin/flink run ../path/to/distributed-cowbird/cowbird-flink/target/scala-2.11/cowbird-flink-assembly-1.0.jar --light-mode ON`

# Set the Kafka cluster
In the /scripts directory some configuration scripts can be found.
 
The **deploy_cowbird_kafka_topics.sh** can be used to install the Kafka topics required to execute the application.




