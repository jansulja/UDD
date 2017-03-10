package com.ftn.jan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import com.ftn.jan.util.StorageProperties;

@SpringBootApplication
@EnableJpaRepositories(basePackages="com.ftn.jan")
@EnableConfigurationProperties(StorageProperties.class)
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class UppProjekatApplication {

	public static void main(String[] args) {
		SpringApplication.run(UppProjekatApplication.class, args);
	}
}
