package com.quad.ScanwordApp.controller;

import com.quad.ScanwordApp.dto.DictionaryDto;
import com.quad.ScanwordApp.service.DictionaryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;



@RestController
@RequestMapping("/dictionary")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class DictionaryController {

    private final DictionaryService dictionaryService;

    @GetMapping
    public List<DictionaryDto> findAllDictionaries(){
        List<DictionaryDto> dictionaries = dictionaryService.findAllDictionaries();
        return dictionaries;
    }

    @GetMapping("/{id}")
    public DictionaryDto findDictionaryById(@PathVariable UUID id){
        DictionaryDto dictionaryDto = dictionaryService.findDictionaryById(id);
        return dictionaryDto;
    }

    @GetMapping(params = "name")
    public DictionaryDto findDictionaryByName(@RequestParam String name){
        DictionaryDto dictionaryDto = dictionaryService.findDictionaryByName(name);
        return dictionaryDto;
    }

    @PostMapping
    public DictionaryDto addDictionary(@RequestBody @Valid DictionaryDto dictionaryDto){
        DictionaryDto dictionaryDto1 = dictionaryService.addDictionary(dictionaryDto);
        return dictionaryDto1;
    }

    @DeleteMapping("/{id}")
    public void deleteDictionary(@PathVariable UUID id){
        dictionaryService.deleteDictionary(id);
    }
}
