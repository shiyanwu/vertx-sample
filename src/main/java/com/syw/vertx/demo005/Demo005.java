package com.syw.vertx.demo005;

import io.vertx.core.Vertx;

/**
 * @author shiyanwu
 */
public class Demo005 {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(MyFirstVerticle.class.getName());
    }
}
