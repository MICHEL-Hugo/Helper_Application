package fr.insa.helperapp.whoIsTheResolverMS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class WhoIsTheResolverMsApplication {
	
    @Bean
    @LoadBalanced // Permet de résoudre les noms des services enregistrés sur Eureka
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

	public static void main(String[] args) {
		SpringApplication.run(WhoIsTheResolverMsApplication.class, args);
	}

}