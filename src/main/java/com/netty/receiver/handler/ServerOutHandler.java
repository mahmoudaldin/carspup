package com.netty.receiver.handler;

import java.net.SocketAddress;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

public class ServerOutHandler extends ChannelOutboundHandlerAdapter {
	 
	
	  @Override
	    public void read(ChannelHandlerContext ctx) throws Exception {
	        ctx.read();
	    }

	    @Override
	    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
//	    	ctx.fireChannelRead(msg);
	        ctx.write(msg, promise);
	    }
	
	 @Override
	    public void flush(ChannelHandlerContext ctx) throws Exception {
	        ctx.flush();
	    }
	
	 @Override
	    public void connect(ChannelHandlerContext ctx, SocketAddress remoteAddress,
	            SocketAddress localAddress, ChannelPromise promise) throws Exception {
	        ctx.connect(remoteAddress, localAddress, promise);
	    }
	 
	}