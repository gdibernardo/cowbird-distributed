resolvers in ThisBuild ++= Seq("Apache Development Snapshot Repository" at "https://repository.apache.org/content/repositories/snapshots/",
  Resolver.mavenLocal)

name := "cowbird-flink"

version := "1.0"

organization := "nl.cowbird"

scalaVersion in ThisBuild := "2.11.11"

val flinkVersion = "1.3.2"

val flinkDependencies = Seq(
  "org.apache.flink" %% "flink-scala" % flinkVersion % "provided",
  "org.apache.flink" %% "flink-streaming-scala" % flinkVersion % "provided",
 // "org.apache.flink" % "flink-clients_2.11" % flinkVersion,

  "org.apache.flink" %% "flink-statebackend-rocksdb" % flinkVersion,

  "org.apache.flink" % "flink-connector-kafka-0.10_2.11" % flinkVersion

  //"org.apache.flink" % "flink-statebackend-rocksdb_2.11" % flinkVersion % "test"
)

val kafkaDependencies = Seq("org.apache.kafka" % "kafka_2.11" % "0.10.2.0")

val generalDependencies = Seq("com.fasterxml.jackson.core" % "jackson-databind" % "2.9.0.pr4")

val dependencies = flinkDependencies ++ kafkaDependencies ++ generalDependencies

unmanagedJars in Compile += file("/Users/gdibernardo/Documents/cowbird/cowbird-flink-common/target/scala-2.11/cowbird-flink-common-assembly-1.0.jar")

lazy val root = (project in file(".")).
  settings(
    libraryDependencies ++= dependencies
  )

mainClass in assembly := Some("job.Job")

// make run command include the provided dependencies
run in Compile <<= Defaults.runTask(fullClasspath in Compile, mainClass in (Compile, run), runner in (Compile, run))

// exclude Scala library from assembly
assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)

/*  MERGE STRATEGY. */
assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}