package com.example.vize.Repositories;


import com.example.vize.Entities.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;


public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
    List<ProductImage> findByPidEquals(Long pid);

    @Transactional
    long deleteByPidEquals(Long pid);

}