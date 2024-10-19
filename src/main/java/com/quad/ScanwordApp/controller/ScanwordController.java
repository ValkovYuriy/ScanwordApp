package com.quad.ScanwordApp.controller;


import com.quad.ScanwordApp.dto.ScanwordDto;
import com.quad.ScanwordApp.service.ScanwordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ScanwordController {
    
    private final ScanwordService scanwordService;

    @GetMapping
    public List<ScanwordDto> findAllScanwords(){
        List<ScanwordDto> allScanwords = scanwordService.findAllScanwords();
        return allScanwords;
    }


    @PostMapping
    public ScanwordDto addScanword(@RequestBody @Valid ScanwordDto scanwordDto){
        ScanwordDto scanwordDto1 = scanwordService.addScanword(scanwordDto);
        return scanwordDto1;
    }

    @PutMapping
    public ScanwordDto updateScanword(@RequestBody @Valid ScanwordDto scanwordDto, @RequestParam UUID id){
        ScanwordDto scanwordDto1 = scanwordService.updateScanword(scanwordDto,id);
        return scanwordDto1;
    }

    @DeleteMapping()
    public void deleteScanword(@RequestParam UUID id){
        scanwordService.deleteScanword(id);
    }
}
