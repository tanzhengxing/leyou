package com.leyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author tan
 * @date 2019/5/13 9:47
 */
@EnableDiscoveryClient
@SpringBootApplication
public class LyUploadService {

  public static void main(String[] args) {
    SpringApplication.run(LyUploadService.class);
  }


}
