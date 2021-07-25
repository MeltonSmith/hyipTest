package r.ian.ianlabtest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import r.ian.ianlabtest.data.dto.UserInfoDTO;
import r.ian.ianlabtest.data.repo.PersonRepo;
import r.ian.ianlabtest.data.repo.UserRepo;

/**
 * @author Melton Smith
 * @since 23.07.2021
 */
@Service
public class UserService {

    private final UserDetailsManager userDetailsManager;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserDetailsManager userDetailsManager, PasswordEncoder passwordEncoder) {
        this.userDetailsManager = userDetailsManager;
        this.passwordEncoder = passwordEncoder;
    }

    //TODO
//    @Transactional
    public void registerUserFromDTO(UserInfoDTO userInfoDTO){
        String encoded = passwordEncoder.encode(userInfoDTO.getPassword());
        userInfoDTO.setPassword(encoded);
        userDetailsManager.createUser(userInfoDTO.toUser());
    }
}
