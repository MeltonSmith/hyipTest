package r.ian.ianlabtest.data.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * @author Melton Smith
 * @since 21.07.2021
 */
@Entity
@Table(name = "user_t")
@Getter
@Setter
@EqualsAndHashCode
//public class User implements UserDetails {
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
//    @Column(name = "id", nullable = false)
    private UUID id;

    @NotBlank
    @Column(unique=true)
    private String login;

    @NotBlank
    private String password;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "person_id")
    @MapsId
    private Person person;

    @JsonIgnore
    @ManyToMany
//    @JoinTable(
//            name = "user_authority_t",
//            joinColumns = @JoinColumn(name = "user_uuid", referencedColumnName = "authority_t_id"))
////            inverseJoinColumns = { @JoinColumn(name = "authority_name", referencedColumnName = "name") }
////    )
    @JoinTable(
            name = "user_authority_t",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "name"))
    @BatchSize(size = 20)
    private Set<Authority> authorities = new HashSet<>();

    @NotNull
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus = UserStatus.REGISTERED;

    public boolean isNotActivated(){
        return userStatus.equals(UserStatus.SENT) || userStatus.equals(UserStatus.REGISTERED);
    }


//    @Override
//    public String getUsername() {
//        return login;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
}
