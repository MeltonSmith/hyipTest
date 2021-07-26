package r.ian.ianlabtest.sec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import r.ian.ianlabtest.data.domain.User;
import r.ian.ianlabtest.data.repo.UserRepo;

/**
 * @author Melton Smith
 * @since 25.07.2021
 */
@Service
public class CustomUserDetailsManager implements UserDetailsManager {

    UserRepo userRepo;

    @Autowired
    public CustomUserDetailsManager(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public void createUser(UserDetails details) {
        userRepo.save((User) details);
    }

    @Override
    public void updateUser(UserDetails details) {
        userRepo.save(((User) details));
    }

    @Override
    public void deleteUser(String login) {
        userRepo.deleteUserByLogin(login);
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        //TODO notImplemented
    }

    @Override
    public boolean userExists(String login) {
        return userRepo.existsByLogin(login);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return userRepo.getByLogin(login);
    }
}
