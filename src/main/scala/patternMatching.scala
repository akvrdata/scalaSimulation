import org.apache.spark._
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.SparkContext._

import scala.util.matching.Regex

class LogParser  extends Serializable  {

  //def containsIP(line:String) : Boolean = return line matches "^([0-9\\.]+) .*$"

  def extractIP(line:String) : String ={
    val pattern = "^([0-9\\.]+) .*$".r
    val pattern(ip:String) = line
    return ip.toString
  }

  def getTop10(s:RDD[String]):Array[(String,Int)] ={
    //val validLogFiles = s.filter(containsIP)
    val validips = s.map(extractIP(_))
    val mappedips = validips.map(x=>(x,1))
    val reducedips = mappedips.reduceByKey((x,y)=>(x+y))
    val sorted = reducedips.sortBy(x=>x._2,false)
    return sorted.take(10)
  }
}

object patternMatching {

  def main(args: Array[String]) {

    val conf = new SparkConf().setAppName("LogParser1").setMaster("local[2]").set("spark.executor.memory","1g");
    val sc = new SparkContext(conf)
    sc.setLogLevel("WARN")

    val logfilecontent = sc.textFile("/data/spark/project/access/access.log.45.gz")

    val iplist = "172.2.2.2 This is ip"

    val obj = new LogParser()
    obj.getTop10(logfilecontent)

  }
}






