package com.quad.ScanwordApp.service;

import com.quad.ScanwordApp.dto.ScanwordDto;
import com.quad.ScanwordApp.exception.NotFoundException;
import com.quad.ScanwordApp.mapper.ScanwordMapper;
import com.quad.ScanwordApp.model.Scanword;
import com.quad.ScanwordApp.repository.DictionaryRepository;
import com.quad.ScanwordApp.repository.ScanwordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ScanwordService {

    private final ScanwordRepository scanwordRepository;

    private final ScanwordMapper scanwordMapper;
    private final DictionaryRepository dictionaryRepository;


    public List<ScanwordDto> findAllScanwords() {
        return scanwordRepository.findAll()
                .stream()
                .map(scanwordMapper::toDto)
                .toList();
    }

    public ScanwordDto findScanwordById(UUID id) {
        return scanwordMapper.toDto(scanwordRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Сканворд с id %s не найден", id))));
    }
    public ScanwordDto addScanword(ScanwordDto scanwordDto) {
        if(!scanwordDto.getIsCreated() && findAllScanwords().stream().anyMatch(scanword -> !scanword.getIsCreated())) {
            return null;
        }
        Scanword scanword = scanwordMapper.toScanword(scanwordDto);
        scanword.setDictionary(dictionaryRepository.findById(scanwordDto.getDictionaryId()).orElse(null));
        return scanwordMapper.toDto(scanwordRepository.save(scanword));
    }

    public ScanwordDto updateScanword(ScanwordDto scanwordDto,UUID id) {
        Scanword scanword = scanwordRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Сканворд с id %s не найден", id)));
        scanword.setName(scanwordDto.getName());
        scanword.setWidth(scanwordDto.getWidth());
        scanword.setHeight(scanwordDto.getHeight());
        scanword.setDictionary(dictionaryRepository.findById(scanwordDto.getDictionaryId()).orElse(null));
        scanword.setContent(scanwordDto.getContent());
        scanword.setIsCreated(scanwordDto.getIsCreated());
        scanword.setPreview(scanwordDto.getPreview());
        scanword.setNumberOfHints(scanwordDto.getNumberOfHints());
        return scanwordMapper.toDto(scanwordRepository.save(scanword));
    }

    public void deleteScanword(UUID id) {
        scanwordRepository.deleteById(id);
    }
}
