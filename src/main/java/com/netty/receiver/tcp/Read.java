package com.netty.receiver.tcp;

import com.netty.receiver.handler.DecoderHandler;
import com.netty.receiver.handler.ServerHandler;
import com.netty.receiver.handler.ServerHandler2;
import com.netty.receiver.handler.ServerHandler3;
import com.netty.receiver.handler.ServerOutHandler;
import com.netty.receiver.handler.ServerOutHandler2;

import reactor.netty.DisposableServer;
import reactor.netty.tcp.TcpServer;

public class Read {

//	public static void main(String[] args) {
//
//		HandshakeHandler handler = new HandshakeHandler();
//		
//	     DisposableServer server = TcpServer.create()
//	    		// .option(ChannelOption.AUTO_CLOSE, false)
//	    		 .port(9022).wiretap(true)
//	    		 //.handle((inbound, outbound) -> outbound.sendString(Flux.just("hello ","world r")).neverComplete())
//	    		// .handle(handler.handleInbound())
//	         .doOnConnection(channel ->
//	             channel.addHandlerFirst(new ServerHandler())
//	                    )
//
////	    		 .handle(
////	    				 (inbound, outbound) -> inbound.receive().asByteBuffer().concatMap(obj -> {
////	    		    
////	    			 System.out.println("buffff:"+ obj.toString());
////	    			 
////	    			 return outbound.neverComplete();
////	    		  })
////	    				 )
//	    
//	    		// .handle((i, o) -> o.sendString(Flux.just("aaa")))
//	    		 
//	    	//	   .handle((inbound, outbound) -> inbound.receive().then().doOnSubscribe(c ->  System.out.println("buffff:"+c.toString())))
//	    		 .bindNow();
//	     
//	    		 
//	     
//	     
////	                .handle((inbound, outbound) -> inbound.receive().then()).bindNow();
//
//	    		 
//	        server.onDispose().block();
//	}

	public static void main(String[] args) {

		DisposableServer server = TcpServer.create().port(9022).wiretap(true)
				.handle((inbound, outbound) -> inbound.receive().asString().then()).doOnConnection(c -> {
					// c.addHandlerFirst(new LoggingHandler(LogLevel.INFO));
					c.addHandlerLast("onConnectHandler",new ServerHandler());
					c.addHandlerLast("payloadHandler",new ServerHandler2());
					c.addHandlerLast("pingHandler",new ServerHandler3());
					c.addHandlerFirst(new ServerOutHandler());
					c.addHandlerFirst(new ServerOutHandler2());
					
					
				}).bindNow();

		server.onDispose().block();

	}
}
