package com.syw.vertx.demo005;

import io.vertx.core.AbstractVerticle;

/**
 * @author shiyanwu
 */
public class MyFirstVerticle extends AbstractVerticle {
    @Override
    public void start() {
        vertx.createHttpServer().requestHandler(req -> {
           req.response().putHeader("content-type", "text/plain")
                   .end("Hello World!");
        }).listen(8080);
    }
}
