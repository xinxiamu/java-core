/**
 *
 */
package study.netty.io.demo1.multipleclient.netty;

import java.util.concurrent.TimeUnit;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;


/**
 * @Description:
 * @version: v1.0.0
 * @author: wbl
 * @date: 2019年9月20日 下午5:27:50
 */
public class ClientInitializer extends ChannelInitializer<SocketChannel>{

	/**
	 * @Description:
	 *
	 */
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ch.pipeline().addLast(new IdleStateHandler(0, 30, 0, TimeUnit.SECONDS));
        ch.pipeline().addLast(new StringEncoder());
      	ch.pipeline().addLast(new StringDecoder());
      	ch.pipeline().addLast(new ClientHandler());
	}

}
