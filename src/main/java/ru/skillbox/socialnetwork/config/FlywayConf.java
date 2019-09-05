package ru.skillbox.socialnetwork.config;

import java.util.Arrays;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class FlywayConf {

   private final String PROFILE_FILL_TEST_DATA = "dev";
   private Logger logger = LogManager.getLogger(FlywayConf.class);

   @Autowired
   private Environment environment;
   @Autowired
   private ApplicationContext context;

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

      //fill test data to DB
      String activeProfile = Arrays.stream(environment.getActiveProfiles())
          .findFirst()
          .orElse("");
      logger.info("Active profile - {}", activeProfile);

      if (activeProfile.equals(PROFILE_FILL_TEST_DATA)) {
         context.getBean(ProfileDev.class).fillTestData();
      }
      return flyway;
   }
}
