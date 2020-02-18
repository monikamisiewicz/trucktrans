package pl.coderslab.trucktrans.item;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.trucktrans.model.Item;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item,Long> {

    Optional<Item> deleteAllByInvoiceId (Long id);
}
