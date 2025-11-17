package com.br.join_sign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class JoinSignApplication {

  public static void main(String[] args) {
    SpringApplication.run(JoinSignApplication.class, args);
  }
}
