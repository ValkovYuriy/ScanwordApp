package com.quad.ScanwordApp.controller.web;

import com.quad.ScanwordApp.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@RequiredArgsConstructor
@Controller
public class AuthController
{
    private final AuthService authService;

    @GetMapping("/")
    public String index(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
            model.addAttribute("isAuthenticated", true);
            model.addAttribute("username", auth.getName());
            if(Objects.equals(auth.getName(), "admin"))
            {
                model.addAttribute("isAdmin", true);
            }
            else
            {
                model.addAttribute("isUser", true);
            }
        } else {
            model.addAttribute("isAuthenticated", false);
        }
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password) {
        if(authService.register(username, password))
        {
            return "redirect:/login";
        }
        else return "redirect:/register?error";
    }

}
