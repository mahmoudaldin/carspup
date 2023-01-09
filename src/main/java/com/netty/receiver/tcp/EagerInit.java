package com.netty.receiver.tcp;

import reactor.netty.DisposableServer;
import reactor.netty.tcp.TcpServer;

public class EagerInit {

	public static void main(String[] args) {
		TcpServer tcpServer =
				TcpServer.create()
				         .handle((inbound, outbound) -> inbound.receive().then());

		tcpServer.warmup() 
		         .block();

		DisposableServer server = tcpServer.bindNow();

		server.onDispose()
		      .block();
	}

}
