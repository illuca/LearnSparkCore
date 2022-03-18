package com.illuca.bigdata.spark.core.wc

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

object WordCount简单写法 {
    def main(args: Array[String]): Unit = {
        //链接
        val sparkConf = new SparkConf().setMaster("local").setAppName("WordCount聚合感觉")
        val sparkContext = new SparkContext(sparkConf)
        val lines = sparkContext.textFile("/Users/sayo/ideaProject/MyFirstFlink/src/main/resources/hello.txt")
        val words = lines.map(line => line.split("\\s+")).flatMap(word => word)
        //二元祖, 并分组
        val word2Tuples = words.map(word => (word, 1))
        val word2Count = word2Tuples.reduceByKey((x, y) => x + y)
        val result = word2Count.collect()
        result.foreach(println)
        //断开连接
        sparkContext.stop()
    }
}
