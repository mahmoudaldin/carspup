package com.netty.receiver.tcpClient;

import java.util.Scanner;

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

public class Write_Ruptela extends Thread {
	static Scanner scan = new Scanner(System.in);
	Connection connection2;
	TcpClient tcpClient;
	public ConfigurableApplicationContext context;

	public static void main(String[] args) {
//		System.err.println(imeiToHex("860186054099482"));

		runServer();
	}

	public static void runServer() {

		HandshakeHandler handler = new HandshakeHandler();

		Connection connection = TcpClient.create()/* .port(9023) */.wiretap(true)
				.host( /* "51.138.63.42" */ /* "92.253.23.156" */ /* "https://collector.iyelo.com" *//*
																										 * "172.75.75.102"
																										 */

						/* "20.31.101.11" */
						/* "212.35.71.156" */ "3.134.125.175" 
				/* "localhost" */

				)
				.port(/* 9022 *//* 16804 */ 9024 )//
				// .host("3.tcp.ngrok.io").port(21087)
				.handle(handler.handleInbound())
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
//				.doOnConnected(c -> c.addHandlerFirst(new EncoderHandler()))

				.connectNow();

		// connection.outbound().sendString(Mono.just("looop")).then().subscribe();

		// String input =
		// "103A00044D51545404C200F0000F38363532303930333934353132393000076465766963657300143132333435363738393A3B3C3D3E3F4041424344";

		byte[] connData = { (byte) 0x10, (byte) 0x3A, (byte) 0x00, (byte) 0x04, (byte) 0x4D, (byte) 0x51, (byte) 0x54,
				(byte) 0x54, (byte) 0x04, (byte) 0xC3, (byte) 0x00, (byte) 0xF0, (byte) 0x00, (byte) 0x0F, (byte) 0x38,
				(byte) 0x36, (byte) 0x35, (byte) 0x32, (byte) 0x30, (byte) 0x39, (byte) 0x30, (byte) 0x33, (byte) 0x39,
				(byte) 0x34, (byte) 0x35, (byte) 0x31, (byte) 0x32, (byte) 0x39, (byte) 0x30, (byte) 0x00, (byte) 0x07,
				(byte) 0x64, (byte) 0x65, (byte) 0x76, (byte) 0x69, (byte) 0x63, (byte) 0x65, (byte) 0x73, (byte) 0x00,
				(byte) 0x14, (byte) 0x31, (byte) 0x32, (byte) 0x33, (byte) 0x34, (byte) 0x35, (byte) 0x36, (byte) 0x37,
				(byte) 0x38, (byte) 0x39, (byte) 0x3A, (byte) 0x3B, (byte) 0x3C, (byte) 0x3D, (byte) 0x3E, (byte) 0x3F,
				(byte) 0x40, (byte) 0x41, (byte) 0x42, (byte) 0x43, (byte) 0x44 };

//		 connData = DatatypeConverter.parseHexBinary("103a00044d51545404c300f0000f" + imeiToAscii("860260050929578")
//		+ "00076465766963657300143132333435363738393a3b3c3d3e3f4041424344");

		// 103300044d51545404c200f0000f383631323330303434393434373834000a6263655f646576696365000a736563726574776f7264

		String dataWithoutLength = imeiToHex("860186054099482") // IMEI
				+ "44" // Command ID(type)
				+ "00" // NO. of records left in flash
				+ "01" // NO. of records
//				+ "631F913D" // Time
				+ RuptelaCar.getTime() + "00" // Time stamp extension: an extra byte to separate records with same time
												// stamp. If some records have same time stamp
				+ "00"// Record extension
				+ "00"// Priority

				+ RuptelaCar.getRandomLongitude()
				// + "20DDE53A"//Longitude
//				+ "0EE3ED84"//Latitude
				+ RuptelaCar.getRandomLat() + "0042"// Altitude
				+ "27C4"// Angle
				+ "13"// Satellites
//				+ "0000"//Speed
				+ RuptelaCar.getSpeedZer0() + "09"// HDOP
				+ "0007"// Event ID
				// Record Header End

				// Record Body Start

				+ "0C"// NO. of 1Byte Sensors

				+ "0005" // IGNITION ID
				+ RuptelaCar.getRandomACC()// IGNITION
				+ "001C" // SensorID
				+ "01" // Sensor
				+ "00B0"// SensorID
				+ "00"// Sensor
				+ "0058"// SensorID
				+ "00"// Sensor
				+ "001B"// SensorID
				+ "19"// Sensor
				+ "0006"// SensorID
				+ "2A"// Sensor
				+ "00AD"// MOVEMENT ID
				+ "00"// MOVEMENT
				+ "0020"// SensorID
				+ "27"// Sensor
				+ "0031"// SensorID
				+ "00"// Sensor
				+ "0032"// SensorID
				+ "06"// Sensor
				+ "0033"// SensorID
				+ "EE"// Sensor
				+ "0088"// hardAcceleration ID
				+ "00"// hardAcceleration

				+ "05"// NO. of 2Bytes Sensors
				+ "001E"// DEVICE_BATTERY ID
//				+ "1010"// DEVICE_BATTERY
				+ RuptelaCar.getRandomDeviceBattery() + "001D"// EXTERNAL_BATTERY ID
//				+ "65FC"// EXTERNAL_BATTERY
				+ RuptelaCar.getRandomExternalBattery() + "0089"// SensorID
				+ "0000"// Sensor
				+ "004E"// Temperature sensor0 ID
//				+ "00ED"// Temperature sensor0
				+ RuptelaCar.getRandomTemp()
				+ "00CD" + RuptelaCar.getRandomFuel()
				
				+ "03"// NO. of 4Bytes Sensors

				+ "0030"// SensorID
				+ "00000000"// Sensor
				+ "0096"// SensorID
				+ "0000A5A2"// Sensor
				+ "0041" // ODOMETER ID
				+ "193F9036"// ODOMETER
				+ "00"// NO. of 8Bytes Sensors
		;

//		System.out.println(dataWithoutLength);
		String allData = /* RuptelaCar.countBytes(dataWithoutLength) */  "00b0"  + dataWithoutLength
				+ RuptelaCar.crc16(dataWithoutLength);
		connData = DatatypeConverter.parseHexBinary(allData);
		/** BCE Device Type */
//		connData = DatatypeConverter
//				.parseHexBinary(
//						"dd03d807fa1603004002a5cd287708f6e0ab40ab8d3a423d9bc641331b20ab0210846f42d1a0a42eaf00950fa40103c504b118005228e708f6e0ab40f48e3a42549cc641201c20a8022ef20b43d1a00730c400910fa40103c504b1180050280709f6e0ab40238f3a42169cc641101c3ea802ca72bb41d1a0d92ec400950fa40103c504b1180050282709f6e0ab405b8f3a42649bc641211b4ba902af362e42d1a08330c400910fa40103e3048a04003f28a708f6e0ab4010903a42cb98c641311c4cae0251141d43d1a0b62eaf00910fa40103e3048a04003f28e708f6e0ab40bc903a426396c641431c4db402816a12439180c72eaf008e0fa40103e3048a04003d280709f6e0ab4014913a420f95c641461c4cb402f6f59e429180c72ec400910fa40103e3048a04003d288709f6e0ab4025923a42fe90c641351c4cb002c85a7443d180d633c400910fa40103e3048a04004b28a709f6e0ab404f923a426190c641251c4baf0291d51342d180d633c400910fa40103e3048a04004b28e709f6e0ab406c923a42e88fc641111c4bae027abce041d180b62ec400910fa40103e3048a04004b28170af6e0ab4074923a42d18fc641021c47af0208e1e53fd0803a2ec400910fa40103c504b118003b28270af6e0ab4074923a42d08fc641001c47af0200000000d080922eaf00910fa40103c504b118003b28370bf6e0ab4074923a42d08fc641001c47af02000000009080d92eaf00910fa40103c504b118004428370cf6e0ab4074923a42d08fc641001a47af02000000009080922eaf00910fa40103c504b118003972"
//						//"48b07c25121003007301a5522827ed03e0ab40e612244242dbf741001c382302000000008080242c0201a00fa40103f90ec08200462887f603e0ab40e612244242dbf741001c3823020000000080803f2c0201a00fa40103f90ec082004628e7ff03e0ab40e612244242dbf741001c382302000000008080242c0201a00fa40103f90ec082004728470904e0ab40e612244242dbf741001c382302000000008080502c0201a00fa40103f90ec082004728a71204e0ab40e612244242dbf741001c382302000000008080482c0201a00fa40103f90ec082004728071c04e0ab40e612244242dbf741001c3823020000000080801b2cf700a00fa40103f90ec082004628672504e0ab40e612244242dbf741001c382302000000008080592c0201a00fa40103f90ec082004728c72e04e0ab40e612244242dbf741001b382302000000008080362c0201a00fa40103f90ec082004628273804e0ab40e612244242dbf741001c382302000000008080592c0201a00fa40103f90ec0820047bb"
//						
//						
//						);

		connection.outbound().sendByteArray(Mono.just(connData)).then().subscribe();

		String input = "";
		// while(!scan.nextLine().equalsIgnoreCase("c"));
		while (!input.equalsIgnoreCase("c")) {

			input = scan.nextLine();
			if (input.equals("data")) {

				String payloadDataWithoutLength = imeiToHex("860186054099482") // IMEI
						+ "44" // Command ID(type)
						+ "00" // NO. of records left in flash
						+ "01" // NO. of records
//						+ "631F913D" // Time
						+ RuptelaCar.getTime() + "00" // Time stamp extension: an extra byte to separate records with
														// same time stamp. If some records have same time stamp
						+ "00"// Record extension
						+ "00"// Priority

						+ RuptelaCar.getRandomLongitude()
						// + "20DDE53A"//Longitude
//						+ "0EE3ED84"//Latitude
						+ RuptelaCar.getRandomLat() + "0042"// Altitude
						+ "27C4"// Angle
						+ "13"// Satellites
//						+ "0000"//Speed
						+ RuptelaCar.getSpeedZer0() + "09"// HDOP
						+ "0007"// Event ID
						// Record Header End

						// Record Body Start

						+ "0C"// NO. of 1Byte Sensors

						+ "0005" // IGNITION ID
						+ RuptelaCar.getRandomACC()// IGNITION
						+ "001C" // SensorID
						+ "01" // Sensor
						+ "00B0"// SensorID
						+ "00"// Sensor
						+ "0058"// SensorID
						+ "00"// Sensor
						+ "001B"// SensorID
						+ "19"// Sensor
						+ "0006"// SensorID
						+ "2A"// Sensor
						+ "00AD"// MOVEMENT ID
						+ "00"// MOVEMENT
						+ "0020"// SensorID
						+ "27"// Sensor
						+ "0031"// SensorID
						+ "00"// Sensor
						+ "0032"// SensorID
						+ "06"// Sensor
						+ "0033"// SensorID
						+ "EE"// Sensor
						+ "0088"// hardAcceleration ID
						+ "00"// hardAcceleration

						+ "05"// NO. of 2Bytes Sensors
						+ "001E"// DEVICE_BATTERY ID
//						+ "1010"// DEVICE_BATTERY
						+ RuptelaCar.getRandomDeviceBattery() + "001D"// EXTERNAL_BATTERY ID
//						+ "65FC"// EXTERNAL_BATTERY
						+ RuptelaCar.getRandomExternalBattery() + "0089"// SensorID
						+ "0000"// Sensor
						+ "004E"// Temperature sensor0 ID
//						+ "00ED"// Temperature sensor0
						+ RuptelaCar.getRandomTemp() + "00CD" + RuptelaCar.getRandomFuel()

						+ "03"// NO. of 4Bytes Sensors

						+ "0030"// SensorID
						+ "00000000"// Sensor
						+ "0096"// SensorID
						+ "0000A5A2"// Sensor
						+ "0041" // ODOMETER ID
						+ "193F9036"// ODOMETER
						+ "00"// NO. of 8Bytes Sensors
				;

//				System.out.println(dataWithoutLength);
				String allPayloadData = /* RuptelaCar.countBytes(dataWithoutLength) */ "00b0" + payloadDataWithoutLength
						+ RuptelaCar.crc16(payloadDataWithoutLength);

				byte[] data = DatatypeConverter.parseHexBinary(allPayloadData);
				connection.outbound().sendByteArray(Mono.just(data)).then().subscribe();
			} else if (input.equals("data2")) {

				byte[] data = DatatypeConverter.parseHexBinary("32fe0200054243452f44006f02020800" + "9AF4732DE7120300"// imeiValue
						+ "017500848735630d02b08012f239ef097f410504305710030b300303400500309236030c30031d300c01a0c16fd4be011300008100006400008900006300006000006500010200030020008b00006700030720006200007f00030920008800030820008c000e00d0edd7ab4167bb1c420000000000000000017500a38735630d02b08012f239ef097f410504304310030b30030340050030c736030c30031d300c01a0c16fd4be011300008100006400008900006300006000006500010200030020008b00006700030720006200007f00030920008800030820008c000e00d0edd7ab4167bb1c420000000000000000017500bc8735630d02b08012f239ef097f410504304310030b300303400500307635030c30031d300c01a0c16fd4be001300008100006400008900006300006000006500010200030020008b00006700030720006200007f00030920008800030820008c000e00d0edd7ab4167bb1c42000000000000000034");
//				byte[] data = DatatypeConverter.parseHexBinary(
//						"326f00054243452f440a0f020208009af4732de712030001540041a903630d02b000c04faa367c0341050340eca4030b30050430d20f030c300500"
//				 +
//				 "302a300113000407203c000200006400006500006300006200040820830500201e000e00d00ccccd41fc7836421e000c0b00006d02c7"
//				);
//
				connection.outbound().sendByteArray(Mono.just(data)).then().subscribe();

			} else if (input.equals("data4")) {

				// "32950100054243452f440522020208007aed6d08010f0300017c00bcec0d630d02b0fc230d83de488c410504305d10030b3006034090628a000500300133050c30dc03031d300c01a066149abf001300008100006400008900006300006000006500000200030020018b000067000407200b006200007f00030920008800030820008c000e00d08784c3416a1539420000070c0000af02e1"
//				byte[] data = DatatypeConverter.parseHexBinary(
//						"329b0800054243452f4400d1020208000df36c08010f0300017c00c0fc0d630d02b01827a83146868b410504304a10030b30060340888ea7000500304934050c30cc1b031d300c01a0a6545bbf011300008100006400008900006300006000006500010200030020008b0000670004072018006200007f00030920008800030820008c000e00d0845ec541afdf3a420000070c00005b02017f00c3fc0d630d02b01827a83146868b410504304b10040b30b3060340888ea7000500307534050c30881b041d30030c01a0a6545bbf01130000810000640000890000630000600000650001020004002002008b0000670004072018006200007f00030920008800030820008c000e00d0805ec541b0df3a420200070cb3005c02017c00dbfc0d630d02b078079b3849868b410504304b10030b30060340508fa7000500304034050c30e81c031d300c01a0a6545bbf011300008100006400008900006300006000006500010200030020008b000067000407201a006200007f00030920008800030820008c000e00d0df5cc541a8df3a420000070c00005902017f00dcfc0d630d02b078079b3849868b410504304a10040b30b7060340518fa700050030a134050c30141c041d30030c01a0a6545bbf01130000810000640000890000630000600000650001020004002001008b000067000407201a006200007f00030920008800030820008c000e00d0dd5cc541a8df3a420100070cb7005802017c00f1fc0d630d02b0d8dd699b4a868b410504304a10030b30060340508fa7000500306334050c30181c031d300c01a0a6545bbf011300008100006400008900006300006000006500010200030020008b000067000407201a006200007f00030920008800030820008c000e00d0095cc541a3df3a420000080c00005702017f00f3fc0d630d02b0d8dd699b4a868b410504304610040b30b9060340518fa7000500305234050c30441b041d30040c01a0a6545bbf01130000810000640000890000630000600000650001020004002002008b0000670004072017006200007f00030920008800030820008c000e00d0055cc541a3df3a420200080cb9005702017d0001fd0d630d02b0b84777c34b868b410504304e10030b30060340518fa7000500304934050c30181c041d30010c01a0a6545bbf011300008100006400008900006300006000006500010200030020008b0000670004072019006200007f00030920008800030820008c000e00d0485bc5419ddf3a420000070c00005602017f0002fd0d630d02b0b84777c34b868b410504304b10040b30b7060340518fa700050030a933050c30181c041d30030c01a0a6545bbf01130000810000640000890000630000600000650001020004002001008b0000670004072019006200007f00030920008800030820008c000e00d0475bc5419ddf3a420100070cb700550219"
//);
				byte[] data = DatatypeConverter.parseHexBinary(
//						 "326f00054243452f440a0f020208009af4732de712030001540041a903630d02b000c04faa367c0341050340eca4030b30050430d20f030c300500"
//						 +
						"302a300113000407203c000200006400006500006300006200040820830500201e000e00d00ccccd41fc7836421e000c0b00006d02c7");

				connection.outbound().sendByteArray(Mono.just(data)).then().subscribe();

			} else if (input.equals("data5")) {

//				byte[] data = DatatypeConverter.parseHexBinary(
//						 "326f00054243452f440a0f020208009af4732de712030001540041a903630d02b000c04faa367c0341050340eca4030b30050430d20f030c300500302a300113000407203c000200006400006500006300006200040820830500201e000e00d00ccccd41fc7836421e000c0b00006d02c7"
//						 +
				// "326f");
//				byte[] data = DatatypeConverter.parseHexBinary(
//						"326f00054243452f440a0f020208009af4732de712030001540041a903630d02b000c04faa367c0341050340eca4030b30050430d20f030c300500"
//								+ "302a300113000407203c000200006400006500006300006200040820830500201e000e00d00ccccd41fc7836421e000c0b00006d02c7"
//								+ "326f00054243452f440a0f020208009af4732de712030001540041a903630d02b000c04faa367c0341050340eca4030b30050430d20f030c300500"
//								+ "302a300113000407203c000200006400006500006300006200040820830500201e000e00d00ccccd41fc7836421e000c0b00006d02c7");

				byte[] data = DatatypeConverter.parseHexBinary(
						"32930100054243452f4400b602020800aa07c0e6660e0300017a00d3aff4630d02b0806c44778f088041050c305418031d30050030802f0c01a02ce5ca3e0603402cc26100050430ae0f030b30018c00006500006700030020040720150308200409203e016200008800008900008b00001300006400000200007f000181000063000e00d0f337c541c4363b42000000000000000012");

				connection.outbound().sendByteArray(Mono.just(data)).then().subscribe();

			} else if (input.equals("bcedata")) {

				byte[] data = DatatypeConverter.parseHexBinary(
						"dd03d807fa1603002b00a5332847eef5e0ab40849e3a42cca0c641001b4ba6020000000090800e2fc400910fa40103e304cbbe003610");
				connection.outbound().sendByteArray(Mono.just(data)).then().subscribe();

			} else if (input.equals("sub")) {

				byte[] subData = DatatypeConverter
						// .parseHexBinary("8219000100143836353230393033393435313239302F4F55544301");
						.parseHexBinary(
								"8219000100143836353230393033393435313239302F4F555443018219000100143836353230393033393435313239302F4F55544301");

//						.parseHexBinary("8219032a00143836303636353035373438333632392f4f55544301");

				connection.outbound().sendByteArray(Mono.just(subData)).then().subscribe();

			} else if (input.equals("cco")) {

				byte[] subData = DatatypeConverter.parseHexBinary("0024" + imeiToHexBigEndian("860186054099482") + "07"
						+ "534554494F20636F6E66696775726174696F6E2064617461206F6B" + "C419");
				connection.outbound().sendByteArray(Mono.just(subData)).then().subscribe();

			} else if (input.equals("ccco")) {
				String cmdVal = scan.nextLine();

				byte[] subData = DatatypeConverter
						.parseHexBinary("324200054243452f4406a4020208009AF4732DE7120300012900c67873602000c0214750525320"
								+ cmdVal + "20286c6f636b2920636f6d6d616e6420657865637574656426");
				connection.outbound().sendByteArray(Mono.just(subData)).then().subscribe();

			} else if (input.equals("csub")) {
				String pid = scan.nextLine();
				byte[] subData = DatatypeConverter.parseHexBinary("400200" + pid);
				connection.outbound().sendByteArray(Mono.just(subData)).then().subscribe();

			} else {
				byte[] pingReq = { (byte) 0xC0, (byte) 0x00 };
				connection.outbound().sendByteArray(Mono.just(pingReq)).then().subscribe();

			}
		}
		connection.dispose();
		connection.onDispose().block();

	}

	public Mono<String> runServer2() {

		HandshakeHandler handler = new HandshakeHandler();

		tcpClient = TcpClient.create().port(9023).wiretap(true).host( /* "localhost" */ "172.75.75.102").port(9022)
				.handle(handler.handleInbound());
		connection2 = tcpClient.connectNow();

		byte[] connData = { (byte) 0x10, (byte) 0x3A, (byte) 0x00, (byte) 0x04, (byte) 0x4D, (byte) 0x51, (byte) 0x54,
				(byte) 0x54, (byte) 0x04, (byte) 0xC3, (byte) 0x00, (byte) 0xF0, (byte) 0x00, (byte) 0x0F, (byte) 0x38,
				(byte) 0x36, (byte) 0x35, (byte) 0x32, (byte) 0x30, (byte) 0x39, (byte) 0x30, (byte) 0x33, (byte) 0x39,
				(byte) 0x34, (byte) 0x35, (byte) 0x31, (byte) 0x32, (byte) 0x39, (byte) 0x30, (byte) 0x00, (byte) 0x07,
				(byte) 0x64, (byte) 0x65, (byte) 0x76, (byte) 0x69, (byte) 0x63, (byte) 0x65, (byte) 0x73, (byte) 0x00,
				(byte) 0x14, (byte) 0x31, (byte) 0x32, (byte) 0x33, (byte) 0x34, (byte) 0x35, (byte) 0x36, (byte) 0x37,
				(byte) 0x38, (byte) 0x39, (byte) 0x3A, (byte) 0x3B, (byte) 0x3C, (byte) 0x3D, (byte) 0x3E, (byte) 0x3F,
				(byte) 0x40, (byte) 0x41, (byte) 0x42, (byte) 0x43, (byte) 0x44 };

		connection2.outbound().sendByteArray(Mono.just(connData)).then().doOnNext(n -> sendSub()).subscribe();
		runData2();
		// connection2.onDispose().block();
		return Mono.just("1st car");

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

	@Override
	public void run() {
		runServer2().subscribe();
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

	public static String imeiToAscii(String imei) {

		return DataTypeUtil.bytesToHex(imei.getBytes(CharsetUtil.US_ASCII));

	}

	public static String imeiToHexBigEndian(String imei) {
		try {
			String hex = Long.toHexString(Unpooled
					.copiedBuffer(DatatypeConverter.parseHexBinary("000" + Long.toHexString(Long.parseLong(imei))))
					.readLong()) + "";

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
					.readLong()) + "";
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

	public static String imeiToHex(String imei) {
		try {
			String hex = Long.toHexString(Unpooled
					.copiedBuffer(DatatypeConverter.parseHexBinary("000" + Long.toHexString(Long.parseLong(imei))))
					.readLong()) + "";

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
					.readLong()) + "";
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
}
