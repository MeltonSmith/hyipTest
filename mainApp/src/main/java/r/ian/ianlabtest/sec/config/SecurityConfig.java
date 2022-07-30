package r.ian.ianlabtest.sec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author Melton Smith
 * @since 22.07.2021
 */
@Configuration
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                    .disable()
                .authorizeRequests()
                .antMatchers("/main", "/registration")
                        .not()
                        .authenticated()
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

        return http.build();
    }

//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                    .antMatchers("/main", "/registration").not().authenticated()
//                    .anyRequest().authenticated()
//                    .and()
//                .formLogin()
//                    .loginPage("/main")
//                    .permitAll()
//                    .usernameParameter("login")
//                    .passwordParameter("password")
//                    .failureUrl("/main?error=true")
//                    .defaultSuccessUrl("/home")
//                    .and()
//                .logout()
//                    .permitAll()
//                    .logoutSuccessUrl("/main");
//
//    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web ->
                web
                        .ignoring()
                        .antMatchers(HttpMethod.OPTIONS, "/**")
                        .antMatchers("/h2/**")
                        .antMatchers("/img/**")
                        .antMatchers("/h2-console/**")
                        .antMatchers("/css/**");
//                        .antMatchers("/app/**/*.{js,html}")
//                        .antMatchers("/test/**");
    }

//    public void configure(WebSecurity web) throws Exception {
//        web
//                .ignoring()
//                .antMatchers("/h2/**", "/img/**", "/css/**");
//    }
}
