package ru.skillbox.socialnetwork.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlywayConf {
   @Value("${spring.datasource.url}")
   private String url;
   @Value("${spring.datasource.username}")
   private String user;
   @Value("${spring.datasource.password}")
   private String password;

   @Bean(initMethod = "migrate")
   public Flyway flyway() {
      Flyway flyway = Flyway.configure().dataSource(url, user, password).load();
      flyway.migrate();
      return flyway;
   }
}
