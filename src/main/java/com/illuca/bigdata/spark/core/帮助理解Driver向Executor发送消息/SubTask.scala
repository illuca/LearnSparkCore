package com.illuca.bigdata.spark.core.帮助理解Driver向Executor发送消息

class SubTask extends Serializable {
    var datas: List[Int] = _
    var logic: (Int) => Int = _
    def compute() = {
        datas.map(logic)
    }
}
