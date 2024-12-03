package com.quad.ScanwordApp.controller.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/start")
@RequiredArgsConstructor
public class SelectScanwordController
{
    @GetMapping
    public String start() {
        return "start";
    }
}
