package com.illuca.bigdata.spark.core.帮助理解Driver向Executor发送消息

import java.io.ObjectOutputStream
import java.net.Socket

object Driver {
    def main(args: Array[String]): Unit = {
        val client = new Socket("localhost", 9999)
        println("Driver作为客户端启动,发送消息...")
        //输出流
        val out = client.getOutputStream()
        val objOut = new ObjectOutputStream(out)
        val task = new Task()

        val subTask1 = new SubTask()
        subTask1.datas = task.data.take(2)
        subTask1.logic = task.logic
        objOut.writeObject(subTask1)

        val client2 = new Socket("localhost", 19999)
        val out2 = client2.getOutputStream()
        val objOut2 = new ObjectOutputStream(out2)
        val subTask2 = new SubTask()
        subTask2.datas = task.data.takeRight(2)
        subTask2.logic = task.logic
        objOut2.writeObject(subTask2)
        println("客户端数据发送完毕.")

        objOut2.flush()
        objOut2.close()
        client2.close()
    }
}
