package r.ian.ianlabtest.sec.exceptions;

import org.springframework.security.core.AuthenticationException;

/**
 * @author Melton Smith
 * @since 31.07.2022
 */
public class UserIsNotActivatedException extends AuthenticationException {

    public UserIsNotActivatedException(String msg) {
        super(msg);
    }

    public UserIsNotActivatedException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
