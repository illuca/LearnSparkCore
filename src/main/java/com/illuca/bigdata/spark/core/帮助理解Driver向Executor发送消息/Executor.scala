package com.illuca.bigdata.spark.core.帮助理解Driver向Executor发送消息

import java.net.{ServerSocket, Socket}

object Executor {
    def main(args: Array[String]): Unit = {
        val server = new ServerSocket(9999)
        println("executor作为服务器启动,接收消息...")
        //accept表示处于阻塞状态
        val clientSocket = server.accept()
        val in = clientSocket.getInputStream()
        println("接收到消息: "+in.read())
        in.close()
        clientSocket.close()
        server.close()
    }
}
