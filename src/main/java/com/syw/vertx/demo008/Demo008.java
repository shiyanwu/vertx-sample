package com.syw.vertx.demo008;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;

/**
 * 除了很少的特例（例如，“Sync”结尾的某些文件系统操作），Vert.x中的所有API都不会阻塞调用线程。
 * 如果可以立即提供结果，它将立即返回，否则您需要提供一个处理器（Handler）来接收稍后回调的事件。
 * 因为Vert.x API不会阻塞线程，所以通过Vert.x，您可以只使用少量的线程来处理大量的并发。
 * 当使用传统的阻塞式API做以下操作时，调用线程可能会被阻塞；
 *      从Socket中读取数据
 *      写数据到磁盘
 *      发送消息给接收者并等待回复
 *      其他很多情况
 *
 *
 * @author shiyanwu
 */
public class Demo008 {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.setPeriodic(1000, id -> {
            // this handler will get called every second
            //这个处理器将会每隔一秒被调用一次
            System.out.println("time fired!");
        });

        //创建http的server
        HttpServer server = vertx.createHttpServer();

        server.requestHandler(request -> {
            // This handler will be called every time an HTTP request is received at the server
            // 服务器每次收到一个HTTP请求时这个处理器将被调用
            request.response().end("hello world!");
        });

        //监听
        server.listen(8080);
    }
}
