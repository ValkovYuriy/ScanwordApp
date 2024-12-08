package com.quad.ScanwordApp.controller;

import com.quad.ScanwordApp.service.ScanwordService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/create_api")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class CreateController
{
    private final ScanwordService scanwordService;

}
