import scala.collection.mutable.ListBuffer
import scala.io.StdIn.readLine
import scala.util.matching.Regex


// Task c, d
object App2 {
  def main(args: Array[String]): Unit = {

    println ("Hello")
    print("Введите процент премии: ")
    val percent = readLine().toDouble

    print("Введите компенсацию питания: ")
    val bonus = readLine().toDouble

    val keyValPattern: Regex = "([0-9]+)".r
    println("Введите оклады: ")
    //val input: String = "100, 150, 200, 80, 120, 75"
    val input = readLine()
    var salary = new ListBuffer[Double]()
    for (patternMatch <- keyValPattern.findAllMatchIn(input)) {
      val v = patternMatch.toString()
      salary += v.toDouble
    }
    val salary2 = salary.toList
    val salary3 = scaleList(salary2, percent, bonus)
    val avg = salary3.sum / salary3.size
    val delta = deltaList(salary3, avg)
    val salary_delta = List(
      salary2, delta ).transpose

    println("Оклады")
    println(salary2)
    println("Оклад + премия + питание")
    println(salary3)
    println("Средний оклад")
    println(avg)
    println("Отклонение окладов")
    //println(salary_delta)
    for (element <- salary_delta) {
      println(element)
    }

    val kpi = List (50.0, -50.0, 30.0, 40.0, 0.0, 10.0)
    println("----kpi---")
    val kpi_s = (salary3 lazyZip kpi).map(_ + _)
    println(kpi)
    println("Оклад")
    println(salary3)
    println("Оклад + kpi")
    println(kpi_s)
    println("Оклад max")
    println(kpi_s.max)
    println("Оклад min")
    println(kpi_s.min)

    println("----new employee---")
    val new_emp = List (350.0, 90.0)
    val new_employee = salary3 ++ new_emp
    val sorted_emp = new_employee.sorted
    println(new_employee)
    println(sorted_emp)

    val n = 130.0
    val i = sorted_emp.indexWhere(element => element > n)
    println (s"индекс вставки $i")
    val n_e = insert(n, sorted_emp)
    println (n_e)

    println ("---middle---")
    //val minn = 130.0
    //val maxx = 190.0

    print("Введите нижнюю границу: ")
    val minn = readLine().toDouble

    print("Введите верхнюю границу: ")
    val maxx = readLine().toDouble
    println ("индексы вставки")
    val middle =   n_e.zipWithIndex.filter(pair => pair._1 >= minn && pair._1 <= maxx).map(pair => pair._2)
    println (middle)

    println ("---+7%---")
    val salary5 = n_e.map(_*1.07)
    println (salary5)

   // print( (List(10, 20) lazyZip  List(3, 4, 5)).map(_ * _))
  }

  def scaleList(xs: List[Double], p: Double, b: Double): List[Double] = xs match {
    case Nil => xs
    case x :: xs1 => x + (x * p / 100) + b :: scaleList(xs1, p, b)
  }

  def deltaList(xs: List[Double], a: Double): List[Double] = xs match {
    case Nil => xs
    case x :: xs1 => x - a :: deltaList(xs1, a)
  }

  def insert(x: Double, xs: List[Double]): List[Double] =
    if (xs.isEmpty || x <= xs.head) x :: xs
    else xs.head :: insert(x, xs.tail)

}
