package com.netty.receiver.tcpClient;

import java.util.Date;
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

		long imei = 865209039451291l;
		ByteBuf bb = Unpooled.buffer(8);
		bb.writeLongLE(imei);
		System.err.println(DataTypeUtil.bytesToHex(bb.array()));
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

}
