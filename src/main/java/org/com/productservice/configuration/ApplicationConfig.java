package org.com.productservice.configuration;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@Configurable
public class ApplicationConfig {

    @Bean
    public RestTemplate createRestTemplate(){
        return new RestTemplate();
    }

}
