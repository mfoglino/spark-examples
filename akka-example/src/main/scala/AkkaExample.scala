import akka.actor.{Props, ActorSystem, Actor}

/**
 * Created by marcos on 26/07/15.
 */
object AkkaExample extends App{


  val system = ActorSystem("HelloSystem")
  // default Actor constructor
  //val helloActor = system.actorOf(Props[CompressedFileActor], name = "path1")

  val disks = List("disk1", "disk2", "disk3")
  val actor = system.actorOf(Props[CompressedFileActor], name = "myActor")


  disks.map(file => actor ! UncompressCompress("compress", file))




}

case class UncompressCompress(val action:String, val pass:String)

class CompressedFileActor extends Actor {
  def receive = {
    case UncompressCompress("compress", file) => getDoSomethingWithADisk(file)
    case _       => println("huh?")
  }


  def getDoSomethingWithADisk(aDisk:String)={
    println("Doing something:" + aDisk)
  }

}