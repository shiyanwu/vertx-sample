package com.syw.vertx.demo007;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

/**
 * 配置集群模式的vertx，通常情况下将需要使用另外一种异步的方式来创建Vertx对象
 *
 * @author shiyanwu
 */
public class Demo007 {

    public static void main(String[] args) {
        // 注意要添加对应的集群管理器依赖，详情见集群管理器章节
        VertxOptions options = new VertxOptions();
        Vertx.clusteredVertx(options, res -> {
            if (res.succeeded()) {
                // 获取到了集群模式下的 Vertx 对象
                Vertx vertx = res.result();
                // 做一些其他的事情
            } else {
                // 获取失败，可能是集群管理器出现了问题
            }
        });
    }
}
