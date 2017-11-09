package com.kiwi.insurance.config;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class HealthCheck implements HealthIndicator {
  
    @Override
    public Health health() {
        int errorCode = check(); // perform some specific health check
        if (errorCode != 0) {
            return Health.down()
              .withDetail("Error Code", errorCode).build();
        }
        return Health.up().build();
    }
     
    public int check() {
        RestTemplate restTemplate = new RestTemplate();
        try {
	        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:9090/kiwi/resources-info", String.class);
	        if (response.getStatusCode() == HttpStatus.OK) {
	        		return 0;
	        } else {
	        		return 1;
	        }
        } catch (Exception e) {
        		return 1;
        }
    }
}
