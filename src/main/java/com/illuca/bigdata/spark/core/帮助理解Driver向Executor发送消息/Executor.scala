package com.illuca.bigdata.spark.core.帮助理解Driver向Executor发送消息

import java.io.ObjectInputStream
import java.net.{ServerSocket, Socket}
import scala.collection.immutable

object Executor {
    def main(args: Array[String]): Unit = {
        val server = new ServerSocket(9999)
        println("executor作为服务器启动,接收消息...")
        //accept表示处于阻塞状态
        val clientSocket = server.accept()
        val in = clientSocket.getInputStream()
        val objIn = new ObjectInputStream(in)
        val task = objIn.readObject().asInstanceOf[Task]
        val ints = task.compute()
        println("接收到消息: "+ ints)
        objIn.close()
        clientSocket.close()
        server.close()
    }
}
