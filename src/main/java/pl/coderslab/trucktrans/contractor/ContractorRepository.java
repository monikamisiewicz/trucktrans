package pl.coderslab.trucktrans.contractor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.trucktrans.model.Contractor;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ContractorRepository extends JpaRepository<Contractor, Long> {

    Optional<Contractor> findByName(String name);

    @Query("select c from Contractor c where c.name like ?1%")
    List<Contractor> findByNameStartsWith(String name);

    Optional<Contractor> findByNip(String nip);
}
