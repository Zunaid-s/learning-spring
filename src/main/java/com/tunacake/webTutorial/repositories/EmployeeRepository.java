package com.tunacake.webTutorial.repositories;


import com.tunacake.webTutorial.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository /* This will tell Spring that this is a JPA repository. */
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, UUID> {
    Optional<EmployeeEntity> findById(UUID id);

    boolean existsById(UUID id);

    void deleteById(UUID id);
/*the JPA repository is used to interact with the database, and we have stated
that the entity is of type EmployeeEntity and the primary key is of type Long*/
}
