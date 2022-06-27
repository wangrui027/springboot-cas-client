package com.wangrui027.springboot_cas_client;

import com.wangrui027.springboot_cas_client.listener.TrustCASServerSSLListener;
import org.jasig.cas.client.boot.configuration.EnableCasClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;

@EnableCasClient
@SpringBootApplication(exclude = {MongoAutoConfiguration.class})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ServletListenerRegistrationBean<TrustCASServerSSLListener> listenerRegistrationBean() {
        ServletListenerRegistrationBean<TrustCASServerSSLListener> registrationBean = new ServletListenerRegistrationBean<>();
        registrationBean.setListener(new TrustCASServerSSLListener());
        return registrationBean;
    }

}
