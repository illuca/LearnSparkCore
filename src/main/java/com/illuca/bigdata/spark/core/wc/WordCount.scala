package com.illuca.bigdata.spark.core.wc

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object WordCount {
    def main(args: Array[String]): Unit = {
        var sparkConf = new SparkConf().setMaster("local").setAppName("WordCount")
        // connect to spark server
        var sparkContext = new SparkContext(sparkConf)

        // groupBy
        val lines: RDD[String] = sparkContext.textFile("/Users/sayo/ideaProject/atguigu-introduction-to-spark/spark-core-introduction/src/main/resources/hello.txt")

        val words: RDD[String] = lines.flatMap(_.split(" "))
        val wordGroups: RDD[(String, Iterable[String])] = words.groupBy((word => word))
        // 二元组
        val tuples: RDD[(String, Int)] = wordGroups.map {
            case (word, list) => (word, list.size)

        }
        // 计算二元组
        val array: Array[(String, Int)] = tuples.collect()
        array.foreach(println)
        // 输出
        // close connection
        sparkContext.stop()
    }
}
