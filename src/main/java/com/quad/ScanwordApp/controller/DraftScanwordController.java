package com.quad.ScanwordApp.controller;


import com.quad.ScanwordApp.dto.DraftScanwordDto;
import com.quad.ScanwordApp.dto.ResponseDto;
import com.quad.ScanwordApp.service.DraftScanwordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/{id}")
    public DraftScanwordDto findDraftScanwordById(@PathVariable UUID id){
        DraftScanwordDto draftScanwordDto = draftScanwordService.findDraftScanwordById(id);
        return draftScanwordDto;
    }

    @PostMapping
    public ResponseEntity<ResponseDto<DraftScanwordDto>> addDraftScanword(@RequestBody @Valid DraftScanwordDto scanwordDto){
        DraftScanwordDto scanwordDto1 = draftScanwordService.addDraftScanword(scanwordDto);
        return ResponseEntity.ok(new ResponseDto<>("success",scanwordDto1));
    }

    @PutMapping
    public ResponseEntity<ResponseDto<DraftScanwordDto>> updateDraftScanword(@RequestBody @Valid DraftScanwordDto scanwordDto, @RequestParam String id){
        DraftScanwordDto scanwordDto1 = draftScanwordService.updateDraftScanword(scanwordDto, UUID.fromString(id));
        return ResponseEntity.ok(new ResponseDto<>("success",scanwordDto1));
    }

    @DeleteMapping("/{id}")
    public void deleteDraftScanword(@PathVariable UUID id){
        draftScanwordService.deleteDraftScanword(id);
    }
}
