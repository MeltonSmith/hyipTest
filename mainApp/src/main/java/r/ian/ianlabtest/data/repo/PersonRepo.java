package r.ian.ianlabtest.data.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import r.ian.ianlabtest.data.domain.Person;

import java.util.UUID;

/**
 * @author Melton Smith
 * @since 25.07.2021
 */
@Repository
public interface PersonRepo extends CrudRepository<Person, UUID> {
}
