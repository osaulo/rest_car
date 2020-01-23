package br.com.microservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Lazy;

import com.netflix.discovery.EurekaClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroserviceProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceProjectApplication.class, args);
	}
}
