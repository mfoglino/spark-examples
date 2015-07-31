
case class Persona(val name: String, val dni: String)

/**
 * Created by marcos on 30/07/15.
 */
object MeliBoysMain extends App {


  val nerds = List("nico", "fran", "rub", "dan")

  val nerds2 = List.apply("nico", "fran", "rub", "dan")

  val chicos = nerds.map(c => c + "_ned")

  val bol = new Boludeces
  implicit val v = 30.0

  nerds2.foreach(nerd => println(bol.nicopregunta(nerd)))

  val x = Persona("fran", "4000000")
  val y = Persona("yuben", "4000001")

  val z = List(x,y)

  z.map(bol.naranja(_))
  println(bol.faure(List()))

  val nums = 1 to 3

  println(nums.map(_*2).reduce(_+_))


  println(bol.nicopregunta("bla"))

  import enotrolado._
  Sarasa.nose

}





class Boludeces  {



  def nicopregunta(algo: String)(implicit pepe:Double) = algo.toUpperCase()+pepe



  def naranja(p:Persona) =
    p match {
      case (Persona("fran", _)) => "bahiano"
      case _ => "otra cosa"
    }

   def faure(list:List[String]) = {
     
     list.size match {

       case 0 => "nadaaaa"
       case _ => list.mkString(",")
     }     
   } 
    
  
}

