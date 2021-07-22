package r.ian.ianskblab.messaging.kafkaconfig;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.transaction.KafkaTransactionManager;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.apache.kafka.clients.admin.AdminClientConfig.*;

/**
 * @author Melton Smith
 * @since 21.07.2021
 */
@Configuration
public class KafkaConfig {

    //NOTE no need I use boot. I guess it's all done by .yml file
//    @Bean
//    //TODO by a property
//    public KafkaAdmin admin() {
//        Map<String, Object> configs = new HashMap<>();
//        configs.put(BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//        configs.put(RECONNECT_BACKOFF_MS_CONFIG, TimeUnit.MINUTES.toMillis(2));
//        configs.put(REQUEST_TIMEOUT_MS_CONFIG, TimeUnit.MINUTES.toMillis(1));
//        return new KafkaAdmin(configs);
//    }

    @Bean
    public NewTopic skbTestUser() {
        return TopicBuilder.name("skbTestUser")
//                .partitions(1) //since 2.4.0
//                .replicas(1)
                .compact()
                .build();
    }



    //NOTE boot handles this
//    @Bean
//    public KafkaTransactionManager<String, String> kafkaTransactionManager(){
//        return new KafkaTransactionManager<>();
//    }

}
