package com.netty.receiver.tcp;

import java.io.File;

import io.netty.handler.ssl.SslContextBuilder;
import reactor.netty.DisposableServer;
import reactor.netty.tcp.TcpServer;

public class Ssl {

	public static void main(String[] args) {
		File cert = new File("certificate.crt");
		File key = new File("private.key");

		SslContextBuilder sslContextBuilder = SslContextBuilder.forServer(cert, key);

		DisposableServer server =
				TcpServer.create()
				         .secure(spec -> spec.sslContext(sslContextBuilder))
				         .bindNow();

		server.onDispose()
		      .block();
	}
	
}
