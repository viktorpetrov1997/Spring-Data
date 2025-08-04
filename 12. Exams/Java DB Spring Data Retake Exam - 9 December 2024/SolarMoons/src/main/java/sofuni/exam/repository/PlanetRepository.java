package sofuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sofuni.exam.models.entity.Planet;

import java.util.Optional;

@Repository
public interface PlanetRepository extends JpaRepository<Planet, Long>
{
    Optional<Planet> findPlanetByName(String name);
}
