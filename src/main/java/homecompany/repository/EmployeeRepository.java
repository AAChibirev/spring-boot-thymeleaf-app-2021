package homecompany.repository;

import homecompany.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {


    List<Employee> findByName(String name);

    @Query(value = "select * from employee e where e.firstname = :name", nativeQuery = true)
    List<Employee> retrieveByName(@Param("name") String name);

    @Query(value = "select * from employee e where e.firstname = :firstname and e.lastname = :lastname"
            , nativeQuery = true)
    List<Employee> findByNameAndSurname(@Param("firstname") String name, @Param("lastname") String surname);

}
