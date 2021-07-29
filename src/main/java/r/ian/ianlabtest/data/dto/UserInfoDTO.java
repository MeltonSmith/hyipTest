package r.ian.ianlabtest.data.dto;

import lombok.Data;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import r.ian.ianlabtest.data.constraint.FieldMatch;
import r.ian.ianlabtest.data.domain.Person;
import r.ian.ianlabtest.data.domain.User;
import r.ian.ianlabtest.sec.role.UserRole;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * TODO custom validator seems to be broken
 *
 * @author Melton Smith
 * @since 23.07.2021
 */
@Data
@FieldMatch(first = "password", second = "password", message = "passwords do not match")
public class UserInfoDTO {

    public UserInfoDTO() {
    }

    public UserInfoDTO(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.repeatPassword = user.getPassword();
        this.userRole = user.getUserRole();
        this.firstName = user.getPerson().getFirstName();
        this.secondName = user.getPerson().getSecondName();
        this.middleName = user.getPerson().getMiddleName();
        this.email = user.getPerson().getEmail();
    }

    private UUID id;

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

    @NotNull
    @Getter
    private UserRole userRole = UserRole.REGISTERED;

    public User toUser(){
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setUserRole(userRole);

        Person person = new Person();
        person.setFirstName(firstName);
        person.setSecondName(secondName);
        person.setMiddleName(middleName);
        person.setEmail(email);
        person.setUser(user);

        user.setPerson(person);

        return user;
    }

    public String getFullNameTrunc(){
        return secondName + " " + firstName.charAt(0) + ". " + middleName.charAt(0) +".";
    }
}
