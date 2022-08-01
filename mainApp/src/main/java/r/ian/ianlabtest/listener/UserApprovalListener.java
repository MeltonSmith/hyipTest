package r.ian.ianlabtest.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import r.ian.ianlabtest.data.domain.User;
import r.ian.ianlabtest.data.repo.UserRepo;
import r.ian.ianlabtest.sec.CustomUserDetailsManager;
import r.ian.ianlabtest.data.domain.UserStatus;

import java.util.UUID;

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

    private UserRepo userRepo;

    @Autowired
    public UserApprovalListener(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @KafkaHandler(isDefault = true)
    //TODO make transaction
    public void listen(ConsumerRecord<String, String> record) {
        String messageValue = record.value();
        String messageKey = record.key();

        log.debug(messageKey + " " + messageValue);

        User userById = userRepo.getById(UUID.fromString(messageKey));

        try{
            UserStatus takenRole = UserStatus.valueOf(messageValue);
            userById.setUserStatus(takenRole);
            userRepo.save(userById);
        }
        catch(IllegalArgumentException exception){
            log.error("No status for the given role {}", messageValue);
        }
    }

}
