package com.syw.vertx.demo001;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;

/**
 * @author shiyanwu
 */
public class Demo001 {

    /**
     * 用vertx实现http服务器
     */
    public static void main(String[] args) {
        //vertx 核心
        Vertx vertx = Vertx.vertx();

        //创建http的server
        HttpServer httpServer = vertx.createHttpServer();

        //服务器响应
        httpServer.requestHandler(request -> {
            request.response().end("Hello vertx!");
        });

        //监听
        httpServer.listen(8080);
    }
}
