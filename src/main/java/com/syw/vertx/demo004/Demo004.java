package com.syw.vertx.demo004;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

/**
 * @author shiyanwu
 */
public class Demo004 {

    /**
     * 用vertx实现http服务器
     */
    public static void main(String[] args) {
        //vertx 核心
        Vertx vertx = Vertx.vertx();

        //web路由器
        Router router = Router.router(vertx);
        router.get("/sync").handler(context -> {
            //同步处理请求
            context.response().end("hello get!");
            System.out.println("sync" + Thread.currentThread().getName());
        });

        router.get("/async").blockingHandler(context -> {
            //异步处理请求
            //耗时操作（数据库访问、服务访问）
            context.response().end("hello get api *!");
            System.out.println("async" + Thread.currentThread().getName());

        });

        //创建http的server
        HttpServer httpServer = vertx.createHttpServer();

        //服务器响应
        httpServer.requestHandler(router::accept);

        //监听
        httpServer.listen(8080);
    }
}
