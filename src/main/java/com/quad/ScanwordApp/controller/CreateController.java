package com.quad.ScanwordApp.controller;

import com.quad.ScanwordApp.dto.ResponseDto;
import com.quad.ScanwordApp.model.json.Data;
import com.quad.ScanwordApp.model.json.FilteredData;
import com.quad.ScanwordApp.service.CreateScanwordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/create_api")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class CreateController {
    private final CreateScanwordService createScanwordService;


    @GetMapping("/{dictionaryId}")
    public ResponseEntity<ResponseDto<Data>> getData(@PathVariable String dictionaryId){
        ResponseDto<Data> response = new ResponseDto<>("success", createScanwordService.getData(UUID.fromString(dictionaryId)));
        return ResponseEntity.ok(response);
    }

    @PostMapping()
    public ResponseEntity<ResponseDto<FilteredData>> getFilteredData(@RequestParam String regex, @RequestBody Data data){
        ResponseDto<FilteredData> response = new ResponseDto<>("success",createScanwordService.getFilteredData(regex, data));
        return ResponseEntity.ok(response);
    }
}
