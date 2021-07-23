package r.ian.ianlabtest.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

/**
 * @author Melton Smith
 * @since 21.07.2021
 */
@Entity
@Table(name = "user_t")
@Data
public class User {

    public User() {
    }

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )

//    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Length(min = 6, max = 20)
    private String login;

    @Length(min = 7, max = 20)
    private String password;


}
