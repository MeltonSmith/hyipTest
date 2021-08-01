package r.ian.ianlabtest.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import r.ian.ianlabtest.data.domain.User;
import r.ian.ianlabtest.sec.CustomUserDetailsManager;
import r.ian.ianlabtest.sec.role.UserRole;

/**
 * @author Melton Smith
 * @since 31.07.2021
 */
@Slf4j
@Service
@Profile("kafka")
@KafkaListener(topics = "#{'${spring.kafka.processedTopic}'}")
@Transactional
public class UserApprovalListener implements MessageListener<ConsumerRecord<String, String>>{

    CustomUserDetailsManager customUserDetailsManager;

    @Autowired
    public UserApprovalListener(CustomUserDetailsManager customUserDetailsManager) {
        this.customUserDetailsManager = customUserDetailsManager;
    }

    @KafkaHandler(isDefault = true)
    public void listen(ConsumerRecord<String, String> record) {
        String messageValue = record.value();
        String messageKey = record.key();

        log.debug(messageKey + " " + messageValue);

        User userById = customUserDetailsManager.getUserById(messageKey);

        try{
            UserRole takenRole = UserRole.valueOf(messageValue);
            userById.setUserRole(takenRole);
            customUserDetailsManager.updateUser(userById);
        }
        catch(IllegalArgumentException exception){
            log.error("No status for the given role {}", messageValue);
        }
    }

}
