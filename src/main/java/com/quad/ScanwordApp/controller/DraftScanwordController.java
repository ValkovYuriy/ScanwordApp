package com.quad.ScanwordApp.controller;


import com.quad.ScanwordApp.dto.DraftScanwordDto;
import com.quad.ScanwordApp.service.DraftScanwordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/draft-scanword")
@RequiredArgsConstructor
public class DraftScanwordController {
    
    private final DraftScanwordService draftScanwordService;

    @GetMapping
    public List<DraftScanwordDto> findAllDraftScanwords(){
        List<DraftScanwordDto> allDraftScanwords = draftScanwordService.findAllDraftScanwords();
        return allDraftScanwords;
    }


    @PostMapping
    public DraftScanwordDto addDraftScanword(@RequestBody @Valid DraftScanwordDto scanwordDto){
        DraftScanwordDto scanwordDto1 = draftScanwordService.addDraftScanword(scanwordDto);
        return scanwordDto1;
    }

    @PutMapping
    public DraftScanwordDto updateDraftScanword(@RequestBody @Valid DraftScanwordDto scanwordDto, @RequestParam UUID id){
        DraftScanwordDto scanwordDto1 = draftScanwordService.updateDraftScanword(scanwordDto,id);
        return scanwordDto1;
    }

    @DeleteMapping()
    public void deleteDraftScanword(@RequestParam UUID id){
        draftScanwordService.deleteDraftScanword(id);
    }
}
