package com.artereTest.aertereTest.repositories;
import com.artereTest.aertereTest.models.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
}
