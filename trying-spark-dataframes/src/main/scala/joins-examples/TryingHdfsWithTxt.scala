package joins

import org.apache.spark.{SparkContext, SparkConf}

/**
 * Created by marcos on 17/09/15.
 */
object TryingHdfsWithTxt extends App{

  val sparkConf = new SparkConf().setAppName(s"Testing Dataframes with csv loading")
    .setMaster("local")
    .set("spark.hadoop.validateOutputSpecs", "false")
  //.set("spark.driver.extraClassPath", "/home/marcos/stories/lzoTest/hadoop-lzo-0.4.19.jar")
  .set("spark.driver.extraLibraryPath","/home/marcos/workspace/hadoop-lzo/target/classes/native/Linux-amd64-64/lib")

    //.set("spark.executor.extraClassPath", "/home/marcos/stories/lzoTest/hadoop-lzo-0.4.19.jar")
    .set("spark.executor.extraLibraryPath","/home/marcos/workspace/hadoop-lzo/target/native/Linux-amd64-64/lib")

  val sc = new SparkContext(sparkConf)
  sc.hadoopConfiguration.set("io.compression.codecs","com.hadoop.compression.lzo.LzopCodec")



  println("prop:"+System.getenv("HADOOP_CONF_DIR"))



  val csvFile = "hdfs://localhost:9000/input/maestra_demograficos_20150627.txt"
  val csvFileZip = "hdfs://localhost:9000/input/maestra_demograficos_20150627.txt.gz"
  val lzoFile = "hdfs://localhost:9000/input/maestra_demograficos_20150627.txt.lzo"

  //val localLzoFile = "/home/marcos/stories/lzoTest/maestra_demograficos_20150627.txt.lzo"
  //val yearlyFileRaw = sc.textFile(localLzoFile)

  val yearlyFileRawText = sc.newAPIHadoopFile(lzoFile, classOf[com.hadoop.mapreduce.LzoTextInputFormat],classOf[org.apache.hadoop.io.LongWritable],classOf[org.apache.hadoop.io.Text])

  val yearlyFileRaw = yearlyFileRawText.map(_._2.toString())


  yearlyFileRaw.take(10).foreach(println)

  //println("count"+yearlyFileRaw.count)
  val sizes = yearlyFileRaw.map(_.split(s"\\|", -1).toList).map(_.size)

  //sizes.take(10).foreach(println)
  val tienen71 = sizes.map(_.equals(71)).reduce(_ && _)



  println("partitions:"+ yearlyFileRaw.partitions.size)
  println("Tienen71:"+tienen71)
}
