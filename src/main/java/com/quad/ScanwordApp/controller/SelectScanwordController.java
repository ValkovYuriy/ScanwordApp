package com.quad.ScanwordApp.controller;

import com.quad.ScanwordApp.dto.ResponseDto;
import com.quad.ScanwordApp.dto.SelectScanwordDto;
import com.quad.ScanwordApp.service.SelectScanwordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/select-api")
@RequiredArgsConstructor
public class SelectScanwordController {

    private final SelectScanwordService selectScanwordService;


    @GetMapping(params = "type")
    public ResponseEntity<ResponseDto<List<SelectScanwordDto>>> findScanwordsForUser(@RequestParam String type){
        List<SelectScanwordDto> scanwords = selectScanwordService.findScanwordsForUser(type);
        return ResponseEntity.ok(new ResponseDto<>("success", scanwords));
    }


}
