package by.application.productcatalog.repository;

import by.application.productcatalog.model.entity.Message;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CrudRepository<Message, Integer> {

    Iterable<Message> findAllByUserId(Integer userId);

    Iterable<Message> findAllByStatus(String status);

    @Query("SELECT max(m.id) FROM Message m")
    Integer findMaxId();
}
