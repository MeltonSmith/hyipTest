package r.ian.ianlabtest.sec;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import r.ian.ianlabtest.data.domain.User;
import r.ian.ianlabtest.data.repo.UserRepo;

import java.math.BigInteger;
import java.util.Collection;
import java.util.UUID;

import static r.ian.ianlabtest.sec.role.UserRole.REGISTERED;

/**
 * @author Melton Smith
 * @since 25.07.2021
 */
@Service
@Slf4j
public class CustomUserDetailsManager implements UserDetailsManager {

    private final UserRepo userRepo;

    @Autowired
    public CustomUserDetailsManager(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User createUserAndGet(UserDetails details) {
        return userRepo.save((User) details);
    }

    @Override
    public void createUser(UserDetails details) {
       createUserAndGet(details);
    }

    @Override
    public void updateUser(UserDetails details) {
        userRepo.save(((User) details));
    }

    public User getUserById(String id) {

        UUID uuid = new UUID(
                new BigInteger(id.substring(0, 16), 16).longValue(),
                new BigInteger(id.substring(16), 16).longValue());

        return this.getUserById(uuid);
    }

    public User getUserById(UUID id) {
        return userRepo.getById(id);
    }

    @Override
    public void deleteUser(String login) {
        userRepo.deleteUserByLogin(login);
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) { }

    @Override
    public boolean userExists(String login) {
        return userRepo.existsByLogin(login);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserDetails userDetails = userRepo.getByLogin(login);
        if (userDetails == null){
            log.debug("Can not authenticate");
            throw new UsernameNotFoundException("Wrong credentials");
        }

        return userDetails;
    }

    /**
     * Takes users which are unsent to kafka after registration
     */
    public Collection<User> getUnsent(){
        return userRepo.getByRole(REGISTERED);
    }
}
