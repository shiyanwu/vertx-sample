package com.syw.vertx.demo003;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

/**
 * @author shiyanwu
 */
public class Demo003 {

    /**
     * 用vertx实现http服务器
     */
    public static void main(String[] args) {
        //vertx 核心
        Vertx vertx = Vertx.vertx();

        //web路由器
        Router router = Router.router(vertx);
        router.get("/get").handler(context -> {
            context.response().end("hello get!");
        });

        router.post("/post").handler(context -> {
            context.response().end("hello post!");
        });

        router.get("/api/*").handler(context -> {
            context.response().end("hello get api *!");
        });

        //创建http的server
        HttpServer httpServer = vertx.createHttpServer();

        //服务器响应
        httpServer.requestHandler(router::accept);

        //监听
        httpServer.listen(8080);
    }
}
