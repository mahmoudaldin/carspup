package com.netty.receiver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

@Configuration
public class AppConfig {
	@Bean
	public Write getServer() {
		return new Write();
	}

	@Bean
	public Write2 getServer2() {
		return new Write2();
	}

	@Bean
	public Write3 getServer3() {
		return new Write3();
	}

	@Bean
	public Write4 getServer4() {
		return new Write4();
	}

	@Bean
	public Write5 getServer5() {
		return new Write5();
	}

	@Bean
	public Write6 getServer6() {
		return new Write6();
	}

	@Bean
	public WriteRifle getRifle() {
		return new WriteRifle();
	}

	@Bean
	public WriteRifle2 getRifle2() {
		return new WriteRifle2();
	}

	@Bean
	public WriteRifle3 getRifle3() {
		return new WriteRifle3();
	}

	@Bean
	public WriteRifle4 getRifle4() {
		return new WriteRifle4();
	}

	@Bean
	public WriteRifle5 getRifle5() {
		return new WriteRifle5();
	}

	@Bean
	public WriteRifle6 getRifle6() {
		return new WriteRifle6();
	}

	@Bean
	public WriteRifle7 getRifle7() {
		return new WriteRifle7();
	}

	@Bean
	public WriteRifle8 getRifle8() {
		return new WriteRifle8();
	}
}
