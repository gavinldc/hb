package com.dc.hb.facade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@ComponentScan("com.dc.hb")
public class AppStart {
	public static void main(String[] args) {
		SpringApplication.run(AppStart.class);
		log.info("hb start success .....");
	}
}
