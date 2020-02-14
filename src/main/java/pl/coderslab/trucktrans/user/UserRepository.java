package pl.coderslab.trucktrans.user;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.trucktrans.model.User;


public interface UserRepository extends JpaRepository<User, Long> {


}
