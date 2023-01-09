package com.netty.receiver.tcpClient;

import javax.xml.bind.DatatypeConverter;

import com.netty.receiver.handler.HandshakeHandler;

import reactor.core.publisher.Mono;
import reactor.netty.Connection;
import reactor.netty.tcp.TcpClient;

public class Write6 extends Thread {

	@Override
	public void run() {
		runServer3();
	}

	public void runServer3() {

		HandshakeHandler handler = new HandshakeHandler();

		TcpClient tcpClient3 = TcpClient.create().port(9027).wiretap(true).host("localhost").port(9022)
				.handle(handler.handleInbound());
		Connection connection3 = tcpClient3.connectNow();

		byte[] connData =  DatatypeConverter.parseHexBinary("103a00044d51545404c300f0000f"
				+ "383631323330303438373533303137"
				+ "00076465766963657300143132333435363738393a3b3c3d3e3f4041424344");

		connection3.outbound().sendByteArray(Mono.just(connData)).then().subscribe();
		runData3(connection3);
		// connection2.onDispose().block();

	}

	void runData3(Connection connection3) {

		while (true) {
			String speed = Car.getRandomSpeed();
			byte[] data = DatatypeConverter.parseHexBinary("32" + "6f" // packet len
					+ "00054243452f440a0f"
					/* payload */ + "02020800" + "793520BF480F0300"// Imei Value//9CF4732DE7120300 for 4
					+ "01" + "5400" + "daf32360"// time
					+ "0d02b0" + "00c04faa367c0341" + "050340eca4030b30050430d20f030c300500302a30" 
					+ "01"// IGN5//
					// or//
					// Acc
					+ "13" + "00"
					+ "040720" + Car.getRandomFuel()// fuel
					+ "00020000640000650000630000620004082083" + "05"// speed value type
					+ "0020"// speed identifier
					+ speed + "0e00d0" + Car.getRandomLat()// lat
					+ Car.getRandomLongitude()// long
					+ speed// speed in gps
					+ "0c0b00006d02c7");

			connection3.outbound().sendByteArray(Mono.just(data)).then().subscribe();
			try {
				Thread.sleep(30000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
