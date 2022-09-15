/**
 *
 */
package study.netty.io.demo1.multipleclient.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import study.netty.io.demo1.multipleclient.config.InitConfig;

/**
 * @Description:
 * @version: v1.0.0
 * @author: wbl
 * @date: 2019年9月20日 下午5:29:32
 */
public class ClientHandler extends ChannelInboundHandlerAdapter{
	  @Override
	    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//		  System.out.println("channelActive登录成功！当前总登录数："+InitConfig.client_num);
		  InitConfig.client_active++;
	    }
	/**
	 * @Description:
	 *
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
			String data = (String) msg;
			if("login_noterize".equals(data)){
				  InitConfig.client_num ++;
//				System.out.println(ctx.channel().localAddress().toString() + "登录确认！当前总登录数："+InitConfig.client_num);
			}else if("heart_noterize".equals(data)){
//				System.out.println(ctx.channel().localAddress().toString() + "心跳确认！当前总登录数："+InitConfig.client_num);
			}
	}
	/**
	 * @Description:
	 *
	 */
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		 InitConfig.client_num --;
		 ClientConnect.localnum--;
		 InitConfig.client_active--;
		super.channelInactive(ctx);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("Unexpected exception from downstream : " + cause.getMessage());
        ctx.close();
	}

	  /** 空闲次数 */
    private int idle_count = 1;
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object obj) throws Exception {

        if (obj instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) obj;
            if (IdleState.WRITER_IDLE.equals(event.state())) { // 如果写通道处于空闲状态就发送心跳命令
            	  String str = InitConfig.HEART_INFO;
//            	  System.out.println("client heartbeat: ["+new Date() +"]  "+ctx.channel().remoteAddress().toString().substring(1)+"当前总登录数："+InitConfig.client_num);
            	  ctx.channel().writeAndFlush(str);
            }
        }
    }


}
