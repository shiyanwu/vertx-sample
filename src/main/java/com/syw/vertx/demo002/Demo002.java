package com.syw.vertx.demo002;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

/**
 * @author shiyanwu
 */
public class Demo002 {

    /**
     * 用vertx实现http服务器
     */
    public static void main(String[] args) {
        //vertx 核心
        Vertx vertx = Vertx.vertx();

        //web路由器
        Router router = Router.router(vertx);
        router.route("/").handler(context -> {
            context.response().end("hello root!");
        });

        router.route("/abc").handler(context -> {
            String name = context.request().getParam("name");
            String header = context.request().getHeader("Content-Type");
            context.response().end("hello abc! " + name + " header:" + header);
        });

        //创建http的server
        HttpServer httpServer = vertx.createHttpServer();

        //服务器响应
        httpServer.requestHandler(router::accept);

        //监听
        httpServer.listen(8080);
    }
}
