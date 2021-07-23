package r.ian.ianlabtest.data.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import r.ian.ianlabtest.data.domain.User;

import java.util.UUID;

/**
 * @author Melton Smith
 * @since 23.07.2021
 */
@Repository
public interface UserRepo extends CrudRepository<User, UUID> {
}
