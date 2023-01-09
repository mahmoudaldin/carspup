package com.netty.receiver.handler;

import java.util.Arrays;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

public class ServerHandler2 extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		ByteBuf in = (ByteBuf) msg;
		System.err.println("Server2 received: " + in.toString(CharsetUtil.UTF_8));

		
		
		byte[] databufArr = new byte[in.readableBytes()];
		in.duplicate().readBytes(databufArr);
		
		 if(databufArr.length == 2) 
		 {
			ctx.fireChannelRead(msg);
			return;
		 }
		
		byte[] holder = null;
		int i = 0;// index

		holder = Arrays.copyOfRange(databufArr, i, i + 1);

		char[] packetTypeIdInfo = DataTypeUtil.hexToBinary(DataTypeUtil.bytesToHex(holder)).toCharArray();

		// type info

		String Retained = "" + packetTypeIdInfo[0];

		String qosLevelBits = "" + packetTypeIdInfo[1] + packetTypeIdInfo[2];

		String qosLevel = "";
		switch (qosLevelBits) {
		case "00":
			qosLevel = "QoS0";
			break;
		case "10":
			qosLevel = "QoS1";
			break;
		case "01":
			qosLevel = "QoS2";
			break;
		case "11":
			qosLevel = "reserved";
			break;
		default:
			qosLevel = "reserved";
		}

		boolean willDUP = packetTypeIdInfo[3] == '1';

		int packetType = Integer.parseInt(new String(Arrays.copyOfRange(packetTypeIdInfo, 4, 7 + 1)),2);

		// end type info

		// (151 & 127) + (2 * 128)

		int remainingLen = databufArr[++i];

		if (remainingLen > 127)

		{
			remainingLen = (remainingLen & 127) + (databufArr[++i] * 128);
		}

		holder = Arrays.copyOfRange(databufArr, ++i, ++i + 1);

		int topicNameLength = Integer.parseInt(DataTypeUtil.bytesToHex(holder), 16);

		holder = Arrays.copyOfRange(databufArr, i + 1, (i += topicNameLength) + 1);

		String topicName = new String(holder);

		byte[] packetId = null;
		if (qosLevel != "QoS0") {
			packetId = Arrays.copyOfRange(databufArr, ++i, ++i + 1);
		}

		// byte[] payload = Arrays.copyOfRange(databufArr, i + 1, (i += remainingLen - 5
		// - topicNameLength) + 1);

		byte[] payload = Arrays.copyOfRange(databufArr, i + 1, i = databufArr.length);

		String payLoadAsText = new String(payload);

		byte[] output = new byte[1 + 1 + packetId.length];
		output[0] = (byte) 0x40;
		output[1] = (byte) packetId.length;
		System.arraycopy(packetId, 0, output, 1 + 1, packetId.length);
		
		ctx.writeAndFlush(Unpooled.copiedBuffer(output));
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) {
		// ctx.writeAndFlush(Unpooled.EMPTY_BUFFER);
		// ctx.pipeline().addFirst("o", new ServerOutHandler());
		// ctx.flush();
//	 .addListener(ChannelFutureListener.CLOSE);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}

	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		System.err.println("2ndS reg channel id:" + ctx.channel().id());
	}

}
