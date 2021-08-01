package r.ian.ianlabtest.messaging.kafkaconfig;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;

/**
 * @author Melton Smith
 * @since 21.07.2021
 */
@Configuration
@Profile("kafka")
@EnableKafka
public class KafkaConfig {

    //no need because I use boot. I guess it's all done by .yml file
    //there should be KafkaAdmin and KafkaTXManager when coding without boot

    @Value("${spring.kafka.approvalTopic}")
    private String approvalTopic;

    @Value("${spring.kafka.processedTopic}")
    private String processedTopic;

    @Bean
    public NewTopic userSentForApproval() {
        return TopicBuilder.name(approvalTopic)
                .partitions(1) //I can omit it since 2.4.0
                .replicas(1)
                .compact()
                .build();
    }

    @Bean
    public NewTopic processedUsers() {
        return TopicBuilder.name(processedTopic)
                .partitions(1)
                .replicas(1)
                .compact()
                .build();
    }

}
