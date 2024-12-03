package com.quad.ScanwordApp.controller.web;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/input-parameters")
@RequiredArgsConstructor
public class InputParametersController {

    @GetMapping
    public String inputParameters() {
        return "input-parameters";
    }
}
