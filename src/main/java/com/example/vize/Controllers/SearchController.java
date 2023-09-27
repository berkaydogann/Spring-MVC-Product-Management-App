package com.example.vize.Controllers;


import com.example.vize.Entities.Product;
import com.example.vize.Services.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class SearchController {

    final SearchService service;

    @GetMapping("/search")
        public String search(@RequestParam(defaultValue = "") String q, Model model,@RequestParam(defaultValue = "0") int page ){
        Page<Product> products = service.products(q,page);
        model.addAttribute("product",products);
        model.addAttribute("q",q);
        return "search";
        }

}
