package com.lvr.HotSauce;

import com.lvr.HotSauce.configuration.CustomBanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HotSauceApplication {

  public static void main(String[] args) {
    SpringApplication application = new SpringApplication(HotSauceApplication.class);
    application.setBanner(new CustomBanner());
    application.run(args);
  }
}
