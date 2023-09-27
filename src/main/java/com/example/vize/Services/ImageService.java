package com.example.vize.Services;


import com.example.vize.Entities.ProductImage;
import com.example.vize.Repositories.ProductImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ImageService {

    final ProductImageRepository repository;

    public ProductImage addImage(ProductImage productImage){
        repository.save(productImage);
        return productImage;
    }

    public List<ProductImage> list(Long pid){
        return repository.findByPidEquals(pid);
    }

    public boolean delete(Long pid){
        try{
            repository.deleteByPidEquals(pid);
            return true;
        }catch (Exception ex){
            System.err.println(ex.getMessage());
        }
        return false;
    }
}
