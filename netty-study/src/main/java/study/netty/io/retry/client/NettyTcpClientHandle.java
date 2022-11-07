package study.netty.io.retry.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;

public class NettyTcpClientHandle extends ChannelInboundHandlerAdapter {

    private RetryStrategy retryStrategy;

    public NettyTcpClientHandle(RetryStrategy retryStrategy) {
        this.retryStrategy = retryStrategy;
    }

    /**
     * 当我们的通道被激活的时候触发的监听
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("--------客户端通道激活---------");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            // NIO 通信 （传输的数据是什么？ ---------> Buffer 对象）
            ByteBuf buf = (ByteBuf) msg;
            //定义byte数组
            byte[] req = new byte[buf.readableBytes()];
            // 从缓冲区获取数据到 req
            buf.readBytes(req);
            //读取到的数据转换为字符串
            String body = new String(req, "utf-8");
            System.out.println("客户端读取到数据：" + body);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放数据 （如果你读取数据后又写出去数据就不需要调用此方法，因为底层代码帮忙实现额释放数据）
            ReferenceCountUtil.release(msg);
        }
    }

    /**
     * 当我们读取完成数据的时候触发的监听
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("--------客户端数据读取完毕---------");
    }

    /**
     * 当我们读取数据异常的时候触发的监听
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("--------客户端数据读取异常---------");
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("NettyTcpClientHandle===触发通道不活跃, 进行重连!");
        retryStrategy.setConsumer(el->{
            System.out.println("NettyTcpClientHandle===重连成功,  触发做自己的事情!!!!!!!");
            ctx.channel().writeAndFlush(Unpooled.copiedBuffer("bbbb".getBytes())); //重连成功发条消息试试
        });
        retryStrategy.processRetryConnect(ctx);
    }
}

