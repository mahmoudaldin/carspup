package com.netty.receiver.handler;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.NettyInbound;
import reactor.netty.NettyOutbound;

public class InOutHandler {

	public Mono<Void> onRead(NettyInbound inbound, NettyOutbound outbound) {
	

		inbound.receive().asByteBuffer().subscribe(in -> System.out.println(in.toString()));
		
		return inbound.receive().then();
	}
	
}
