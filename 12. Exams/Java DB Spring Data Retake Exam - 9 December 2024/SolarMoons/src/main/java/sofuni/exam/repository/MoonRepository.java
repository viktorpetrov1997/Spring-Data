package sofuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sofuni.exam.models.entity.Moon;
import sofuni.exam.models.enums.Type;

import java.util.List;
import java.util.Optional;

@Repository
public interface MoonRepository extends JpaRepository<Moon, Long>
{
    Optional<Moon> findMoonByName(String name);
    List<Moon> findMoonsByPlanetTypeAndRadiusBetweenOrderByName(Type planetType, double radius, double radius2);
}
