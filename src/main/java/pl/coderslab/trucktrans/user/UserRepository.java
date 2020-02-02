package pl.coderslab.mytrans.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.mytrans.model.User;

public interface UserRepository extends JpaRepository<User, Long> {


}
