package com.netty.receiver.handler;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.BitSet;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

public class ServerHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {

		ByteBuf in = (ByteBuf) msg;
		System.err.println("Server received: " + in.toString(CharsetUtil.UTF_8));

		byte[] connbufArr = new byte[in.readableBytes()];
		in.duplicate().readBytes(connbufArr);
		byte[] holder = null;
		int i = 0;// index

		// Connection packet
		if (connbufArr[i] == 0x10) {
			int remainingLength = connbufArr[++i];

			if (connbufArr.length - 2 != remainingLength) {
				ctx.disconnect();
				System.out.println("Packet is not compelete!");
			}
			holder = Arrays.copyOfRange(connbufArr, ++i, ++i + 1);

			int protocolNameLength = Integer.parseInt(DataTypeUtil.bytesToHex(holder), 16);

			holder = Arrays.copyOfRange(connbufArr, i + 1, (i += protocolNameLength) + 1);

			String protocolName = new String(holder);

			int protocolVer = connbufArr[++i];

			holder = Arrays.copyOfRange(connbufArr, ++i, i + 1);

			char[] connectFlags = DataTypeUtil.hexToBinary(DataTypeUtil.bytesToHex(holder)).toCharArray();

			// flags

			boolean reserved = connectFlags[0] == '1';

			boolean cleanSession = connectFlags[1] == '1';

			boolean willFlag = connectFlags[2] == '1';

			boolean willQoS0 = connectFlags[3] == '1';

			boolean willQoS1 = connectFlags[4] == '1';

			boolean willRetain = connectFlags[5] == '1';

			boolean passwordExist = connectFlags[6] == '1';

			boolean usernameExist = connectFlags[7] == '1';

			// end flags

			holder = Arrays.copyOfRange(connbufArr, ++i, ++i + 1);

			int timeout = Integer.parseInt(DataTypeUtil.bytesToHex(holder), 16);

			holder = Arrays.copyOfRange(connbufArr, ++i, ++i + 1);

			int clientIdLen = Integer.parseInt(DataTypeUtil.bytesToHex(holder), 16);

			holder = Arrays.copyOfRange(connbufArr, i + 1, (i += clientIdLen) + 1);

			String clientId = new String(holder);

			String username = null;
			if (usernameExist) {

				holder = Arrays.copyOfRange(connbufArr, ++i, ++i + 1);

				int usernameLen = Integer.parseInt(DataTypeUtil.bytesToHex(holder), 16);

				holder = Arrays.copyOfRange(connbufArr, i + 1, (i += usernameLen) + 1);

				username = new String(holder);

			}
			String password = null;
			if (passwordExist) {

				holder = Arrays.copyOfRange(connbufArr, ++i, ++i + 1);

				int passwordLen = Integer.parseInt(DataTypeUtil.bytesToHex(holder), 16);

				holder = Arrays.copyOfRange(connbufArr, i + 1, (i += passwordLen) + 1);

				password = new String(holder);

			}

			byte[] output = { (byte) 0x20, (byte) 0x02, (byte) 0x00, (byte) 0x00};
			ctx.writeAndFlush( Unpooled.copiedBuffer(
					output));
		
		}

		

//		ctx.fireChannelRead(
//				(Unpooled.copiedBuffer(in.toString(CharsetUtil.UTF_8)/* +"\n#passedby"+"1...." */, CharsetUtil.UTF_8)));
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) {
//	 ctx.writeAndFlush(Unpooled.EMPTY_BUFFER);
	//	ctx.fireChannelReadComplete();
///		ctx.flush();
		// .addListener(ChannelFutureListener.CLOSE);
		
		ctx.pipeline().remove("onConnectHandler");
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
	//	super.handlerRemoved(ctx);
		
		System.out.println("handler removed!");
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}

	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		System.err.println("reg channel id:" + ctx.channel().id());
	}

}
