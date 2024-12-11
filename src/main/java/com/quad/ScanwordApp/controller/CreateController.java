package com.quad.ScanwordApp.controller;

import com.quad.ScanwordApp.dto.ResponseDto;
import com.quad.ScanwordApp.model.json.FilteredData;
import com.quad.ScanwordApp.service.CreateScanwordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/create_api")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class CreateController
{
    private final CreateScanwordService createScanwordService;

    @GetMapping
    public ResponseEntity<ResponseDto<FilteredData>> getFilteredData(@RequestParam Integer length, @RequestParam String regex, @RequestParam UUID dictionaryId){
        ResponseDto<FilteredData> response = new ResponseDto<>("success",createScanwordService.getFilteredData(length,regex,dictionaryId));
        return ResponseEntity.ok(response);
    }

}
