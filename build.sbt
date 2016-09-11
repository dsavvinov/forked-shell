name := "SoftwareDesign"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.4",
  "org.slf4j" % "slf4j-api" % "1.7.5",
  "org.slf4j" % "slf4j-simple" % "1.7.5",
  "junit" % "junit" % "4.11" % "test",
  "com.github.scala-incubator.io" % "scala-io-core_2.9.1" % "0.4.0",
  "org.scalatest" % "scalatest_2.11" % "3.0.0" % "test"
)