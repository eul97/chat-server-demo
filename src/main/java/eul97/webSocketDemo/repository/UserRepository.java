package eul97.webSocketDemo.repository;

import eul97.webSocketDemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<String, User> {
}
