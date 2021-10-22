package com.bfs.onboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
@EnableCaching
public class OnboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnboardApplication.class, args);
    }

}
