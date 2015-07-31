package joins

import org.apache.spark.{SparkContext, SparkConf}


/**
 * Created by marcos on 30/07/15.
 */
object TryingPipeRddCommand extends App{

  val sparkConf = new SparkConf()
    .setAppName(s"Testing Dataframes with csv loading").setMaster("local")
    .set("spark.hadoop.validateOutputSpecs", "false")
  val sc = new SparkContext(sparkConf)

  val csvFile = "src/main/resources/part-0_withheader.gz"
  val rdd = sc.textFile(csvFile)

  val resu = rdd.pipe("head -n 1").collect


  resu.take(10).foreach(println)

}
