package com.syw.vertx.demo006;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

/**
 * @author shiyanwu
 */
public class Demo006 {
    public static void main(String[] args) {
        /**
         * Vertx 对象是Vert.x的控制中心，也是做一切事情的基础，
         * 包括创建客户端和服务器，获取事件总线的引用、设置定时器等。
         */
        Vertx vertx = Vertx.vertx();

        //如果缺省的配置不合适，可在创建对象的同时指定配置项，如下：
        Vertx vertx1 = Vertx.vertx(new VertxOptions().setWorkerPoolSize(40));
    }
}
