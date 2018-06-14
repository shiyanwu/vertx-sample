package com.syw.vertx.demo009;

import io.vertx.core.Vertx;
import io.vertx.core.WorkerExecutor;

/**
 * 运行阻塞代码
 * 默认情况下，executeBlocking在同一个上下文环境中（如： 同一个Verticep实例）被调用了多次，那么这些不同的
 * executeBlocking代码块会顺序执行（一个接一个）。如果不关调用顺序，可以将ordered参数的值设为false，这样
 * 任何executeBlocking都会在Worker Pool中并行执行。
 * 另外一种运行阻塞式代码的方法是使用 Worker Verticle。一个 Worker Verticle 始终会使用
 * Worker Pool 中的某个线程来执行。默认的阻塞式代码会在 Vert.x 的 Worker Pool 中执行，
 * 通过 setWorkerPoolSize 配置。
 *
 * @author shiyanwu
 */
public class Demo009 {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.executeBlocking(future -> {
            //调用一些需要耗费显著执行时间返回结果的阻塞式API
            // String result = someAPI.blockingMethod("hello");
            // future.complete(result);
        }, res -> {
            System.out.println("The result is: " + res.result());
        });

        /**
         * 可以为不同的用途创建不同的池，当使用同一个名字创建了许多worker时，它们将共享一个pool。
         * 当所有的worker executor调用了close方法被关闭过后，对应的worker pool会被销毁。
         */
        WorkerExecutor executor = vertx.createSharedWorkerExecutor("my-worker-pool");
        executor.executeBlocking(future -> {
            // 调用一些需要耗费显著执行时间返回结果的阻塞式API
            // String result = someAPI.blockingMethod("hello");
            // future.complete(result);
        }, res -> {
            System.out.println("The result is: " + res.result());
        });

        // Worker Executor 在不需要的时候必须被关闭：
        executor.close();


        //如果 Worker Executor 在 Verticle 中创建，那么 Verticle 实例销毁的同时 Vert.x 将会自动关闭这个 Worker Executor。
        //Worker Executor 可以在创建的时候配置：

        int poolSize = 10;
        // 2分钟
        long maxExecuteTime = 120000;

        WorkerExecutor executor1 = vertx.createSharedWorkerExecutor("my-worker-pool", poolSize, maxExecuteTime);
    }
}
