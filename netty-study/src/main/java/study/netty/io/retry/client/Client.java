package study.netty.io.retry.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import study.netty.io.retry.Constants;

import java.net.InetSocketAddress;

public class Client {

    public static void main(String[] args) {

        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        RetryStrategy retryStrategy = new RetryStrategy();
        retryStrategy.setRetryAddTime(1);
        retryStrategy.setRetryMaxCount(10);
        Bootstrap bootstrap = retryStrategy.buildBootstrapAndReturnBootstrap(new Bootstrap());

        bootstrap = bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
//                        pipeline.addLast(new StringEncoder());
//                        pipeline.addLast(new StringDecoder());
                        // 添加自定义重试连接处理器
                        pipeline.addLast(new NettyTcpClientHandle(retryStrategy));
                    }
                });

        bootstrap.remoteAddress(new InetSocketAddress(Constants.HOST, Constants.PORT));
        try {
            ChannelFuture channelFuture = bootstrap.connect();
            // 添加监听, 最初启动时候是否成功!
            channelFuture.addListener(el->{
                // 失败则进行重试!
                if (!el.isSuccess()) {
                    ChannelHandlerContext context = channelFuture.channel().pipeline().context(NettyTcpClientHandle.class);
                    retryStrategy.processRetryConnect(context);
                }else {
                    System.out.println("NettyTcpClient-启动成功了!!!");
                    channelFuture.channel().writeAndFlush(Unpooled.copiedBuffer("aaa".getBytes()));
                }
            });

            channelFuture.sync().channel().closeFuture().sync();
        } catch (Exception e) {
            System.out.println(String.format("NettyTcpClient-发生异常, 信息:%s", e.getMessage()));
        }
    }
}
