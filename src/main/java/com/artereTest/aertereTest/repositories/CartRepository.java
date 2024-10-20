package com.artereTest.aertereTest.repositories;
import com.artereTest.aertereTest.models.Cart;
import com.artereTest.aertereTest.models.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {

}

