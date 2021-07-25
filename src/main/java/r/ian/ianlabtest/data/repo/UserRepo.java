package r.ian.ianlabtest.data.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import r.ian.ianlabtest.data.domain.User;

import java.util.UUID;

/**
 * @author Melton Smith
 * @since 23.07.2021
 */
@Repository
public interface UserRepo extends CrudRepository<User, UUID> {

    @Query("delete from User u where u.login = :login")
    void deleteUserByLogin(@Param("login") String login);

    @Query("select case when count(u)> 0 then true else false end from User u where u.login = :login")
    boolean existsByLogin(@Param("login") String login);

    @Query("select u from User u where u.login = :login")
    UserDetails getByLogin(String login);
}
