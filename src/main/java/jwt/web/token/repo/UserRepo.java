package jwt.web.token.repo;

import jwt.web.token.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {

    Optional<User> findByUserName(String uname);
}
