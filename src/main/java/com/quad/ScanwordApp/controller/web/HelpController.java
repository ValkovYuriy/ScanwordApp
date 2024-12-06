package com.quad.ScanwordApp.controller.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/help")
@RequiredArgsConstructor
public class HelpController {

    @GetMapping
    public String helpGet(){
        return "help";
    }
    @PostMapping
    public String helpPost(){
        return "help";
    }
}
