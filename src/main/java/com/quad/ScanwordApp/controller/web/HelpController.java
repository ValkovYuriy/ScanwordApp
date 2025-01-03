package com.quad.ScanwordApp.controller.web;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/help")
@RequiredArgsConstructor
public class HelpController {

    @GetMapping
    public String helpGet(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken))
        {
            model.addAttribute("isAuthenticated", true);
            if(auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")))
            {
                model.addAttribute("isAdmin", true);
                model.addAttribute("isUser", false);
            }
            else {
                model.addAttribute("isUser", true);
                model.addAttribute("isAdmin", false);
            }
        }
        else {
            model.addAttribute("isAuthenticated", false);
            model.addAttribute("isUser", false);
            model.addAttribute("isAdmin", false);
        }
        return "help";
    }
    @PostMapping
    public String helpPost(){
        return "help";
    }
}
