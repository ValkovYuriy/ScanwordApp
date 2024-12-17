package com.quad.ScanwordApp.controller.web;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/work-with-dictionaries")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class WorkWithDictionariesController {

    @GetMapping
    public String workWithDictionaries(){
        return "work-with-dictionaries";
    }
}
