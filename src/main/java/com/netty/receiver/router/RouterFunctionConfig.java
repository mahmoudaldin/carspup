package com.netty.receiver.router;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.netty.receiver.handler.SampleHandlerFunction;

@Configuration
public class RouterFunctionConfig {

	@Bean
	public RouterFunction<ServerResponse> route(SampleHandlerFunction sampleHandlerFunction) {

		return RouterFunctions
				.route(GET("/functional/flux").and(accept(MediaType.APPLICATION_JSON)), sampleHandlerFunction::flux)
				.andRoute(GET("/functional/mono").and(accept(MediaType.APPLICATION_JSON)), sampleHandlerFunction::mono)
				.andRoute(POST("/functional/client").and(accept(MediaType.APPLICATION_JSON)), sampleHandlerFunction::client);

	}

}
