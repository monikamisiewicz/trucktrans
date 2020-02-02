package pl.coderslab.trucktrans.company;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.trucktrans.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
