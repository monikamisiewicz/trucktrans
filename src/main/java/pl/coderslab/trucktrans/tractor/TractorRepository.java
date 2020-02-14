package pl.coderslab.trucktrans.truck;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.trucktrans.model.Tractor;

import java.util.List;
import java.util.Optional;

public interface TruckRepository extends JpaRepository<Tractor,  Long> {

//    List<Truck> findByDriver(Driver driver);

    List<Tractor> findByMake(String make);

    List<Tractor> findByModel(String model);

    Optional<Tractor> findByRegistrationNumber(String registrationNumber);

    @Query("select t from Tractor t where t.registrationNumber like ?1%")
    List<Tractor> findByRegistrationNumberStartsWith(String registrationNumber);

    Optional<Tractor> findByVin(String vin);

    @Query("select t from Tractor t where t.vin like ?1%")
    List<Tractor> findByVinStartsWith(String vin);

    @Query("select t from Tractor t where t.vin like %?1")
    List<Tractor> findByVinEndsWith(String vin);
}
