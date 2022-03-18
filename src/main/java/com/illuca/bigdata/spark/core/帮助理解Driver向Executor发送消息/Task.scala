package com.illuca.bigdata.spark.core.帮助理解Driver向Executor发送消息

class Task {
    val data = List(1, 2, 3, 4)
    val logic: (Int) => Int = _ * 2

    def compute() = {
        data.map(logic)
    }
}
