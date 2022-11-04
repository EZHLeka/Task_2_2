import sun.invoke.empty.Empty

import scala.collection.mutable.ListBuffer
import scala.io.StdIn.readLine
import scala.util.matching.Regex

object App {
  def main(args: Array[String]): Unit = {

    print ("Hello, Scala! \n")
    val str = "Hello, Scala!"
    val str1 = "and goodbye python!"
    println (str.reverse)
    println(str.map(_.toLower))
    println(str drop 1)
    val l = str.size-1
   // println(l)
    println(str.slice(0, l))
    println(str+" "+ str1)

    print("Введите годовой доход: ")
    val  y_salary = readLine().toFloat//
    print("Введите процент премии:  ")
    val percent = readLine().toFloat//
    print("Введите компенсацию питания: ")
    val bonus = readLine().toFloat
    println(s" $y_salary $percent $bonus")
    println(m_salary(y_salary , percent, bonus))


  }

  val m_salary = (x: Float, y: Float, z: Float) => ((x + (x * y /100) + z*12) * 0.87)/12



}
