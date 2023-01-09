package com.netty.receiver.tcp;

import com.netty.receiver.handler.ServerHandler;

import reactor.netty.DisposableServer;
import reactor.netty.tcp.TcpServer;

public class CallBacks_OnConn {

	public static void main(String[] args) {
		DisposableServer server = TcpServer.create().wiretap(true).doOnConnection(
//	(c) -> System.out.println("channel:" + c.toString())							
				
				//conn -> conn.addHandler(new ReadTimeoutHandler(5, TimeUnit.SECONDS))
				conn -> conn.addHandler(new ServerHandler())
				
				
				
				).bindNow();

		server.onDispose().block();
	}

}
