package r.ian.ianlabtest.messaging.kafkaconfig;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;

/**
 * @author Melton Smith
 * @since 01.08.2021
 */

@Profile("kafka")
@ConfigurationProperties(prefix = "spring.kafka")
@Setter
@Getter
public class KafkaProperties {
    private String approvalTopic;
    private String processedTopic;
}
