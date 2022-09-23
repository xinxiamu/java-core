package com.waylau.netty.demo.http2.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http2.Http2Settings;

import java.util.concurrent.TimeUnit;

/**
 * Reads the first {@link Http2Settings} object and notifies a {@link ChannelPromise}
 *
 * @since 1.0.0 2019年12月29日
 * @author <a href="https://waylau.com">Way Lau</a>
 */
public class Http2SettingsHandler extends SimpleChannelInboundHandler<Http2Settings> {
    private final ChannelPromise promise;

    /**
     * Create new instance
     *
     * @param promise Promise object used to notify when first settings are received
     */
    public Http2SettingsHandler(ChannelPromise promise) {
        this.promise = promise;
    }

    /**
     * Wait for this handler to be added after the upgrade to HTTP/2, and for initial preface
     * handshake to complete.
     *
     * @param timeout Time to wait
     * @param unit {@link TimeUnit} for {@code timeout}
     * @throws Exception if timeout or other failure occurs
     */
    public void awaitSettings(long timeout, TimeUnit unit) throws Exception {
        if (!promise.awaitUninterruptibly(timeout, unit)) {
            throw new IllegalStateException("Timed out waiting for settings");
        }
        if (!promise.isSuccess()) {
            throw new RuntimeException(promise.cause());
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Http2Settings msg) throws Exception {
        promise.setSuccess();
        ctx.pipeline().remove(this);
    }
}
