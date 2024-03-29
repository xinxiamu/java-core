package study.netty.io.demo1;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import study.netty.io.demo1.hg.HgCliType;
import study.netty.io.demo1.hg.HgCliSend;

/**
 * netty 客户端
 */
public class Client {

    public static void main(String[] args) throws InterruptedException {

        //线程工作组
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        //辅助类 帮我我们创建netty服务
        Bootstrap b = new Bootstrap();
        b.group( workerGroup)//绑定两个工作组
                .channel(NioSocketChannel.class)//设置NIO模式

                //初始化绑定服务通道
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel sc) throws Exception {
                        //为通道进行初始化：数据传输过来的时候会进行拦截和执行 (可以有多个拦截器)
                        sc.pipeline().addLast(new ClientHandler());
//                        sc.pipeline().addLast("ping", new IdleStateHandler(60, 20, 60 * 10, TimeUnit.SECONDS));
                    }
                });
        ChannelFuture cf = b.connect("192.168.0.117",56667).syncUninterruptibly();
//        String clientMsg = "netty client request data-";
//        String clientMsg = ">$pro,m4*E975";
        HgCliSend send = new HgCliSend(HgCliType.S13, null);
        String msg = send.getResult();
//        String msg = "aaa";
        System.out.println("send msg: " + msg);
        cf.channel().writeAndFlush(Unpooled.copiedBuffer(msg.getBytes()));
        //释放连接
//        cf.channel().closeFuture().sync();
//        workerGroup.shutdownGracefully();
    }
}
