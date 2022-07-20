package r.ian.ianlabtest.sec;


import org.springframework.security.core.Authentication;

/**
 * @author Melton Smith
 * @since 26.07.2021
 */
public interface AuthenticationFacade {
    Authentication getAuthentication();
}
