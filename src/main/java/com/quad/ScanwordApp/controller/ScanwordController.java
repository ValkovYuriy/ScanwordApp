package com.quad.ScanwordApp.controller;


import com.quad.ScanwordApp.dto.ScanwordDto;
import com.quad.ScanwordApp.service.ScanwordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/scanword")
@RequiredArgsConstructor
public class ScanwordController {
    
    private final ScanwordService scanwordService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<ScanwordDto> findAllScanwords(){
        List<ScanwordDto> allScanwords = scanwordService.findAllScanwords();
        return allScanwords;
    }

    @GetMapping("/{id}")
    public ScanwordDto findScanwordById(@PathVariable UUID id){
        ScanwordDto scanwordDto = scanwordService.findScanwordById(id);
        return scanwordDto;
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

    @DeleteMapping("/{id}")
    public void deleteScanword(@PathVariable UUID id){
        scanwordService.deleteScanword(id);
    }

}
