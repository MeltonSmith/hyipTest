package r.ian.ianlabtest.listener;

import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Melton Smith
 * @since 03.07.2025
 */
public class ListenerConfig {

//    @Bean
//    public Map<String, Object> consumer_Configs() {
//        Map<String, Object> prop = new HashMap<>();
//        prop.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "eprupetw10aa:30903");
//        prop.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        prop.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        prop.put(ConsumerConfig.GROUP_ID_CONFIG, "batch");
//        prop.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "5");
//        return prop;
//    }

//    @Bean
//    public ConsumerFactory<String, String> consumerFactory() {
//        DefaultKafkaConsumerFactory<String, String> stringStringDefaultKafkaConsumerFactory = new DefaultKafkaConsumerFactory<>(consumer_Configs());
//        return stringStringDefaultKafkaConsumerFactory;
//
//
//    }

//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory());
////        factory.setBatchListener(true);
//        return factory;
//    }
}
