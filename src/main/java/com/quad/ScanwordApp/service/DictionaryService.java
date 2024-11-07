package com.quad.ScanwordApp.service;

import com.quad.ScanwordApp.exception.NotFoundException;
import com.quad.ScanwordApp.model.Dictionary;
import com.quad.ScanwordApp.dto.DictionaryDto;
import com.quad.ScanwordApp.mapper.DictionaryMapper;
import com.quad.ScanwordApp.repository.DictionaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DictionaryService {

    private final DictionaryRepository dictionaryRepository;

    private final DictionaryMapper dictionaryMapper;

    public List<DictionaryDto> findAllDictionaries() {
        return dictionaryRepository.findAll()
                .stream()
                .map(dictionaryMapper::toDto)
                .toList();
    }

    public DictionaryDto findDictionaryById(UUID id) {
        return dictionaryMapper.toDto(dictionaryRepository
                .findById(id)
                .orElseThrow(()-> new NotFoundException(String.format("Словарь с id %s не найден",id))));
    }

    public DictionaryDto findDictionaryByName(String name) {
        DictionaryDto dictionary = dictionaryMapper.toDto(dictionaryRepository
                .findDictionaryByName(name));
        if (dictionary == null){
            throw new NotFoundException("Словарь с названием " + name + " не найден");
        }
        return dictionary;
    }

    public DictionaryDto addDictionary(DictionaryDto dictionaryDto) {
        Dictionary dictionary = dictionaryMapper.toDictionary(dictionaryDto);
        return dictionaryMapper.toDto(dictionaryRepository.save(dictionary));
    }

    public void deleteDictionary(UUID id) {
        dictionaryRepository.deleteById(id);
    }


}
