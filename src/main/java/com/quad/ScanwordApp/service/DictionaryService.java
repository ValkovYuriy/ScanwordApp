package com.quad.ScanwordApp.service;

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

    public List<DictionaryDto> findAllWords() {
        return dictionaryRepository.findAll()
                .stream()
                .map(dictionaryMapper::toDto)
                .toList();
    }

    public DictionaryDto addWord(DictionaryDto dictionaryDto) {
        Dictionary dictionary = dictionaryMapper.toDictionary(dictionaryDto);
        return dictionaryMapper.toDto(dictionaryRepository.save(dictionary));
    }

    public void deleteWord(UUID id) {
        dictionaryRepository.deleteById(id);
    }
}
