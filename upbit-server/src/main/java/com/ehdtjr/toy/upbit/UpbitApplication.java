package com.ehdtjr.toy.upbit;

import javax.annotation.PreDestroy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.ApplicationPidFileWriter;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class UpbitApplication {
	public static void main(String[] args) {
//		SpringApplication.run(SenderApplication.class, args);
		SpringApplication application = new SpringApplicationBuilder().sources(UpbitApplication.class)
				.listeners(new ApplicationPidFileWriter("./this.pid")) // shutdown.sh 사용
				.build();
		application.run(args);
	}

	@PreDestroy
	public void destroy() {
		log.error("shutdown application"); // TODO 종료 알람 등록
	}
}
