package pl.coderslab.trucktrans.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.trucktrans.model.*;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByOrderNumber(String orderNumber);

    List<Order> findByDrivers(Driver driver);

    List<Order> findByContractor(Contractor contractor);

    List<Order> findByTractor(Tractor tractor);

    List<Order> findByTrailer(Trailer trailer);

    Optional<Order> findByDate(LocalDate date);

    @Query("SELECT o FROM Order o WHERE o.date BETWEEN ?1 AND ?2")
    Set<Order> findOrderByDateBetween(LocalDate start, LocalDate end);

}
