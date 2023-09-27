package com.example.vize.Controllers;


import com.example.vize.Entities.Product;
import com.example.vize.Services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class DetailController {

    final ProductService productService;

    Product product;
    @GetMapping("/detail")
    public String details(Model model){
        model.addAttribute("product",product);
        return "detail";
    }

    @GetMapping("/detail/{pid}")
    public String productDetail(@PathVariable Long pid){
        product = productService.singleProduct(pid);
        return "redirect:/detail";
    }
}
