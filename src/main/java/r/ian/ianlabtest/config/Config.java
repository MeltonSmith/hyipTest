package r.ian.ianlabtest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import r.ian.ianlabtest.sec.CustomUserDetailsManager;
import r.ian.ianlabtest.service.UserService;

/**
 * Used when I don't have a kafka online
 *
 * @author Melton Smith
 * @since 29.07.2021
 */
//@Configuration
//@Profile("default")
public class Config {

    @Bean
    public UserService userService(CustomUserDetailsManager customUserDetailsManager, PasswordEncoder passwordEncoder){
        return new UserService(customUserDetailsManager, passwordEncoder);
    }

}