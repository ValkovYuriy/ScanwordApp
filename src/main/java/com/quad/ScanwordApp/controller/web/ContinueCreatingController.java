package com.quad.ScanwordApp.controller.web;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/continue-creation")
@PreAuthorize("hasRole('ADMIN')")
public class ContinueCreatingController {
    @GetMapping
    public String continueCreating() {
        return "continue-creation";
    }
}
