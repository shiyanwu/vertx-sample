package com.syw.vertx.demo010;

import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServer;
import io.vertx.core.net.NetServer;

import java.nio.file.FileSystem;

/**
 * @author shiyanwu
 */
public class Demo010 {
    public static void main(String[] args) {
        /**
         * Vert.x中的Future可以用来协调多个异步操作的结果。它支持并发组合（并执行多个异步调用）
         * 和顺序组合（依次执行异步调用）。
         *
         * 并发合并
         * CompositeFuture.all 方法接受多个Future对象作为参数（最多传入6个，或者传入list）。当所有的Future
         * 都成功完成，该方法将返回一个成功的Future;当作一个Future执行失败，则返回一个失败的Future:
         */
        Future<HttpServer> httpServerFuture = Future.future();
        //httpServer.listen(httpServerFuture.completer());

        Future<NetServer> netServerFuture = Future.future();
        //netServer.listen(netServerFuture.completer());

        CompositeFuture.all(httpServerFuture, netServerFuture).setHandler(ar -> {
            if (ar.succeeded()) {
                // 所有服务器启动完成
            } else {
                // 有一个服务器启动失败
            }
        });

        /**
         * join 方法的合并会等待所有的Future完成，无论成败。CompositeFuture.join 方法接受多个
         * Future 作为参数（最多6个），并将结果归并成一个 Future 。当全部 Future 成功执行完成，
         * 得到的 Future 是成功状态的；当至少一个 Future 执行失败时，得到的 Future 是失败状态的。
         */


        /*
        CompositeFuture.join(future1, future2, future3).setHandler(ar -> {
            if (ar.succeeded()) {
                // 所有都成功
            } else {
                // 至少一个失败
            }
        });

        CompositeFuture.join(Arrays.asList(future1, future2, future3));
        */

        /**
         * 顺序合并
         * 和 all 以及 any 实现的并发组合不同，compose 方法作用于顺序组合 Future。
         */

        /*
        FileSystem fs = vertx.fileSystem();
        Future<Void> startFuture = Future.future();

        Future<Void> fut1 = Future.future();
        fs.createFile("/foo", fut1.completer());

        fut1.compose(v -> {
            // fut1中文件创建完成后执行
            Future<Void> fut2 = Future.future();
            fs.writeFile("/foo", Buffer.buffer(), fut2.completer());
            return fut2;
        }).compose(v -> {
            // fut2文件写入完成后执行
            fs.move("/foo", "/bar", startFuture.completer());
        },
            // 如果任何一步失败，将startFuture标记成failed
            startFuture);
        */

    }
}
