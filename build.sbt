name := "scalaSimuation"



version := "0.1"



scalaVersion := "2.12.4"

libraryDependencies ++= 
Seq(
"org.apache.spark" %% "spark-core" % "1.5.2" % "provided",

  "com.holdenkarau" %% "spark-testing-base" % "1.5.2_0.6.0" % "test"
  
)


