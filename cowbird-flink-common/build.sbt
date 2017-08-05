name := "cowbird-flink-common"

organization := "nl.cowbird"

version := "1.0"

scalaVersion := "2.11.11"


libraryDependencies ++= Seq(
  "org.apache.kafka" % "kafka_2.11" % "0.10.2.0",
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.9.0.pr4",
  "org.json"%"org.json"%"chargebee-1.0"
)

//artifact in (Compile, assembly) := {
//  val art = (artifact in (Compile, assembly)).value
//  art.copy(`classifier` = Some("assembly"))
//}
//
//addArtifact(artifact in Compile, assembly)