package r.ian.ianlabtest.data.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import r.ian.ianlabtest.data.domain.Authority;

/**
 * @author Melton Smith
 * @since 30.07.2022
 */
@Repository
public interface AuthorityRepo extends JpaRepository<Authority, String> {}
