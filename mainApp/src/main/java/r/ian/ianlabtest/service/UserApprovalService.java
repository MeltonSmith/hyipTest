package r.ian.ianlabtest.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaFailureCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.concurrent.ListenableFuture;
import r.ian.ianlabtest.data.domain.User;
import r.ian.ianlabtest.sec.CustomUserDetailsManager;
import r.ian.ianlabtest.sec.role.UserRole;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 * @author Melton Smith
 * @since 29.07.2021
 */
@Service
@Slf4j
@Profile("kafka")
public class UserApprovalService {

    @Value("${spring.kafka.approvalTopic}")
    private String approvalTopic;

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final CustomUserDetailsManager customUserDetailsManager;

    @Autowired
    public UserApprovalService(KafkaTemplate<String, String> kafkaTemplate, CustomUserDetailsManager customUserDetailsManager) {
        this.kafkaTemplate = kafkaTemplate;
        this.customUserDetailsManager = customUserDetailsManager;
    }

    /**
     * Non-blocking send for approval for each new user
     */
    public void sendForApproval(User user){
        String uuid = user.getId().toString();
        var future = kafkaTemplate.send(approvalTopic, uuid, uuid);

        future.addCallback(result -> {
                    user.setUserRole(UserRole.SENT);
                    customUserDetailsManager.updateUser(user);
                },
                (KafkaFailureCallback<Integer, String>) ex -> {
                    ProducerRecord<Object, Object> failedProducerRecord = ex.getFailedProducerRecord();
                    log.error("Couldn't send message to approval system, the record key is "
                            + failedProducerRecord.key()
                            + ", value: " + failedProducerRecord.value());
                }
        );
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW,
            isolation = Isolation.READ_COMMITTED)
    @Scheduled(fixedDelay = 60000, initialDelay = 5000)
    public void sendForApproval(){
        Collection<User> unsent = customUserDetailsManager.getUnsent();

        for (User user : unsent) {
            //no new transaction no async from calling here
            sendForApproval(user);
        }

        if (!unsent.isEmpty())
            log.debug("{} user records were successfully sent to your messaging system", unsent.size());
    }
}
