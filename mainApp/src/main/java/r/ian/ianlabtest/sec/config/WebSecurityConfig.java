package r.ian.ianlabtest.sec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author Melton Smith
 * @since 22.07.2021
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig  {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)  throws Exception {
        http
                .csrf().disable()
                .authorizeRequests(request -> {
                    request.requestMatchers("/main", "/registration", "/h2/**", "/img/**", "/css/**").permitAll()
                            .anyRequest().authenticated();
                })
//                    .antMatchers("/main", "/registration").not().authenticated()
//                    .anyRequest().authenticated()
//                    .and()
                .formLogin((form) -> {
                    form.loginPage("/main")
                            .permitAll()
                            .usernameParameter("login")
                            .passwordParameter("password")
                            .failureUrl("/main?error=true")
                            .defaultSuccessUrl("/home");
                })
                .logout(logout -> logout.permitAll()
                        .logoutSuccessUrl("/main"));

        return http.build();

    }

//    public void configure(WebSecurity web) throws Exception {
//        web
//                .ignoring()
//                .antMatchers("/h2/**", "/img/**", "/css/**");
//    }
}
