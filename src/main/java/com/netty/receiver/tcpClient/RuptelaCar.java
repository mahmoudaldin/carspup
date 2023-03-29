package com.netty.receiver.tcpClient;

import java.util.Date;
import java.util.Random;

import javax.xml.bind.DatatypeConverter;

import com.netty.receiver.handler.DataTypeUtil;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class RuptelaCar {
	int speed;
	static final Random r = new Random();

	public RuptelaCar(int speed) {
		super();
		this.speed = speed;
	}

	public static void main(String[] args) {

//		long imei = 865209039451291l;
//		ByteBuf bb = Unpooled.buffer(8);
//		bb.writeLongLE(imei);
//		System.err.println(DataTypeUtil.bytesToHex(bb.array()));

		// HashSet set = new HashSet<>();
//		for (int i = 0; i < 1000; i++) {
//			// set.add(getRandomACC());
//			System.err.println(getRandomACC());
//
//		}
		 System.err.println(getRandomFuel());

//		System.err.println(crc16(
//				"00030E55AC32021A440002631F913D00000020DDE53A0EE3ED84004227C41300000900070C000500001C0100B000005800001B1900062A00AD000020270031000032060033EE00880004001E1010001D65FC00890000004E00ED0300300000000000960000A5A20041193F903600631F91B700000020DDE53A0EE3ED84003E27C41500000700AE0D000500001C0100B000005800001B1900062A00AD0000202700AE010031000032060033EE00880004001E1010001D65FD00890000004E00ED0300300000000000960000A5A20041193F903600"));

	}

	static String getRandomSpeed() {
		int low = 0;
		int high = 140;
		int result = 0;
		result = r.nextInt(high - low) + low;
//		System.out.println("spd:" + result);
		ByteBuf bb = Unpooled.buffer(2);
		bb.writeShort((short) (Math.ceil(result /* 50 */ / 0.539957)));
		String speed = DataTypeUtil.bytesToHex(bb.array());
		bb.release();
		return speed;
	}

	static String getSpeedZer0() {
		// System.out.println(result);
		ByteBuf bb = Unpooled.buffer(2);
		bb.writeShort(0);
		String speed = DataTypeUtil.bytesToHex(bb.array());
		bb.release();
		return speed;
	}

	static String getRandomDeviceBattery() {
		int low = 3000;
		int high = 5000;
		int result = 0;
		result = r.nextInt(high - low) + low;
		// System.out.println(result);
		ByteBuf bb = Unpooled.buffer(2);
		bb.writeShort(result);
		String batt = DataTypeUtil.bytesToHex(bb.array());
		bb.release();
		return batt;
	}

	static String getRandomExternalBattery() {
		int low = 9500;
		int high = 21000;
		int result = 0;
		result = r.nextInt(high - low) + low;
		// System.out.println(result);
		ByteBuf bb = Unpooled.buffer(2);
		bb.writeShort(result);
		String batt = DataTypeUtil.bytesToHex(bb.array());
		bb.release();
		return batt;
	}

	static String getTime() {

		ByteBuf bb = Unpooled.buffer(4);
		bb.writeInt((int) (new Date().getTime() / 1000));
		String time = DataTypeUtil.bytesToHex(bb.array());
		bb.release();
		return time;
	}

	static String getRandomFuel() {
		int low = 0;
		int high = 140;
		int result = 0;
		result = r.nextInt(high - low) + low;
//		 System.out.println(result);
		ByteBuf bb = Unpooled.buffer(2);
		bb.writeShort(result);
		String fuel = DataTypeUtil.bytesToHex(bb.array());
		bb.release();
		return fuel;
	}

	static String getStaticFuel() {
		// System.out.println(result);
		ByteBuf bb = Unpooled.buffer(1);
		bb.writeShort(50);
		String fuel = DataTypeUtil.bytesToHex(bb.array());
		bb.release();
		return fuel;
	}

	static String getRandomTemp() {
		int low = 0;
		int high = 255;
		int result = 0;
		result = r.nextInt(high - low) + low;
		// System.out.println(result);
		ByteBuf bb = Unpooled.buffer(2);
		bb.writeShort(result);
		String temp = DataTypeUtil.bytesToHex(bb.array());
		bb.release();
		return temp;
	}

	static String getRandomLat() {

		int low = 16;
		int high = 30;
		float result = 0;
		result = low + new Random().nextFloat() * (high - low);
		// System.out.println(result);
		ByteBuf bb = Unpooled.buffer(4);
		bb.writeInt((int) (result /* 21.5f */ * 10000000));
		String lat = DataTypeUtil.bytesToHex(bb.array());
		bb.release();
		return lat;
	}

	static String getStaticLat() {

		// System.out.println(result);
		ByteBuf bb = Unpooled.buffer(4);
		bb.writeInt((int) (/* result */ 27.43f * 10000000));
		String lat = DataTypeUtil.bytesToHex(bb.array());
		bb.release();
		return lat;
	}

	static String getRandomLongitude() {

		int low = 38;
		int high = 50;
		float result = 0;
		result = low + new Random().nextFloat() * (high - low);
		// System.out.println(result);
		ByteBuf bb = Unpooled.buffer(4);
		bb.writeInt((int) (result /* 24.5f */ * 10000000));
		String lon = DataTypeUtil.bytesToHex(bb.array());
		bb.release();
		return lon;
	}

	static String getStaticLongitude() {

		// System.out.println(result);
		ByteBuf bb = Unpooled.buffer(4);
		bb.writeInt((int) (41.6f * 10000000));
		String lon = DataTypeUtil.bytesToHex(bb.array());
		bb.release();
		return lon;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "" + speed;
	}

	static String getRandomACC() {
		// TODO Auto-generated method stub
		int low = 0;
		int high = 2;
		int result = 0;
		result = r.nextInt(high - low) + low;

		if (result != 0 && result != 1) {
			return "00";
		}

		return "0" + result;
	}
	static String getRandomMovment() {
		return getRandomACC();
	}

//	public static String calculateChecksum(String hexString) {
//		byte[] bytes = hexStringToByteArray(hexString);
//		int crc = calculateCRC16Kermit(bytes);
//		return Integer.toHexString(crc).toUpperCase();
//	}
//
//	private static byte[] hexStringToByteArray(String hexString) {
//		int len = hexString.length();
//		byte[] data = new byte[len / 2];
//		for (int i = 0; i < len; i += 2) {
//			data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
//					+ Character.digit(hexString.charAt(i + 1), 16));
//		}
//		return data;
//	}
//
//	private static int calculateCRC16Kermit(byte[] bytes) {
//		int crc = 0;
//		for (byte b : bytes) {
//			crc = (crc >>> 8) | (crc << 8);
//			crc ^= (b & 0xFF);
//			crc ^= ((crc & 0xFF) >> 4);
//			crc ^= (crc << 12) & 0xFFFF;
//			crc ^= ((crc & 0xFF) << 5) & 0xFFFF;
//		}
//		return crc & 0xFFFF;
//	}

	public static int crc16(byte[] data) {
		int poly = 0x8408; // reversed 0x1021
		int crc = 0;
		for (int i = 0; i < data.length; i++) {
			crc ^= (data[i] & 0xFF);
			for (int j = 0; j < 8; j++) {
				int carry = crc & 1;
				crc >>= 1;
				if (carry == 1) {
					crc ^= poly;
				}
			}
		}
		return crc & 0xFFFF;
	}

	public static String crc16(String hexString) {
		int poly = 0x8408; // reversed 0x1021
		int crc = 0;
		byte[] data = hexStringToByteArray(hexString);
		for (int i = 0; i < data.length; i++) {
			crc ^= (data[i] & 0xFF);
			for (int j = 0; j < 8; j++) {
				int carry = crc & 1;
				crc >>= 1;
				if (carry == 1) {
					crc ^= poly;
				}
			}
		}
		return String.format("%04X", crc);
	}

	private static byte[] hexStringToByteArray(String hexString) {
		int len = hexString.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
					+ Character.digit(hexString.charAt(i + 1), 16));
		}
		return data;
	}

	public static int countBytes(String... hexStrings) {
		int totalBytes = 0;
		for (String hexString : hexStrings) {
			totalBytes += hexString.length() / 2;
		}
		return totalBytes;
	}

	public static String countBytes(String hexString) {
		int totalBytes = hexString.length() / 2;
		return String.format("%04X", totalBytes);
	}

}
