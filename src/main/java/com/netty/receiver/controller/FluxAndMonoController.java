package com.netty.receiver.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.netty.receiver.tcpClient.Write;
import com.netty.receiver.tcpClient.WriteRifle;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class FluxAndMonoController {

	@Autowired
	Write server;

	@GetMapping("/flux")
	public Flux<Integer> returnFlux() {
		return Flux.just(1, 2, 3, 4).delayElements(Duration.ofSeconds(1)).log();
	}

	@GetMapping(value = "/fluxstream", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Flux<Integer> returnFluxStream() {
		return Flux.just(1, 2, 3, 4).delayElements(Duration.ofSeconds(1)).log();
	}

	@GetMapping("/mono")
	public Mono<Integer> returnMono() {
		return Mono.just(1).log();
	}

	@PostMapping(value = "/data")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Mono<Void> data() {
		try {
			server.sendData();
		} catch (Exception e) {

			return Mono.error(e);
		}

		return Mono.empty();
	}

	@PostMapping(value = "/restartConn")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Mono<Void> restartConn() {
		try {
			server.rstConn();
		} catch (Exception e) {

			return Mono.error(e);
		}

		return Mono.empty();
	}

	@PostMapping(value = "/sub")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Mono<Void> sub() {
		try {
			server.sendSub();
		} catch (Exception e) {

			return Mono.error(e);
		}
		return Mono.empty();
	}

	@PostMapping(value = "/cco")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Mono<Void> cco() {
		try {
			server.sendCco();
		} catch (Exception e) {

			return Mono.error(e);
		}
		return Mono.empty();
	}

	@PostMapping(value = "/ping")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Mono<Void> ping() {
		try {
			server.sendPing();
		} catch (Exception e) {

			return Mono.error(e);
		}
		return Mono.empty();
	}

	@PostMapping(value = "/cSub")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Mono<Void> cSub(@RequestHeader("pid") String pid) {
		try {
			server.sendCSub(pid);
		} catch (Exception e) {

			return Mono.error(e);
		}
		return Mono.empty();
	}

	@PostMapping(value = "/rifle1")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Mono<Void> wr1() {
		try {
			WriteRifle.main(null);
		} catch (Exception e) {

			return Mono.error(e);
		}
		return Mono.empty();
	}

}
