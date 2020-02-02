package pl.coderslab.trucktrans.truck;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.trucktrans.model.Truck;

import java.util.List;
import java.util.Optional;

public interface TruckRepository extends JpaRepository<Truck,  Long> {

//    List<Truck> findByDriver(Driver driver);

    List<Truck> findByMake(String make);

    List<Truck> findByModel(String model);

    Optional<Truck> findByRegistrationNumber(String registrationNumber);

    @Query("select t from Truck t where t.registrationNumber like ?1%")
    List<Truck> findByRegistrationNumberStartsWith(String registrationNumber);

    Optional<Truck> findByVin(String vin);

    @Query("select t from Truck t where t.vin like ?1%")
    List<Truck> findByVinStartsWith(String vin);

    @Query("select t from Truck t where t.vin like %?1")
    List<Truck> findByVinEndsWith(String vin);
}
