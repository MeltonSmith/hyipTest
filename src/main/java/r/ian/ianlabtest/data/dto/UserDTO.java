package r.ian.ianlabtest.data.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import r.ian.ianlabtest.data.constraint.FieldMatch;
import r.ian.ianlabtest.data.domain.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author Melton Smith
 * @since 23.07.2021
 */
@Data
@FieldMatch(first = "password", second = "password", message = "passwords do not match")
public class UserDTO {

    @Length(min = 6, max = 20)
    private String login;

    @Length(min = 6, max = 20)
    private String password;

    @Length(min = 6, max = 20)
    private String repeatPassword;

    @NotBlank
    private String firstName;

    @NotBlank
    private String secondName;

    @NotBlank
    private String middleName;

    @Email
    private String email;

    public User toUser(){
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setSecondName(secondName);
        user.setMiddleName(middleName);
        user.setEmail(email);

        return user;
    }
}
