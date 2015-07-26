package joins

import org.apache.spark.rdd.RDD

import scala.concurrent.duration.Duration
import scala.reflect.ClassTag

/**
 * Created by marcos on 30/06/15.
 */
object Common {

  def time[A](a: => A) = {
       val now = System.nanoTime
       val result = a
       val nanos = (System.nanoTime - now)
       println(s"${Duration.fromNanos(nanos).toSeconds} seconds")
       result
     }


  implicit class RichRDD[T: ClassTag](dataset: RDD[T]) {
    def dropHeader: RDD[T] = dataset.mapPartitionsWithIndex {
      (idx, lines) => {
        if (idx == 0) lines.drop(1)
        lines
      }
    }
  }

  implicit class RichString[T](str: String) {
    def splitFields(char: Char) = str.split(s"\\$char", -1).toVector
  }
}
