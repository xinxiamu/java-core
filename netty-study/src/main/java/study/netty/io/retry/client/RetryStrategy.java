package study.netty.io.retry.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.EventLoop;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * 重试策略类
 */
public class RetryStrategy {

    // 启动引导类
    private Bootstrap bootstrap;
    // 最大重试次数
    private Integer retryMaxCount = Integer.MAX_VALUE;
    // 最长间隔重试时间
    private Integer retryMaxTime = 60;
    // 每次重试增加多少秒
    private Integer retryAddTime = 0;
    // 重试成功触发的事件
    private Consumer<Boolean> consumer;
    // 初始重试次数
    private int currentRetryCount = 0;
    // 初始重试间隔 1秒
    private long delay = 1L;

    public Bootstrap buildBootstrapAndReturnBootstrap(Bootstrap bootstrap) {
        this.bootstrap = bootstrap;
        return bootstrap;
    }

    public void setRetryMaxCount(Integer retryMaxCount) {
        this.retryMaxCount = retryMaxCount;
    }

    public void setRetryMaxTime(Integer retryMaxTime) {
        this.retryMaxTime = retryMaxTime;
    }

    public void setRetryAddTime(Integer retryAddTime) {
        this.retryAddTime = retryAddTime;
    }

    public void setConsumer(Consumer<Boolean> consumer) {
        this.consumer = consumer;
    }

    public void processRetryConnect(ChannelHandlerContext ctx) {
        if (Objects.isNull(ctx)){
            System.out.println("RetryStrategy===处理器都不存在!!!");
            // 关闭整个客户端, 是整个netty应用停止
            bootstrap.config().group().shutdownGracefully();
            return;
        }

        if (currentRetryCount >= retryMaxCount) {
            System.out.println("RetryStrategy===重试已达最大次数, 取消重试, 关闭客户端!!!");
            // 关闭整个客户端, 是整个netty应用停止
            bootstrap.config().group().shutdownGracefully();
            return;
        }

        long delay = this.getDelay();
        EventLoop eventLoop = ctx.channel().eventLoop();
        eventLoop.schedule(() -> {
            try {
                currentRetryCount++;
                long delayTime = this.delay;
                int count = this.currentRetryCount;

                ChannelFuture channelFuture = bootstrap.connect();
                channelFuture.addListener(future -> {
                    // 触发事件
                    boolean futureSuccess = future.isSuccess();
                    // 失败后重调递归调
                    if (!futureSuccess) {
                        this.processRetryConnect(ctx);
                    } else {
                        // 成功后重置次数和延迟时间
                        this.currentRetryCount = 0;
                        this.delay = 0L;
                        Optional.ofNullable(consumer).ifPresent(el -> el.accept(Boolean.TRUE));
                    }
                    System.out.println(String.format("===RetryStrategy===, 重连, 当前次数:%s, 当前延迟重试间隔:%s秒, 重试结果:%b", count, delay, futureSuccess));
                });
                channelFuture.sync();
            } catch (Exception e) {
                System.out.println("NettyRetryClientHandle====重连失败, 原因: " + e.getMessage());
            }
        }, delay, TimeUnit.SECONDS);
        // 传递给下一个处理器
        ctx.fireChannelInactive();
    }

    private long getDelay() {
        if (delay >= retryMaxTime) {
            return retryMaxTime;
        }
        if (currentRetryCount != 0) {
            delay += retryAddTime;
        }
        return delay;
    }
}
