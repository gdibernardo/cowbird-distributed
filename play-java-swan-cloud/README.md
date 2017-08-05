This is the SWAN framework built using play framework.
For more information about this repository, please contact: Roshan Bharath Das (http://few.vu.nl/~rbs238/)

# Usage

## Run the Cowbird manager
`sbt "runMain distributed.manager.CowbirdManagerApp 2551"`

## Run the Cowbird node
` sbt "runMain distributed.node.CowbirdNodeApp $port"`

## Register Expression from the Play frontend
```Java
String myExpression = "self@test:value{MEAN,1000}";
try {
    ValueExpression expression = (ValueExpression) ExpressionFactory.parse(myExpression);
    identifier = FrontendManager.sharedInstance().registerValueExpression(expression, new ValueExpressionListener() {
     @Override
     public void onNewValues(String id, TimestampedValue[] newValues) {
            if(newValues!=null && newValues.length>0) {
                System.out.println("Test Sensor (Value)1:" + newValues[newValues.length-1].toString());
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
