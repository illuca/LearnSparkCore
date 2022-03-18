package com.illuca.bigdata.spark.core.wc

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object WordCount聚合感觉 {
    def main(args: Array[String]): Unit = {
        //链接
        val sparkConf: SparkConf = new SparkConf().setMaster("local").setAppName("WordCount聚合感觉")
        val sparkContext = new SparkContext(sparkConf)
        val lines: RDD[String] = sparkContext.textFile("/Users/sayo/ideaProject/MyFirstFlink/src/main/resources/hello.txt")
        val words: RDD[String] = lines.map(line => line.split("\\s+")).flatMap(word => word)
        //二元祖, 并分组
        val word2Tuples: RDD[(String, Iterable[(String, Int)])] = words
            .map(word => (word, 1))
            .groupBy(tuple => tuple._1)
        //计算二元组
        val array: Array[String] = Array[String]("naruto", "sasuke")

        val word2Count: RDD[(String, Int)] = word2Tuples.map {
            case (word, list) => {
                list.reduce((t1, t2) => (t1._1, t1._2 + t2._2))
            }
        }
        val result: Array[(String, Int)] = word2Count.collect()
        result
            .foreach(println)
        //断开连接
        sparkContext.stop()
    }
}
