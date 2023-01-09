package com.netty.receiver.tcpClient;

import java.io.IOException;
import java.net.ServerSocket;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javax.xml.bind.DatatypeConverter;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.netty.receiver.ReceiverApplication;
import com.netty.receiver.handler.DataTypeUtil;
import com.netty.receiver.handler.HandshakeHandler;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelOption;
import io.netty.util.CharsetUtil;
import reactor.core.publisher.Mono;
import reactor.netty.Connection;
import reactor.netty.tcp.TcpClient;

public class WriteRifle extends Thread {
	static Scanner scan = new Scanner(System.in);
	Connection connection2;
	TcpClient tcpClient;
	public ConfigurableApplicationContext context;

	static List<String> imeis = new ArrayList<>();

	static Map<String, Connection> imeisXConns = new HashMap<>();

	public static void main(String[] args) {
		loadImies();
		runServer();
	}

	private static void loadImies() {

		long imeiValue = 111111111111110L;
		for (int i = 1; i <= 500; i++) {

			imeis.add("" + (imeiValue++));
		}

	}

	public static void runServer() {

		imeis.forEach(imeiValue -> {

			HandshakeHandler handler = new HandshakeHandler();

			Connection connection = TcpClient.create()
					/* .port(findRandomOpenPortOnAllLocalInterfaces()) */.wiretap(true)
//					.host( /* "51.138.63.42" */ /* "172.75.75.102" */  "92.253.23.156"  /* "20.31.101.11" */
//			/* "localhost" */)
//					.port(9022)
					//
					 .host("8.tcp.ngrok.io").port(18694)
					// .host("3.tcp.ngrok.io").port(21087)
					.handle(handler.handleInbound())
//					         .handle((inbound, outbound) -> {
//					        	 
//					        	 
//					        return outbound.sendString(Mono.just("{\r\n"
//					         		+ "      \"Name\":\"chevrolet chevelle malibu\",\r\n"
//					         		+ "      \"Miles_per_Gallon\":18,\r\n"
//					         		+ "      \"Cylinders\":8,\r\n"
//					         		+ "      \"Displacement\":307,\r\n"
//					         		+ "      \"Horsepower\":130,\r\n"
//					         		+ "      \"Weight_in_lbs\":3504,\r\n"
//					         		+ "      \"Acceleration\":12,\r\n"
//					         		+ "      \"Year\":\"1970-01-01\",\r\n"
//					         		+ "      \"Origin\":\"USA\"\r\n"
//					         		+ "   }")
//					        		 ).neverComplete();
			//
//					         }
//					        		 ) 
//					.doOnConnected(c -> c.addHandlerFirst(new EncoderHandler()))

					.connectNow(Duration.ofMinutes(4));

			imeisXConns.put(imeiValue, connection);

			byte[] connData = DatatypeConverter.parseHexBinary("103a00044d51545404c300f0000f" + imeiToAscii(imeiValue)
					+ "00076465766963657300143132333435363738393a3b3c3d3e3f4041424344");
			connection.outbound().sendByteArray(Mono.just(connData)).then().share().block(Duration.ofSeconds(12));

//			connection.dispose();
//			connection.onDispose().block();

		});

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Set<String> imeisNotConnected = new HashSet<>();

		while (true) {
			imeisXConns.entrySet().forEach(e -> {

				String speed = Car.getSpeedZer0();
				byte[] data = DatatypeConverter.parseHexBinary("32" + "6f" // packet len
						+ "00054243452f440a0f"
				/* payload */ + "02020800" + imeiToHex(e.getKey())// Imei Value
						+ "01" + "5400" + Car.getTime()// time
						+ "0d02b0" + "00c04faa367c0341" + "050340eca4030b30050430d20f030c300500302a30" + "01"// IGN5
				// or
				// Acc
						+ "13" + "00" + "040720" + Car.getStaticFuel()// fuel
						+ "00020000640000650000630000620004082083" + "05"// speed value type
						+ "0020"// speed identifier
						+ speed///////
						+ "0e00d0" + Car.getStaticLat()// lat
						+ Car.getStaticLongitude()// long
						+ speed// speed in gps
						+ "0c0b00006d02c7");

				Connection c = e.getValue();

				if (!c.channel().isActive()) {
					imeisNotConnected.add(e.getKey());
				}

				System.err.println(imeisNotConnected.size());

				e.getValue().outbound().sendByteArray(Mono.just(data)).then().delaySubscription(Duration.ofSeconds(2))
						.subscribe();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});

//			try {
//				Thread.sleep(200000);
//			} catch (InterruptedException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
		}
	}

	public void rstConn() {
		context = ReceiverApplication.context;
		ApplicationArguments args = context.getBean(ApplicationArguments.class);

		Thread thread = new Thread(() -> {
			context.close();
			context = SpringApplication.run(ReceiverApplication.class, args.getSourceArgs());
		});

		thread.setDaemon(false);
		thread.start();

	}

	public void sendData() {
		String speed = Car.getRandomSpeed();
		byte[] data = DatatypeConverter.parseHexBinary("32" + "6f" // packet len
				+ "00054243452f440a0f"
				/* payload */ + "02020800" + "9AF4732DE7120300"// Imei Value
				+ "01" + "5400" + "daf32360"// time
				+ "0d02b0" + "00c04faa367c0341" + "050340eca4030b30050430d20f030c300500302a30" + "01"// IGN5//
				// or//
				// Acc
				+ "13" + "00" + "040720" + Car.getRandomFuel()// fuel
				+ "00020000640000650000630000620004082083" + "05"// speed value type
				+ "0020"// speed identifier
				+ speed + "0e00d0" + Car.getRandomLat()// lat
				+ Car.getRandomLongitude()// long
				+ speed// speed in gps
				+ "0c0b00006d02c7");

		connection2.outbound().sendByteArray(Mono.just(data)).then().subscribe();
	}

	public void sendSub() {

		byte[] subData = DatatypeConverter.parseHexBinary("8219000100143836353230393033393435313239302F4F55544301");
		connection2.outbound().sendByteArray(Mono.just(subData)).then().subscribe();

	}

	public void sendCco() {

		byte[] subData = DatatypeConverter.parseHexBinary(
				"324200054243452f4406a4020208009AF4732DE7120300012900c67873602000c021475052532044313d3120286c6f636b2920636f6d6d616e6420657865637574656426");
		connection2.outbound().sendByteArray(Mono.just(subData)).then().subscribe();

	}

	public void sendCSub(String pid) {
		byte[] subData = DatatypeConverter.parseHexBinary("400200" + pid);
		connection2.outbound().sendByteArray(Mono.just(subData)).then().subscribe();

	}

	public void sendPing() {
		byte[] pingReq = { (byte) 0xC0, (byte) 0x00 };
		connection2.outbound().sendByteArray(Mono.just(pingReq)).then().subscribe();

	}

	public static void startTcpClient() {

		HandshakeHandler handler = new HandshakeHandler();

		Connection connection = TcpClient.create().option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000).wiretap(true)
				.host("localhost").port(9022).handle(handler.handleInbound())
//				         .handle((inbound, outbound) -> {
//				        	 
//				        	 
//				        return outbound.sendString(Mono.just("{\r\n"
//				         		+ "      \"Name\":\"chevrolet chevelle malibu\",\r\n"
//				         		+ "      \"Miles_per_Gallon\":18,\r\n"
//				         		+ "      \"Cylinders\":8,\r\n"
//				         		+ "      \"Displacement\":307,\r\n"
//				         		+ "      \"Horsepower\":130,\r\n"
//				         		+ "      \"Weight_in_lbs\":3504,\r\n"
//				         		+ "      \"Acceleration\":12,\r\n"
//				         		+ "      \"Year\":\"1970-01-01\",\r\n"
//				         		+ "      \"Origin\":\"USA\"\r\n"
//				         		+ "   }")
//				        		 ).neverComplete();
//	
//				         }
//				        		 ) 
				.connectNow();

		// connection.outbound().sendString(Mono.just("looop")).then().subscribe();

		String input = "{\r\n" + "      \"Name\":\"chevrolet chevelle malibu\",\r\n"
				+ "      \"Miles_per_Gallon\":18,\r\n" + "      \"Cylinders\":8,\r\n"
				+ "      \"Displacement\":307,\r\n" + "      \"Horsepower\":130,\r\n"
				+ "      \"Weight_in_lbs\":3504,\r\n" + "      \"Acceleration\":12,\r\n"
				+ "      \"Year\":\"1970-01-01\",\r\n" + "      \"Origin\":\"USA\"\r\n" + "   }";

		while (!input.equalsIgnoreCase("c")) {
			connection.outbound().sendString(Mono.just(input)).then().subscribe();
			input = scan.nextLine();
		}
		connection.dispose();
		connection.onDispose()
		// .block()
		;
		// return "Client";
	}

	public void runData2() {

//		HandshakeHandler handler = new HandshakeHandler();
//
//		Connection connection = TcpClient.create().port(9023).wiretap(true).host( /* "51.138.63.42" */ "localhost")
//				.port(9022).handle(handler.handleInbound())
//
//				.connectNow();
//
//		byte[] connData = { (byte) 0x10, (byte) 0x3A, (byte) 0x00, (byte) 0x04, (byte) 0x4D, (byte) 0x51, (byte) 0x54,
//				(byte) 0x54, (byte) 0x04, (byte) 0xC3, (byte) 0x00, (byte) 0xF0, (byte) 0x00, (byte) 0x0F, (byte) 0x38,
//				(byte) 0x36, (byte) 0x35, (byte) 0x32, (byte) 0x30, (byte) 0x39, (byte) 0x30, (byte) 0x33, (byte) 0x39,
//				(byte) 0x34, (byte) 0x35, (byte) 0x31, (byte) 0x32, (byte) 0x39, (byte) 0x30, (byte) 0x00, (byte) 0x07,
//				(byte) 0x64, (byte) 0x65, (byte) 0x76, (byte) 0x69, (byte) 0x63, (byte) 0x65, (byte) 0x73, (byte) 0x00,
//				(byte) 0x14, (byte) 0x31, (byte) 0x32, (byte) 0x33, (byte) 0x34, (byte) 0x35, (byte) 0x36, (byte) 0x37,
//				(byte) 0x38, (byte) 0x39, (byte) 0x3A, (byte) 0x3B, (byte) 0x3C, (byte) 0x3D, (byte) 0x3E, (byte) 0x3F,
//				(byte) 0x40, (byte) 0x41, (byte) 0x42, (byte) 0x43, (byte) 0x44 };
//
//		connection.outbound().sendByteArray(Mono.just(connData)).then().subscribe();
//		connection.dispose();
//		connection.onDispose().block();

		while (true) {
			String speed = Car.getRandomSpeed();
			byte[] data = DatatypeConverter.parseHexBinary("32" + "6f" // packet len
					+ "00054243452f440a0f"
					/* payload */ + "02020800" + "9AF4732DE7120300"// Imei Value
					+ "01" + "5400" + "daf32360"// time
					+ "0d02b0" + "00c04faa367c0341" + "050340eca4030b30050430d20f030c300500302a30" + "01"// IGN5// Acc
					+ "13" + "00" + "040720" + Car.getRandomFuel()// fuel
					+ "00020000640000650000630000620004082083" + "05"// speed value type
					+ "0020"// speed identifier
					+ speed + "0e00d0" + Car.getRandomLat()// lat
					+ Car.getRandomLongitude()// long
					+ speed// speed in gps
					+ "0c0b00006d02c7");

			connection2.outbound().sendByteArray(Mono.just(data)).then().subscribe();
			try {
				Thread.sleep(30000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public static Integer findRandomOpenPortOnAllLocalInterfaces() {
		try (ServerSocket socket = new ServerSocket(0);) {
			return socket.getLocalPort();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

//	public static String imeiToHex(String imei) {
//		try {
//			return Long.toHexString(Unpooled
//					.copiedBuffer(DatatypeConverter.parseHexBinary("000" + Long.toHexString(Long.parseLong(imei))))
//					.readLongLE()) + "";
//		} catch (IllegalArgumentException ex) {
//			String hex = Long.toHexString(Unpooled
//					.copiedBuffer(DatatypeConverter.parseHexBinary("0000" + Long.toHexString(Long.parseLong(imei))))
//					.readLongLE()) + "";
//			if (hex.length() == 15)
//				return "0" + hex;
//			else
//				return hex;
//		}
//
//	}

	public static String imeiToHex(String imei) {
		try {
			String hex = Long.toHexString(Unpooled
					.copiedBuffer(DatatypeConverter.parseHexBinary("000" + Long.toHexString(Long.parseLong(imei))))
					.readLongLE()) + "";

			int dif = 16 - hex.length();

			if (dif != 0) {
				for (int i = 0; i < dif; i++) {
					hex = "0" + hex;
				}
			}
			return hex;

		} catch (IllegalArgumentException ex) {
			String hex = Long.toHexString(Unpooled
					.copiedBuffer(DatatypeConverter.parseHexBinary("0000" + Long.toHexString(Long.parseLong(imei))))
					.readLongLE()) + "";
//			if (hex.length() == 15)
//				return "0" + hex;
//			

			int dif = 16 - hex.length();

			if (dif != 0) {

				for (int i = 0; i < dif; i++) {
					hex = "0" + hex;
				}
			}

			return hex;
		}

	}

	public static String imeiToAscii(String imei) {

		return DataTypeUtil.bytesToHex(imei.getBytes(CharsetUtil.US_ASCII));

	}

	@Override
	public void run() {
		loadImies();
		runServer();
	}

}
