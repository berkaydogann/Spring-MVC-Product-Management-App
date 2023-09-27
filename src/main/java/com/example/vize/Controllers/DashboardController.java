package com.example.vize.Controllers;


import com.example.vize.Entities.Product;
import com.example.vize.Services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class DashboardController {

    final ProductService productService;
    final HttpServletRequest request;

    @GetMapping("/dashboard")
    public String dashboard(Model model){
        List<Product> products = productService.products();
        model.addAttribute("products",products);
        if(request.getSession().getAttribute("user") != null){
            return "dashboard";
        }else{
            return "redirect:/login";
        }

    }

    @PostMapping("/productSave")
    public String saveProduct(Product product){
        productService.productSave(product);
        return "redirect:/dashboard";
    }

    @GetMapping("/productDelete/{pid}")
    public String productDelete(@PathVariable String pid){
        productService.delete(pid);
        return "redirect:/dashboard";
    }

}
