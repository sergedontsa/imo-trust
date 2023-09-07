package com.trust.gestion;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Locale;
import java.util.TimeZone;

@EnableJpaRepositories(basePackages = "com.trust.gestion.*")
@EntityScan(basePackages = "com.trust.gestion.*")
@EnableScheduling
@EnableTransactionManagement
@SpringBootApplication
public class GestionApplication {
    @Value("${server.timezone}")
    private String serverTimezone;

    @PostConstruct
    public void init(){
        Locale.setDefault(Locale.CANADA);
        TimeZone.setDefault(TimeZone.getTimeZone(serverTimezone));
    }
    public static void main(String[] args) {
        SpringApplication.run(GestionApplication.class, args);
    }

}
