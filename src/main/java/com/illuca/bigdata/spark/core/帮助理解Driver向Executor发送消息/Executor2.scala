package com.illuca.bigdata.spark.core.帮助理解Driver向Executor发送消息

import java.io.ObjectInputStream
import java.net.ServerSocket

object Executor2 {
    def main(args: Array[String]): Unit = {
        val server = new ServerSocket(19999)
        println("executor作为服务器启动,接收消息...")
        //accept表示处于阻塞状态
        val clientSocket = server.accept()
        val in = clientSocket.getInputStream()
        val objIn = new ObjectInputStream(in)
        val task = objIn.readObject().asInstanceOf[SubTask]
        val ints = task.compute()
        println("接收到消息: "+ ints)
        objIn.close()
        clientSocket.close()
        server.close()
    }
}
