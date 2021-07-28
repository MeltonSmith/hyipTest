package r.ian.ianlabtest.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import r.ian.ianlabtest.data.domain.User;
import r.ian.ianlabtest.sec.CustomUserDetailsManager;
import r.ian.ianlabtest.sec.role.UserRole;

import java.util.concurrent.TimeUnit;

/**
 * @author Melton Smith
 * @since 29.07.2021
 */
@Service
@Slf4j
public class UserApprovalService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final CustomUserDetailsManager customUserDetailsManager;

    @Autowired
    public UserApprovalService(KafkaTemplate<String, String> kafkaTemplate, CustomUserDetailsManager customUserDetailsManager) {
        this.kafkaTemplate = kafkaTemplate;
        this.customUserDetailsManager = customUserDetailsManager;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Async
    public void sendForApproval(User user){
        try {
            kafkaTemplate.send("hyipTestUser", user.getId().toString(), user.getPerson().getEmail()).get(10, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("Couldn't send message to kafka");
            throw new RuntimeException(e);
        }
        user.setUserRole(UserRole.SENT);
        customUserDetailsManager.updateUser(user);
    }
}
