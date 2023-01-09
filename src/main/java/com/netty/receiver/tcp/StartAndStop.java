package com.netty.receiver.tcp;

import reactor.netty.DisposableServer;
import reactor.netty.tcp.TcpServer;

public class StartAndStop {

	public static void main(String[] args) {
		DisposableServer server =
				TcpServer.create()   
				         .bindNow(); 

		server.onDispose().log()
		      .block();
	}

}
