package com.netty.receiver.tcpClient;

import reactor.netty.Connection;
import reactor.netty.tcp.TcpClient;

public class ReadC {

	public static void main(String[] args) {
		Connection connection =
				TcpClient.create().wiretap(true)
				         .host("localhost")
				         .port(9022)
				         .handle((inbound, outbound) -> inbound.receive().then()) 
				         .connectNow();

		connection.onDispose()
		          .block();
	}
	
}
