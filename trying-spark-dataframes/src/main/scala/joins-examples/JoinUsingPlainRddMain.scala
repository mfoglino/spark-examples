package joins

import org.apache.spark.{SparkConf, SparkContext}

/**
 * Created by marcos on 29/06/15.
 */
object JoinUsingPlainRddMain extends App {

  import Common.{RichRDD, RichString}

  val sparkConf = new SparkConf()
    .setAppName(s"Testing Dataframes with csv loading").setMaster("local")
    .set("spark.hadoop.validateOutputSpecs", "false")
  val sc = new SparkContext(sparkConf)
  val csvFile = "src/main/resources/part-0_withheader.gz"
  val yearlyFileRaw = sc.textFile(csvFile).dropHeader

  Common.time(joinUsingRdd())


  def joinUsingRdd() = {
    val yearlyRdd = yearlyFileRaw.map(_.splitFields('|'))
    val joinableRdd = yearlyRdd.map(row => (row.head, row.tail))
    val joined = joinableRdd.join(joinableRdd)
    joined.saveAsTextFile("/tmp/joinRdd")
  }
}
