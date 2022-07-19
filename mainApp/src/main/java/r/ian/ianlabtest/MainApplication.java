package r.ian.ianlabtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Arrays;

@SpringBootApplication
@EnableAsync
@EnableTransactionManagement
@EnableJpaRepositories
@EnableScheduling
@ConfigurationPropertiesScan
public class MainApplication implements CommandLineRunner {

    @Autowired
    private ApplicationContext applicationContext;

    public static void main(String[] args) {
         SpringApplication.run(MainApplication.class, args);
    }

    @Override
    public void run(String... args) {
        String[] allBeanNames = applicationContext.getBeanDefinitionNames();

        Arrays.stream(allBeanNames)
                .filter(beanName -> beanName.toLowerCase().endsWith("transactionmanager"))
                .map(beanName -> "beanName -> " + beanName + ", the bean is " + applicationContext.getBean(beanName).getClass())
                .forEach(System.out::println);

    }


}
