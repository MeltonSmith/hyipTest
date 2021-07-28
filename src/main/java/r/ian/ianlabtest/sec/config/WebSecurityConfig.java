package r.ian.ianlabtest.sec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Melton Smith
 * @since 22.07.2021
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/main", "/registration").not().authenticated()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/main")
                    .permitAll()
                    .usernameParameter("login")
                    .passwordParameter("password")
                    .failureUrl("/main?error=true")
                    .defaultSuccessUrl("/home")
                    .and()
                .logout()
                    .permitAll()
                    .logoutSuccessUrl("/main");

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/h2/**", "/img/**", "/css/**");
    }
}
