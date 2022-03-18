package com.illuca.bigdata.spark.core.帮助理解Driver向Executor发送消息

import java.net.Socket

object Driver {
    def main(args: Array[String]): Unit = {
        val client = new Socket("localhost", 9999)
        println("Driver作为客户端启动,发送消息...")
        //输出流
        val out = client.getOutputStream()

        out.write(257);
        out.close()
        client.close()
    }
}
