package study.netty.io.retry.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.UnsupportedEncodingException;

public class ServerPoHandlerProto extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("--------服务端通道激活---------");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws UnsupportedEncodingException {

        // NIO 通信 （传输的数据是什么？ ---------> Buffer 对象）
        ByteBuf buf = (ByteBuf) msg;
        //定义byte数组
        byte[] req = new byte[buf.readableBytes()];
        // 从缓冲区获取数据到 req
        buf.readBytes(req);
        //读取到的数据转换为字符串
        String body = new String(req, "utf-8");
        System.out.println("服务端读取到数据：" + body);

        //响应给客户端的数据
        ctx.writeAndFlush(Unpooled.copiedBuffer("netty server response data 发送数据到客户端".getBytes()));
    }

    /**
     * 当我们读取完成数据的时候触发的监听
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("--------服务端数据读取完毕---------");
    }

    /**
     * 当我们读取数据异常的时候触发的监听
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("--------服务端数据读取异常---------");
        cause.printStackTrace();
        ctx.close();
    }
}
