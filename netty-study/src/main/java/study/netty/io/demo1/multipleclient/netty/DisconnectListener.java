/**
 *
 */
package study.netty.io.demo1.multipleclient.netty;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoop;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @version: v1.0.0
 * @author: wbl
 * @date: 2019年9月23日 上午10:00:29
 */
public class DisconnectListener implements ChannelFutureListener{

	/**
	 * @Description:
	 *
	 */
	@Override
	public void operationComplete(ChannelFuture future) throws Exception {

		if(future.isSuccess()){
			return;
		}
        System.err.println("重连");
		final EventLoop loop = future.channel().eventLoop();
		loop.schedule(new Runnable(){
			@Override
			public void run() {
				try{
					ClientConnect re = new ClientConnect();
					re.connect();
				}catch(Exception e){
					 e.printStackTrace();
				}
			}
		}, 1L, TimeUnit.SECONDS);


	}

}
