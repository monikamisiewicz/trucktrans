package pl.coderslab.trucktrans.invoice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.trucktrans.model.Contractor;
import pl.coderslab.trucktrans.model.Invoice;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface InvoiceRepository extends JpaRepository<Invoice,Long> {

    List<Invoice> findByContractor(Contractor contractor);

    List<Invoice> findByInvoiceNumberContaining(String number);

    Optional<Invoice> findByInvoiceNumber(String number);

    Optional<Invoice> findByInvoiceDate(LocalDate date);

    @Query("SELECT i FROM Invoice i WHERE i.invoiceDate BETWEEN ?1 AND ?2")
    Set<Invoice> findInvoiceByDateBetween(LocalDate start, LocalDate end);

    List<Invoice> findByPaymentMethod(String method);


}
