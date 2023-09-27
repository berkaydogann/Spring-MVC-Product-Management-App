package com.example.vize.Configs;

import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class FilterConfig implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String url = request.getRequestURI();
        String freeUrls[] = {"/","/login","/search","/loginUser","/search","/detail","/detail/{pid}"};
        boolean loginStatus = true;
        for(String item : freeUrls){
            if(url.equals(item)){
                loginStatus = false;
                break;
            }
        }
       if(loginStatus){
           boolean status = request.getSession().getAttribute("user") == null;
           if(status){
               response.sendRedirect("/login");
           }else{

               filterChain.doFilter(request,response);
           }
       }else{
           filterChain.doFilter(request,response);
       }
    }
}
