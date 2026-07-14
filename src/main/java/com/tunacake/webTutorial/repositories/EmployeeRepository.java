package com.tunacake.webTutorial.repositories;


import com.tunacake.webTutorial.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository /* This will tell Spring that this is a JPA repository. */
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
/*the JPA repository is used to interact with the database, and we have stated
that the entity is of type EmployeeEntity and the primary key is of type Long*/
}
