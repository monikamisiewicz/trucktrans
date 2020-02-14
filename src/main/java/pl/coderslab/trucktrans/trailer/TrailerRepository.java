package pl.coderslab.trucktrans.trailer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.trucktrans.model.Trailer;

import java.util.List;
import java.util.Optional;

public interface TrailerRepository extends JpaRepository<Trailer, Long> {

//    List<Trailer> findByDriver(Driver driver);

    List<Trailer> findByMake(String make);

    List<Trailer> findByModel(String model);

    Optional<Trailer> findByRegistrationNumber(String registrationNumber);

    @Query("select t from Trailer t where t.registrationNumber like ?1%")
    List<Trailer> findByRegistrationNumberStartsWith(String registrationNumber);

    Optional<Trailer> findByVin(String vin);

    @Query("select t from Trailer t where t.vin like ?1%")
    List<Trailer> findByVinStartsWith(String vin);

    @Query("select t from Trailer t where t.vin like %?1")
    List<Trailer> findByVinEndsWith(String vin);
}
