package pl.coderslab.trucktrans.driver;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.trucktrans.model.Driver;


import java.util.List;
import java.util.Optional;

public interface DriverRepository extends JpaRepository<Driver, Long> {

    List<Driver> findByFirstName(String firstName);

    List<Driver> findByLastName(String lastName);

    @Query("select d from Driver d where  d.lastName like %?1% ")
    List<Driver> findByLastNameContaining(String name);

    Optional<Driver> findByPesel(String pesel);
}
