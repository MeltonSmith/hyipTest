package r.ian.ianlabtest.service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import r.ian.ianlabtest.data.dto.UserInfoDTO;
import r.ian.ianlabtest.sec.CustomUserDetailsManager;

/**
 * @author Melton Smith
 * @since 23.07.2021
 */
@Service
@Slf4j
public class UserService {

    private final CustomUserDetailsManager userDetailsManager;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(CustomUserDetailsManager userDetailsManager, PasswordEncoder passwordEncoder) {
        this.userDetailsManager = userDetailsManager;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Trying to save user and send its info in a single transaction. (works in a single transaction, blocking call of kafkaTemplate)
     */
    @SneakyThrows
    @Transactional(value = "transactionManager")
    public void registerUserFromDTO(UserInfoDTO userInfoDTO){
        String encoded = passwordEncoder.encode(userInfoDTO.getPassword());
        userInfoDTO.setPassword(encoded);
        userDetailsManager.createUserAndGet(userInfoDTO.toUser());

        //user and person share the same Id, async sending to kafka
        //can be null if profile is default
//        Optional.ofNullable(userApprovalService).ifPresent(approvalService -> approvalService.sendForApproval(user));
    }
}
