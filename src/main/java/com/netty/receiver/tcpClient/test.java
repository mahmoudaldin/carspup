package com.netty.receiver.tcpClient;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.netty.receiver.handler.DataTypeUtil;

import io.netty.util.CharsetUtil;

public class test {
	public static void main(String[] args) {
//	System.err.println(imeiToAscii("865209039451290"));

//System.out.println("abc".hashCode()< "zzzzzzzzzzzzzzzzzzz".hashCode());
//		
//System.out.println("dslks dskdlss".replaceFirst(".*\s", "")); 

		System.err.println(LocalDateTime.now(ZoneId.of("GMT+00:00")));

		System.err.println(Instant.now());
		System.err.println(Instant.now().toEpochMilli());

		System.err.println(new Date().getTime());
		System.err.println(new Date());


	}

	public static List<Integer> numberOfItems(String s, List<Integer> startIndices, List<Integer> endIndices) {

		char sc[] = s.toCharArray();

		List<Integer> itemsCounts = new ArrayList<>();
		for (int indxs = 0; indxs < startIndices.size(); indxs++) {

			int open = 0;
			int itemCount = 0;
			int allItemsCount = 0;

			for (int i = startIndices.get(indxs) - 1; i < endIndices.get(indxs); i++) {

				if (sc[i] == '|') {
					open++;
					if (i < endIndices.get(indxs) - 1)
						continue;
				}
				if (open != 0) {

					if (open % 2 == 0) {
						allItemsCount += itemCount;
						itemCount = 0;
						open++;

					}

					if (open % 2 != 0)
						itemCount++;
					// itemsCounts.add(itemCount);
				}

			}
			itemsCounts.add(allItemsCount);
		}
		return itemsCounts;

	}

	public static String imeiToAscii(String imei) {

		return DataTypeUtil.bytesToHex(imei.getBytes(CharsetUtil.US_ASCII));

		// return Unpooled.copiedBuffer(imei, CharsetUtil.US_ASCII);

	}

	/*
	 * List<Integer> s = new ArrayList<>(); List<Integer> e = new ArrayList<>();
	 * s.add(1); s.add(1); s.add(1); e.add(1); e.add(5); e.add(6);
	 * 
	 * 
	 * System.out.println(s); System.out.println(e);
	 * 
	 * 
	 * 
	 * String d = "z";
	 * 
	 * if (d.charAt(0) == 'z')
	 * 
	 * 
	 * System.out.println(numberOfItems("|**|*|",s,e));
	 */
}
