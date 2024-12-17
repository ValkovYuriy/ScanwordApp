package com.quad.ScanwordApp.controller.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/select-scanword")
@RequiredArgsConstructor
public class WebSelectController {

    @GetMapping
    public String selectScanword() {
        return "select-scanword";
    }

}
