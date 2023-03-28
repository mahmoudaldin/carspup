package com.netty.receiver;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.netty.receiver.tcpClient.Write;
import com.netty.receiver.tcpClient.Write2;
import com.netty.receiver.tcpClient.Write3;
import com.netty.receiver.tcpClient.Write4;
import com.netty.receiver.tcpClient.Write5;
import com.netty.receiver.tcpClient.Write6;
import com.netty.receiver.tcpClient.WriteRifle;
import com.netty.receiver.tcpClient.WriteRifle10;
import com.netty.receiver.tcpClient.WriteRifle11;
import com.netty.receiver.tcpClient.WriteRifle12;
import com.netty.receiver.tcpClient.WriteRifle13;
import com.netty.receiver.tcpClient.WriteRifle14;
import com.netty.receiver.tcpClient.WriteRifle2;
import com.netty.receiver.tcpClient.WriteRifle3;
import com.netty.receiver.tcpClient.WriteRifle4;
import com.netty.receiver.tcpClient.WriteRifle5;
import com.netty.receiver.tcpClient.WriteRifle6;
import com.netty.receiver.tcpClient.WriteRifle7;
import com.netty.receiver.tcpClient.WriteRifle8;
import com.netty.receiver.tcpClient.WriteRifle9;
import com.netty.receiver.tcpClient.WriteRupRifle15;
import com.netty.receiver.tcpClient.WriteRupRifle16;
import com.netty.receiver.tcpClient.WriteRupRifle17;
import com.netty.receiver.tcpClient.WriteRupRifle18;
import com.netty.receiver.tcpClient.WriteRupRifle19;
import com.netty.receiver.tcpClient.WriteRupRifle20;

@SpringBootApplication
public class ReceiverApplication implements ApplicationRunner {
	@Autowired
	Write server;

	@Autowired
	Write2 server2;

	@Autowired
	Write3 server3;

	@Autowired
	Write4 server4;

	@Autowired
	Write5 server5;

	@Autowired
	Write6 server6;

	@Value(value = "${rifles}")
	String[] rifles;

	@Autowired
	WriteRifle rifle;
	@Autowired
	WriteRifle2 rifle2;
	@Autowired
	WriteRifle3 rifle3;
	@Autowired
	WriteRifle4 rifle4;
	@Autowired
	WriteRifle5 rifle5;
	@Autowired
	WriteRifle6 rifle6;
	@Autowired
	WriteRifle7 rifle7;
	@Autowired
	WriteRifle8 rifle8;
	@Autowired
	WriteRifle9 rifle9;
	@Autowired
	WriteRifle10 rifle10;
	@Autowired
	WriteRifle11 rifle11;
	@Autowired
	WriteRifle12 rifle12;
	@Autowired
	WriteRifle13 rifle13;
	@Autowired
	WriteRifle14 rifle14;
	@Autowired
	WriteRupRifle15 rifleRup15;
	@Autowired
	WriteRupRifle16 rifleRup16;
	@Autowired
	WriteRupRifle17 rifleRup17;
	@Autowired
	WriteRupRifle18 rifleRup18;
	@Autowired
	WriteRupRifle19 rifleRup19;
	@Autowired
	WriteRupRifle20 rifleRup20;

	public static ConfigurableApplicationContext context;

	public static void main(String[] args) {
		context = SpringApplication.run(ReceiverApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		/*
		 * server.start(); server2.start(); server3.start(); server4.start();
		 * server5.start(); server6.start();
		 */

		List<String> rifless = Arrays.asList(rifles);
		if (rifless.contains("1")) {
			System.out.println("Start rifle 1 ...");
			rifle.start();
		}
		if (rifless.contains("2")) {
			System.out.println("Start rifle 2 ...");
			rifle2.start();
		}
		if (rifless.contains("3")) {
			System.out.println("Start rifle 3 ...");
			rifle3.start();
		}
		if (rifless.contains("4")) {
			System.out.println("Start rifle 4 ...");
			rifle4.start();
		}
		if (rifless.contains("5")) {
			System.out.println("Start rifle 5 ...");
			rifle5.start();
		}
		if (rifless.contains("6")) {
			System.out.println("Start rifle 6 ...");
			rifle6.start();
		}
		if (rifless.contains("7")) {
			System.out.println("Start rifle 7 ...");
			rifle7.start();
		}
		if (rifless.contains("8")) {
			System.out.println("Start rifle 8 ...");
			rifle8.start();
		}
		if (rifless.contains("9")) {
			System.out.println("Start rifle 9 ...");
			rifle9.start();
		}
		if (rifless.contains("10")) {
			System.out.println("Start rifle 10 ...");
			rifle10.start();
		}
		if (rifless.contains("11")) {
			System.out.println("Start rifle 11 ...");
			rifle11.start();
		}
		if (rifless.contains("12")) {
			System.out.println("Start rifle 12 ...");
			rifle12.start();
		}
		if (rifless.contains("13")) {
			System.out.println("Start rifle 13 ...");
			rifle13.start();
		}
		if (rifless.contains("14")) {
			System.out.println("Start rifle 14 ...");
			rifle14.start();
		}
		if (rifless.contains("15")) {
			System.out.println("Start rup rifle 15 ...");
			rifleRup15.start();
		}
		if (rifless.contains("16")) {
			System.out.println("Start rup rifle 16 ...");
			rifleRup16.start();
		}
		if (rifless.contains("17")) {
			System.out.println("Start rup rifle 17 ...");
			rifleRup17.start();
		}
		if (rifless.contains("18")) {
			System.out.println("Start rup rifle 18 ...");
			rifleRup18.start();
		}
		if (rifless.contains("19")) {
			System.out.println("Start rup rifle 19 ...");
			rifleRup19.start();
		}
		if (rifless.contains("20")) {
			System.out.println("Start rup rifle 20 ...");
			rifleRup20.start();
		}

	}

}
