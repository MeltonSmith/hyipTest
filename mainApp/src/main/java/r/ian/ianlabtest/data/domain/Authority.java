package r.ian.ianlabtest.data.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * @author Melton Smith
 * @since 30.07.2022
 */
@Entity
@Table(name = "authority_t")
@Getter
@Setter
@EqualsAndHashCode
public class Authority {

    @Id
    @Size(max = 30)
    @Column(length = 30)
    private String name;

}
