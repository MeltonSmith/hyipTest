package r.ian.ianlabtest.sec;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import r.ian.ianlabtest.data.domain.User;
import r.ian.ianlabtest.data.repo.UserRepo;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.Collectors;

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
        return this.getUserById(UUID.fromString(id));
    }

    public User getUserByIdWithoutDelimiters(String id) {

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

//    @Override
//    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
//        UserDetails userDetails = userRepo.getByLogin(login);
//        if (userDetails == null){
//            log.debug("Can not authenticate");
//            throw new UsernameNotFoundException("Wrong credentials");
//        }
//
//        return userDetails;
//    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String login) {
        log.debug("Authenticating {}", login);

//        if (new EmailValidator().isValid(login, null)) {
            return userRepo
                    .findUserWithAuthoritiesByLogin(login)
                    .map(user -> createSpringSecurityUser(login, user))
                    .orElseThrow(() -> new UsernameNotFoundException("User " + login + " was not found in the database"));
//        }

//        String lowercaseLogin = login.toLowerCase(Locale.ENGLISH);
//        return userRepository
//                .findOneWithAuthoritiesByLogin(lowercaseLogin)
//                .map(user -> createSpringSecurityUser(lowercaseLogin, user))
//                .orElseThrow(() -> new UsernameNotFoundException("User " + lowercaseLogin + " was not found in the database"));
    }

    private org.springframework.security.core.userdetails.User createSpringSecurityUser(String lowercaseLogin, User user) {
//        if (!user.isActivated()) {
//            throw new UserNotActivatedException("User " + lowercaseLogin + " was not activated");
//        }
        List<GrantedAuthority> grantedAuthorities = user
                .getAuthorities()
                .stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), grantedAuthorities);
    }


    /**
     * Takes users which are unsent to kafka after registration
     */
    public Collection<User> getUnsent(){
        return userRepo.getByRole(REGISTERED);
    }
}
