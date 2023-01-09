package com.netty.receiver.tcpClient;

import javax.xml.bind.DatatypeConverter;

import com.netty.receiver.handler.HandshakeHandler;

import reactor.core.publisher.Mono;
import reactor.netty.Connection;
import reactor.netty.tcp.TcpClient;

public class Write3 extends Thread {

	@Override
	public void run() {
		runServer4();
	}

	public void runServer4() {

		HandshakeHandler handler = new HandshakeHandler();

		TcpClient tcpClient4 = TcpClient.create().port(9025).wiretap(true).host("localhost").port(9022)
				.handle(handler.handleInbound());
		Connection connection4 = tcpClient4.connectNow();

		byte[] connData = { (byte) 0x10, (byte) 0x3A, (byte) 0x00, (byte) 0x04, (byte) 0x4D, (byte) 0x51, (byte) 0x54,
				(byte) 0x54, (byte) 0x04, (byte) 0xC3, (byte) 0x00, (byte) 0xF0, (byte) 0x00, (byte) 0x0F, 
				(byte) 0x38, (byte) 0x36, (byte) 0x35, (byte) 0x32, (byte) 0x30, (byte) 0x39, (byte) 0x30, (byte) 0x33, (byte) 0x39,
				(byte) 0x34, (byte) 0x35, (byte) 0x31, (byte) 0x32, (byte) 0x39, (byte) 0x32, 
				(byte) 0x00, (byte) 0x07,
				(byte) 0x64, (byte) 0x65, (byte) 0x76, (byte) 0x69, (byte) 0x63, (byte) 0x65, (byte) 0x73, (byte) 0x00,
				(byte) 0x14, (byte) 0x31, (byte) 0x32, (byte) 0x33, (byte) 0x34, (byte) 0x35, (byte) 0x36, (byte) 0x37,
				(byte) 0x38, (byte) 0x39, (byte) 0x3A, (byte) 0x3B, (byte) 0x3C, (byte) 0x3D, (byte) 0x3E, (byte) 0x3F,
				(byte) 0x40, (byte) 0x41, (byte) 0x42, (byte) 0x43, (byte) 0x44 };

		connection4.outbound().sendByteArray(Mono.just(connData)).then().subscribe();
		runData4(connection4);
		// connection2.onDispose().block();

	}

	void runData4(Connection connection4) {

		while (true) {
			String speed = Car.getRandomSpeed();
			byte[] data = DatatypeConverter.parseHexBinary("32" + "6f" // packet len
					+ "00054243452f440a0f"
					/* payload */ + "02020800" + "9CF4732DE7120300"// Imei Value
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

			connection4.outbound().sendByteArray(Mono.just(data)).then().subscribe();
			try {
				Thread.sleep(30000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
