package com.example.vize.Services;

import com.example.vize.Entities.Product;
import com.example.vize.Repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {

    final ProductRepository repository;

    public Page<Product> products(String q, int page){
        q = "%"+q+"%";
        try{
            Pageable pageable = PageRequest.of(page,10);
            Page<Product> prd = repository.findByTitleLikeIgnoreCaseOrDetailLikeIgnoreCaseOrShortDetailLike(q,q,q,pageable);
            return prd;
        }catch (Exception ex){
            System.err.println(ex.getMessage());
        }
        return null;
    }

    public List<Product> products(String q){
        q = "%"+q+"%";
        try{
            return repository.findByTitleLikeIgnoreCaseOrDetailLikeIgnoreCase(q,q);
        }catch (Exception ex){
            System.err.println(ex.getMessage());
        }
        return null;
    }

}
