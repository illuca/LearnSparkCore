package com.illuca.bigdata.spark.core.帮助理解Driver向Executor发送消息

import java.io.ObjectOutputStream
import java.net.Socket

object Driver {
    def main(args: Array[String]): Unit = {
        println("Driver作为客户端启动,发送消息...")
        val task = new Task()

        val client1 = new Socket("localhost", 9999)
        val out1 = client1.getOutputStream()
        val objOut1 = new ObjectOutputStream(out1)
        val subTask1 = new SubTask()
        subTask1.datas = task.data.take(2)
        subTask1.logic = task.logic
        objOut1.writeObject(subTask1)

        objOut1.flush()
        objOut1.close()
        client1.close()
        println("客户端1数据发送完毕.")

        val client2 = new Socket("localhost", 19999)
        val out2 = client2.getOutputStream()
        val objOut2 = new ObjectOutputStream(out2)
        val subTask2 = new SubTask()
        subTask2.datas = task.data.takeRight(2)
        subTask2.logic = task.logic
        objOut2.writeObject(subTask2)

        objOut2.flush()
        objOut2.close()
        client2.close()
        println("客户端2数据发送完毕.")
    }
}
