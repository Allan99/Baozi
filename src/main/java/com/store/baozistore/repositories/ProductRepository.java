package com.store.baozistore.repositories;

import com.store.baozistore.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Produto, Long> {
}
