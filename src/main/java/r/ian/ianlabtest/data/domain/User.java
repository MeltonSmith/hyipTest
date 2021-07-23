package r.ian.ianlabtest.data.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

/**
 * @author Melton Smith
 * @since 21.07.2021
 */
@Entity
@Table(name = "user_t")
@Data
@EqualsAndHashCode
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @NotBlank
    private String login;

    @NotBlank
    private String password;

    @NotBlank
    private String firstName;

    @NotBlank
    private String secondName;

    @NotBlank
    private String middleName;

    @Email
    private String email;


}
