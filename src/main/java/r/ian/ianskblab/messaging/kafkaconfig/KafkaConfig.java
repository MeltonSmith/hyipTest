package r.ian.ianskblab.messaging.kafkaconfig;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

/**
 * @author Melton Smith
 * @since 21.07.2021
 */
//@Configuration
public class KafkaConfig {

    //NOTE no need I use boot. I guess it's all done by .yml file
    //there should be KafkaAdmin and KafkaTXManager when coding without boot


    @Bean
    public NewTopic skbTestUser() {
        return TopicBuilder.name("skbTestUser")
                .partitions(1) //I can omit it since 2.4.0
                .replicas(1)
                .compact()
                .build();
    }

}
