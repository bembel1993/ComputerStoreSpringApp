package by.application.productcatalog.repository;

import by.application.productcatalog.model.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    @Override
    Optional<User> findById(Integer id);

    Optional<User> findByLogin(String login);

    Optional<User> findByLoginAndPassword(String login, String password);

    Optional<User> findByAccessMode(Integer accessMode);

    @Query("SELECT max(u.id) FROM User u")
    Integer findMaxId();
}
