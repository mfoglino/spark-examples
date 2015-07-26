package joins

import org.apache.spark.{SparkConf, SparkContext}

/**
 * Created by marcos on 29/06/15.
 */
object JoinUsingDataFramesMain extends App {


  val sparkConf = new SparkConf().setAppName(s"Testing Dataframes with csv loading")
    .setMaster("local")
    .set("spark.hadoop.validateOutputSpecs", "false")
  val sc = new SparkContext(sparkConf)
  val sqlContext = new org.apache.spark.sql.SQLContext(sc)

  val csvFile = "src/main/resources/part-0_withheader.gz"

  val yearlySantander = sqlContext.read.format("com.databricks.spark.csv")
    .option("header", "true")
    .option("delimiter", "|")
    .load(csvFile)

  yearlySantander.printSchema()

  Common.time(joinUsingDataFrames)


  def joinUsingDataFrames = {
    val join = yearlySantander.join(yearlySantander, yearlySantander("CUSTOMER_ID") === yearlySantander("CUSTOMER_ID"))
    join.rdd.saveAsTextFile("/tmp/joinDataFrames")
  }
}
