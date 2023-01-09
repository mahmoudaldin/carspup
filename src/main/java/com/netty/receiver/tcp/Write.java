package com.netty.receiver.tcp;

import reactor.core.publisher.Flux;
import reactor.netty.DisposableServer;
import reactor.netty.tcp.TcpServer;

public class Write {

	public static void main(String[] args) {
		DisposableServer server =
				TcpServer.create()
				         .handle((inbound, outbound) -> outbound.sendString(Flux.just("hello ","world"))) 
				         .bindNow();

		server.onDispose()
		      .block();
	}

}
