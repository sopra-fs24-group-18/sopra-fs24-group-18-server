package ch.uzh.ifi.hase.soprafs24.repository;

import ch.uzh.ifi.hase.soprafs24.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
  User findByUsername(String username);

  Optional<User> findById(Long id);

  User findByToken(String token);

  List<User> findAllByIdInOrderByScoreDesc(List<Long> list);
}
