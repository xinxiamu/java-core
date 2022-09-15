/**
 *
 */
package study.netty.io.demo1.multipleclient.netty;

import java.util.Date;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import study.netty.io.demo1.multipleclient.config.GetProperties;


/**
 * @Description:
 * @version: v1.0.0
 * @author: wbl
 * @date: 2019年9月20日 下午4:56:28
 */
public class ClientConnect implements Runnable{
	public static  String SERVER_IP= GetProperties.getValue("server.ip");
	public static  int SERVER_PORT = Integer.parseInt(GetProperties.getValue("server.port"));
	public static  String LOGIN_INFO = GetProperties.getValue("client.login");
	public static  String HEART_INFO = GetProperties.getValue("client.heart");

	public static int localnum = 0;

	public void connect(){

		 EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap gate = new Bootstrap();
            gate.group(group)
    		 .channel(NioSocketChannel.class)
    		 .option(ChannelOption.TCP_NODELAY, true)
    		 .handler(new ClientInitializer());

            ChannelFuture channelFuture = gate.connect(SERVER_IP, SERVER_PORT).sync();
            localnum++;
//            System.out.println("start登录总个数："+localnum);
            if(channelFuture.channel().isActive()){
           	sendMsg(channelFuture.channel());
            }
            channelFuture.addListener(new DisconnectListener()); //添加监听，处理重连
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
	}



  public void sendMsg(Channel channel) {
	   String str = LOGIN_INFO;
       channel.writeAndFlush(str);
   }


/**
 * @Description:
 *
 */
@Override
public void run() {
	connect();
}

}
