package SpringMapping.repository;

import SpringMapping.DBEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DBEmployeeRepository extends JpaRepository<DBEmployee, Long>
{
    List<DBEmployee> findByBirthdayBeforeOrderBySalaryDesc(LocalDate date);
}
