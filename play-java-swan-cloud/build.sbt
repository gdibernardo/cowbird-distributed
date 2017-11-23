name := """play-java-swan-cloud"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)//.dependsOn(kafkaCommon)
//lazy val kafkaCommon = RootProject(file("../cowbird-kafka-common"))


scalaVersion := "2.11.11"

val akkaVersion = "2.5.3"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  "org.postgresql" % "postgresql" % "9.4-1204-jdbc42",
  "org.json"%"org.json"%"chargebee-1.0",
  "javax.mail" % "mail" % "1.4.7",
  "com.twitter" % "hbc-core" % "2.2.0",
  "com.rabbitmq" % "amqp-client" % "4.1.0",//,

  //"com.typesafe.akka" %% "akka-remote" % "$akka.version$" // 2.5.3
 //  "com.typesafe.akka" %% "akka-remote" % "2.5.3"
  //"com.typesafe.akka" % "akka-actor_2.11" % "2.4.13"
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-remote" % akkaVersion,
  "com.typesafe.akka" %% "akka-cluster" % akkaVersion,
  "com.typesafe.akka" %% "akka-cluster-metrics" % akkaVersion,
  "com.typesafe.akka" %% "akka-cluster-tools" % akkaVersion,
  "com.typesafe.akka" %% "akka-persistence" % akkaVersion,

  // "org.apache.kafka" % "kafka_2.11" % "0.10.2.0"

   "nl.cowbird" %% "cowbird-flink-common" % "1.0",

   "com.mashape.unirest" % "unirest-java" % "1.4.9",
   "org.apache.httpcomponents" % "httpclient" % "4.3.6",
   "org.apache.httpcomponents" % "httpasyncclient" % "4.0.2",
   // "org.apache.httpcomponents" % "httpmime" % "4.5.3"
   "org.apache.httpcomponents" % "httpmime" % "4.3.6"
)

// unmanagedJars in Compile += file("/Users/gdibernardo/Documents/cowbird/cowbird-flink-common/target/scala-2.11/cowbird-flink-common_2.11-1.0.jar")


// root.dependsOn("../cowbird-kafka-common")
// lazy val root = project.dependsOn()


