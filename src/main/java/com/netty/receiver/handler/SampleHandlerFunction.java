package com.netty.receiver.handler;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerRequest.Headers;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.netty.receiver.tcpClient.Write;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class SampleHandlerFunction {

	public Mono<ServerResponse> flux(ServerRequest serverRequest) {

		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(Flux.just(1, 2, 3, 4).log(),
				Integer.class);

	}

	public Mono<ServerResponse> mono(ServerRequest serverRequest) {

		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
				.body(Mono.just("{\r\n" + "      \"Name\":\"chevrolet chevelle malibu\",\r\n"
						+ "      \"Miles_per_Gallon\":18,\r\n" + "      \"Cylinders\":8,\r\n"
						+ "      \"Displacement\":307,\r\n" + "      \"Horsepower\":130,\r\n"
						+ "      \"Weight_in_lbs\":3504,\r\n" + "      \"Acceleration\":12,\r\n"
						+ "      \"Year\":\"1970-01-01\",\r\n" + "      \"Origin\":\"USA\"\r\n" + "   }").log(),
						String.class);

	}

	public Mono<ServerResponse> client(ServerRequest serverRequest) {

//	Write.startTcpClient();
//	
//		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(
//				Mono.just("Client started").log(), String.class
//					);

		Headers data = serverRequest.headers();
		print(data.header("uda").toString());
		//data.flatMap(this::print);
		return ServerResponse.ok().build();

	}
	
	public Mono<Void> print(String str) {
		
		System.out.println(str);
		
		return Mono.empty();
	}
}
