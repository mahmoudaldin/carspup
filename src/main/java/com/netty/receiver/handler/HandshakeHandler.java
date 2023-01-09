package com.netty.receiver.handler;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.NettyInbound;
import reactor.netty.NettyOutbound;

import java.util.function.BiFunction;

@Slf4j
@Component
@AllArgsConstructor
public class HandshakeHandler {

	public BiFunction<NettyInbound, NettyOutbound, Publisher<Void>> handleInbound() {

		return (nettyInbound, nettyOutbound) -> {

			return nettyInbound.receive().asByteArray().flatMap(bytes -> {
				String hexData = DataTypeUtil.bytesToHex(bytes);
				System.out.println("Server received HEX payload: " + hexData);
				String utf8Data = DataTypeUtil.hexToStringUtf8(hexData);
				System.out.println("Server received UTF-8 payload:" + utf8Data);
				if (hexData.equalsIgnoreCase("6B")) {
					nettyOutbound.sendString(Flux.just("S", "SB", "RS")).then().subscribe();
				} else if (utf8Data.equalsIgnoreCase("x")) {
					nettyOutbound.withConnection(cnn -> cnn.dispose());
					nettyOutbound.withConnection(cnn -> cnn.channel().connect(cnn.channel().remoteAddress(), cnn.channel().localAddress()));
				} else if (utf8Data.contains("rest")) {
					final String uri = "http://localhost:8080/functional/client";

					RestTemplate restTemplate = new RestTemplate();
				//	String result = restTemplate.getForObject(uri, String.class);

					 HttpHeaders headers = new HttpHeaders();
					    headers.setContentType(MediaType.APPLICATION_JSON);
					    headers.add("uda", utf8Data);
					    HttpEntity<String> request = 
					    	      new HttpEntity<String>(null, headers);
					    	    
					restTemplate.postForEntity(uri, request ,String.class);
					
					//System.out.println("reeest: " + result);
				}

				return Mono.empty();
			}).onErrorResume(e -> {
				System.err.println("Error occurred: " + e.getMessage());
				return Mono.empty();
			}).then();
		};
	}

}
