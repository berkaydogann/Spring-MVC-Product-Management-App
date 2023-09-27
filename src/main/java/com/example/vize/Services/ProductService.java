package com.example.vize.Services;


import com.example.vize.Entities.Product;
import com.example.vize.Repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ProductService {

    final ProductRepository productRepository;

   public List<Product> products(){
       return productRepository.findAll();
    }

   public Page<Product> someProducts(){
        return productRepository.findAll(PageRequest.of(0,10, Sort.by(Sort.Direction.DESC,"pid")));
    }

    public boolean delete(String pid){
       try{
           Long lPid = Long.parseLong(pid);
           productRepository.deleteById(lPid);
           return true;
       }catch (Exception ex){
           System.err.println(ex.getMessage());
       }
       return false;
    }

    public boolean productSave(Product product){
       try {
           productRepository.save(product);
           return true;
       }catch (Exception ex){
           System.err.println(ex.getMessage());
       }
       return false;
    }

    public Product singleProduct(Long pid){
        Optional<Product> product = productRepository.findById(pid);
       try {
          if(product.isPresent()){
              return product.get();
          }
       }catch (Exception ex){
           System.err.println(ex.getMessage());
       }
       return null;
    }
}
