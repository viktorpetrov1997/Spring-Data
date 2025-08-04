package sofuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sofuni.exam.models.entity.Discoverer;

import java.util.Optional;

@Repository
public interface DiscovererRepository extends JpaRepository<Discoverer, Long>
{
    Optional<Discoverer> findByFirstNameAndLastName(String firstName, String lastName);
}
