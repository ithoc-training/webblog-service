package de.ithoc.webblog;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!dev")
public class WebblogConfig {

    @Value("${db.username}")
    private String dbUsername;

    @Value("${db.password}")
    private String dbPassword;

    @Bean
    public String dbUsername() {
        System.out.println("dbUsername: " + dbUsername);
        return dbUsername;
    }

    @Bean
    public String dbPassword() {
        System.out.println("dbPassword: " + dbPassword);
        return dbPassword;
    }
}
