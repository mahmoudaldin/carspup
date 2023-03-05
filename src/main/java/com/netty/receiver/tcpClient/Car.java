package com.netty.receiver.tcpClient;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

import com.netty.receiver.handler.DataTypeUtil;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class Car {
	int speed;
	static final Random r = new Random();

	public Car(int speed) {
		super();
		this.speed = speed;
	}

	public static void main(String[] args) {

//		long imei = 865209039451291l;
//		ByteBuf bb = Unpooled.buffer(8);
//		bb.writeLongLE(imei);
//		System.err.println(DataTypeUtil.bytesToHex(bb.array()));

		// HashSet set = new HashSet<>();
		for (int i = 0; i < 1000; i++) {
			// set.add(getRandomACC());
			System.err.println(getRandomACC());

		}
		// System.err.println(set);

	}

	static String getRandomSpeed() {
		int low = 0;
		int high = 140;
		int result = 0;
		result = r.nextInt(high - low) + low;
		// System.out.println(result);
		ByteBuf bb = Unpooled.buffer(2);
		bb.writeShortLE(result /* 50 */);
		String speed = DataTypeUtil.bytesToHex(bb.array());
		bb.release();
		return speed;
	}

	static String getSpeedZer0() {
		// System.out.println(result);
		ByteBuf bb = Unpooled.buffer(2);
		bb.writeShortLE(0);
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
		bb.writeShortLE(result);
		String batt = DataTypeUtil.bytesToHex(bb.array());
		bb.release();
		return batt;
	}

	static String getRandomExternalBattery() {
		int low = 11000;
		int high = 21000;
		int result = 0;
		result = r.nextInt(high - low) + low;
		// System.out.println(result);
		ByteBuf bb = Unpooled.buffer(2);
		bb.writeShortLE(result);
		String batt = DataTypeUtil.bytesToHex(bb.array());
		bb.release();
		return batt;
	}

	static String getTime() {

		ByteBuf bb = Unpooled.buffer(4);
		bb.writeIntLE((int) (new Date().getTime() / 1000));
		String time = DataTypeUtil.bytesToHex(bb.array());
		bb.release();
		return time;
	}

	static String getRandomFuel() {
		int low = 0;
		int high = 140;
		int result = 0;
		result = r.nextInt(high - low) + low;
		// System.out.println(result);
		ByteBuf bb = Unpooled.buffer(1);
		bb.writeByte(result);
		String fuel = DataTypeUtil.bytesToHex(bb.array());
		bb.release();
		return fuel;
	}

	static String getStaticFuel() {
		// System.out.println(result);
		ByteBuf bb = Unpooled.buffer(1);
		bb.writeByte(50);
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
		ByteBuf bb = Unpooled.buffer(1);
		bb.writeByte(result);
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
		bb.writeFloatLE(result /* 21.5f */);
		String lat = DataTypeUtil.bytesToHex(bb.array());
		bb.release();
		return lat;
	}

	static String getStaticLat() {

		// System.out.println(result);
		ByteBuf bb = Unpooled.buffer(4);
		bb.writeFloatLE(/* result */ 27.43f);
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
		bb.writeFloatLE(result /* 24.5f */);
		String lon = DataTypeUtil.bytesToHex(bb.array());
		bb.release();
		return lon;
	}

	static String getStaticLongitude() {

		// System.out.println(result);
		ByteBuf bb = Unpooled.buffer(4);
		bb.writeFloatLE(41.6f);
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

}
