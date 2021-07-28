package r.ian.ianlabtest.service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import r.ian.ianlabtest.data.domain.User;
import r.ian.ianlabtest.data.dto.UserInfoDTO;
import r.ian.ianlabtest.sec.CustomUserDetailsManager;

import java.util.concurrent.TimeUnit;

/**
 * @author Melton Smith
 * @since 23.07.2021
 */
@Service
@Slf4j
public class UserService {

    private final CustomUserDetailsManager userDetailsManager;

    private final PasswordEncoder passwordEncoder;

    private final KafkaTemplate<String, String> userKafkaTemplate;

    @Autowired
    public UserService(CustomUserDetailsManager userDetailsManager, PasswordEncoder passwordEncoder, KafkaTemplate<String, String> userKafkaTemplate) {
        this.userDetailsManager = userDetailsManager;
        this.passwordEncoder = passwordEncoder;
        this.userKafkaTemplate = userKafkaTemplate;
    }

    /**
     * Trying to save user and send its info in a single transaction
     */
    @SneakyThrows
    @Transactional(value = "transactionManager")
//    @Async
    public void registerUserFromDTO(UserInfoDTO userInfoDTO){
        String encoded = passwordEncoder.encode(userInfoDTO.getPassword());
        userInfoDTO.setPassword(encoded);
        User user = userDetailsManager.createUserAndGet(userInfoDTO.toUser());

        //user and person share the same Id

        userKafkaTemplate.send("hyipTestUser", user.getId().toString(), user.getPerson().getEmail()).get(10, TimeUnit.SECONDS);
    }
}
