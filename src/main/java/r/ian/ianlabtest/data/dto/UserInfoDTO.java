package r.ian.ianlabtest.data.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import r.ian.ianlabtest.data.constraint.FieldMatch;
import r.ian.ianlabtest.data.domain.Person;
import r.ian.ianlabtest.data.domain.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * TODO custom validator seems to be broken
 *
 * @author Melton Smith
 * @since 23.07.2021
 */
@Data
@FieldMatch(first = "password", second = "password", message = "passwords do not match")
public class UserInfoDTO {

    @Length(min = 6, max = 20)
    private String login;

    @Length(min = 7, max = 20)
    private String password;

    @Length(min = 7, max = 20)
    private String repeatPassword;

    @NotBlank
    private String firstName;

    @NotBlank
    private String secondName;

    @NotBlank
    private String middleName;

    @Email
    @NotBlank
    private String email;

    public User toUser(){
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);

        Person person = new Person();
        person.setFirstName(firstName);
        person.setSecondName(secondName);
        person.setMiddleName(middleName);
        person.setEmail(email);
        person.setUser(user);

        user.setPerson(person);

        return user;
    }
}
