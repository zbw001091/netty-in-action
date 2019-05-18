package nia.chapter2.echoclient.protobuf;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

public class EchoClientProtoBufOutboundHandler extends ChannelOutboundHandlerAdapter {

	@Override
	public void read(ChannelHandlerContext ctx) throws Exception {
		System.out.println("outbound read");
		super.read(ctx);
	}

	@Override
	public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
		System.out.println("outbound write");
		System.out.println(((ByteBuf)msg));
		super.write(ctx, msg, promise);
	}

	@Override
	public void flush(ChannelHandlerContext ctx) throws Exception {
		System.out.println("outbound flush");
		super.flush(ctx);
	}

}
