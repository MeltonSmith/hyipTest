package r.ian.ianlabtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class IanLabTestApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(IanLabTestApplication.class, args);
    }

}
