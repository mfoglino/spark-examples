name := "trying-spark-dataframes"

version := "1.0"

scalaVersion := "2.11.7"



// Read here for optional jars and dependencies
libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "1.4.0",
  "org.apache.spark" %% "spark-sql" % "1.4.0",
  "com.databricks" % "spark-csv_2.11" % "1.0.3"
)

