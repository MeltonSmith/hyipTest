package r.ian.ianlabtest.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.beans.factory.annotation.Autowired;
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
@Transactional
public class UserApprovalListener {

    CustomUserDetailsManager customUserDetailsManager;

    @Autowired
    public UserApprovalListener(CustomUserDetailsManager customUserDetailsManager) {
        this.customUserDetailsManager = customUserDetailsManager;
    }

    @KafkaListener(topics = "#{'${spring.kafka.processedTopic}'}",
            batch = "true")
    //TODO make transaction
    public void listen(ConsumerRecords<String, String> records) {

        records.forEach(record -> {
            String messageValue = record.value();
            String messageKey = record.key();

            log.info(messageKey + " " + messageValue);
        });


//        User userById = customUserDetailsManager.getUserById(messageKey);
//
//        try{
//            UserRole takenRole = UserRole.valueOf(messageValue);
//            userById.setUserRole(takenRole);
//            customUserDetailsManager.updateUser(userById);
//        }
//        catch(IllegalArgumentException exception){
//            log.error("No status for the given role {}", messageValue);
//        }
    }

//    public void listen(ConsumerRecord<String, String> record) {
//        String messageValue = record.value();
//        String messageKey = record.key();
//
//        log.debug(messageKey + " " + messageValue);
//
//        User userById = customUserDetailsManager.getUserById(messageKey);
//
//        try{
//            UserRole takenRole = UserRole.valueOf(messageValue);
//            userById.setUserRole(takenRole);
//            customUserDetailsManager.updateUser(userById);
//        }
//        catch(IllegalArgumentException exception){
//            log.error("No status for the given role {}", messageValue);
//        }
//    }

}
