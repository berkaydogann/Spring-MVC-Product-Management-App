package com.example.vize.Repositories;


import com.example.vize.Entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByTitleLikeIgnoreCaseOrDetailLikeIgnoreCase(String title, String detail);

    Page<Product> findByTitleLikeIgnoreCaseOrDetailLikeIgnoreCaseOrShortDetailLike(String title, String detail, String shortDetail, Pageable pageable);


}