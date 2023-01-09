//package com.netty.receiver.tcp;
//
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//import io.netty.buffer.ByteBuf;
//import io.netty.buffer.Unpooled;
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.channel.ChannelInboundHandlerAdapter;
//import io.netty.channel.ChannelPipeline;
//import io.netty.handler.logging.LogLevel;
//import io.netty.handler.logging.LoggingHandler;
//import reactor.netty.DisposableServer;
//import reactor.netty.tcp.TcpServer;
//
//@SpringBootApplication
//public class RecieverDemoApplication extends ChannelInboundHandlerAdapter {
//
//	public static void main(String[] args) {
//		RecieverDemoApplication main = new RecieverDemoApplication();
//		main.init();
//	}
//
//	private void init() {
//		DisposableServer server = TcpServer.create().port(9022).wiretap(true)
//				.handle((inbound, outbound) -> inbound.receive().asString().then()).doOnConnection(c -> {
//					c.addHandlerFirst(new LoggingHandler(LogLevel.INFO));
//					c.addHandler(RecieverDemoApplication.this);
//				})
//				.bindNow();
//
//		server.onDispose().block();
//	}
//
//	private void addPipes(ChannelPipeline pipeline) {
//		pipeline.addFirst(new LoggingHandler(LogLevel.INFO));
//		pipeline.addLast(this);
//	}
//
//	@Override
//	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//		ByteBuf rcvd = (ByteBuf) msg;
//		byte[] byt = new byte[rcvd.writerIndex()];
//		rcvd.readBytes(byt);
//
//		String rcvdMsg = new String(byt);
//		System.err.println(rcvdMsg);
//
//		ByteBuf msgSend = Unpooled.buffer("Server Response".length());
//		msgSend.writeBytes("Server Response".getBytes());
//	}
//
//	@Override
//	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//		ctx.flush();
//		System.out.println("Send message complete");
//	}
//
//	@Override
//	public void channelActive(ChannelHandlerContext ctx) throws Exception {
//		System.out.println("channel Active ");
//	}
//
//	@Override
//	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//		System.out.println("ExceptionHappen");
//		cause.printStackTrace();
//		ctx.close();
//	}
//}
