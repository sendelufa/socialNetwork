package ru.skillbox.socialnetwork;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;
@SpringBootApplication
@EnableEurekaClient

public class SocialNetworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialNetworkApplication.class, args);
	}
}
