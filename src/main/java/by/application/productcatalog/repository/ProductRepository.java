package by.application.productcatalog.repository;

import by.application.productcatalog.model.entity.Product;
import by.application.productcatalog.model.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

    @Override
    Optional<Product> findById(Integer id);

    Optional<Product> findByName(String name);

    Iterable<Product> findAllByCategoryId(Integer categoryId);

    @Query("SELECT p FROM Product p WHERE p.name LIKE %:name% ")
    List<Product> findAllByName(@Param("name") String name);
}