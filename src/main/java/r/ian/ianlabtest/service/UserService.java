package r.ian.ianlabtest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import r.ian.ianlabtest.data.dto.UserDTO;
import r.ian.ianlabtest.data.repo.UserRepo;

/**
 * @author Melton Smith
 * @since 23.07.2021
 */
@Service
public class UserService {

    private final UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    //TODO
//    @Transactional
    public void registerUserFromDTO(UserDTO userDTO){
        String encoded = passwordEncoder.encode(userDTO.getPassword());
        userDTO.setPassword(encoded);
        userRepo.save(userDTO.toUser());
    }
}
