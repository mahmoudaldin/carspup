package com.netty.receiver;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.netty.receiver.tcpClient.WriteRifle2;
import com.netty.receiver.tcpClient.WriteRifle3;
import com.netty.receiver.tcpClient.WriteRifle4;
import com.netty.receiver.tcpClient.WriteRifle5;
import com.netty.receiver.tcpClient.WriteRifle6;
import com.netty.receiver.tcpClient.WriteRifle7;
import com.netty.receiver.tcpClient.WriteRifle8;

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

		rifle.start();
		rifle2.start();

		rifle3.start();
		rifle4.start();

		rifle5.start();
		rifle6.start();

		rifle7.start();
		rifle8.start();

	}

}
