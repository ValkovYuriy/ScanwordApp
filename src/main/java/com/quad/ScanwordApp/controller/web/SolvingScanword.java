package com.quad.ScanwordApp.controller.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/solving")
@RequiredArgsConstructor
public class SolvingScanword {

    @GetMapping
    public String helpGet() {
        return "solvingScanword";
    }

    @PostMapping
    public String helpPost() {
        return "solvingScanword";
    }
}
