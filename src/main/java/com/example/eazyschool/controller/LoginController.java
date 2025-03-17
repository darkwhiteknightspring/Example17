package com.example.eazyschool.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class LoginController {
    @RequestMapping(value="/login",method={
        RequestMethod.GET,RequestMethod.POST
    })
    public String displayLoginPage(Model model,@RequestParam(value="error",required=false) String error,@RequestParam(value="logout",required=false)String logout){
        String errorMessage = null;
        if(error!=null){
            errorMessage="Username or Password is incorrect !!";
        }
        if(logout!=null){
            errorMessage="You have been logged out successfully";
        }
        log.info(errorMessage);
        model.addAttribute("errorMessage",errorMessage);
        return "login.html";
    }

    /*

        The security context holder holds al the details after successful authentication of the user takes place.

     */

    @RequestMapping(value="/logout",method=RequestMethod.GET)
    public String logoutPage(HttpServletRequest request,HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth!=null){
            new SecurityContextLogoutHandler().logout(request,response,auth);
        }
        return "redirect:/login?logout=true";
    }

}
