package com.illuca.bigdata.spark.core.帮助理解Driver向Executor发送消息

class Task extends Serializable {
    val data = List(1, 2, 3, 4)
    data.take(2)
    val logic: (Int) => Int = _ * 2

    def compute() = {
        data.map(logic)
    }
}
