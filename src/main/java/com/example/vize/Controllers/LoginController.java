package com.example.vize.Controllers;


import com.example.vize.Entities.User;
import com.example.vize.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LoginController {

    final HttpServletRequest request;
    final UserService service;

    int status = 0;

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("status",status);
        status = 0;
        return "login";
    }

    @PostMapping("/loginUser")
    public String login(User user){
        User u = service.userLogin(user);
        if(u != null){
            request.getSession().setAttribute("user",""+u.getUid());
            return "redirect:/dashboard";
        }
        status = 1;
       return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(){
        request.getSession().removeAttribute("user");
        return "redirect:/";
    }
}
