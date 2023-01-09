package com.netty.receiver.handler;

import java.net.SocketAddress;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.util.CharsetUtil;

public class ServerHandler3 extends ChannelInboundHandlerAdapter{

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		
		
		/*
		 * SocketAddress ra = ctx.channel().remoteAddress(); SocketAddress la =
		 * ctx.channel().localAddress(); ChannelPromise cp =
		 * ctx.channel().voidPromise(); ctx.channel().disconnect();
		 * if(!ctx.channel().isActive()) ctx.connect(ra,la);
		 */
		ByteBuf in = (ByteBuf) msg;
		 System.err.println(
		 "Server3 received: " + in.toString(CharsetUtil.UTF_8));
		 
		 byte[] pingReqBufArr = new byte[in.readableBytes()];
			in.duplicate().readBytes(pingReqBufArr);
		 
		 if(pingReqBufArr.length == 2) 
		 {
			byte[] output = { (byte) 0xD0, (byte) 0x00};
			ctx.writeAndFlush( Unpooled.copiedBuffer(
					output));
		 }
//		 ctx.write(in);
//			ctx.fireChannelRead((Unpooled
//					.copiedBuffer(in.toString(CharsetUtil.UTF_8)/* +"\n#passedby"+"1...." */,
//				 CharsetUtil.UTF_8)));
		 }
	
	@Override
	 public void channelReadComplete(ChannelHandlerContext ctx) {
//	 ctx.writeAndFlush(Unpooled.EMPTY_BUFFER);
		//ctx.fireChannelReadComplete();
///		ctx.flush();
	// .addListener(ChannelFutureListener.CLOSE);
	 }
	
	@Override
	 public void exceptionCaught(ChannelHandlerContext ctx,
	 Throwable cause) {
	 cause.printStackTrace();
	 ctx.close();
	 }
	
	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
       System.err.println("reg channel id:"+ ctx.channel().id());
    }
		
}
