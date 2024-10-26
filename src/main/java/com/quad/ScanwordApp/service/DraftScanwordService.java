package com.quad.ScanwordApp.service;

import com.quad.ScanwordApp.dto.DraftScanwordDto;
import com.quad.ScanwordApp.exception.NotFoundException;
import com.quad.ScanwordApp.mapper.DraftScanwordMapper;
import com.quad.ScanwordApp.model.DraftScanword;
import com.quad.ScanwordApp.repository.DraftScanwordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DraftScanwordService {

    private final DraftScanwordRepository draftScanwordRepository;

    private final DraftScanwordMapper draftScanwordMapper;

    public List<DraftScanwordDto> findAllDraftScanwords() {
        return draftScanwordRepository.findAll()
                .stream()
                .map(draftScanwordMapper::toDto)
                .toList();
    }

    public DraftScanwordDto addDraftScanword(DraftScanwordDto draftScanwordDto) {
        DraftScanword draftScanword = draftScanwordMapper.toDraftScanword(draftScanwordDto);
        return draftScanwordMapper.toDto(draftScanwordRepository.save(draftScanword));
    }

    public DraftScanwordDto updateDraftScanword(DraftScanwordDto draftScanwordDto, UUID id) {
        DraftScanword draftScanword = draftScanwordRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Черновик сканворда с id %s не найден", id)));
        draftScanword.setContent(draftScanwordDto.getContent());
        return draftScanwordMapper.toDto(draftScanwordRepository.save(draftScanword));
    }

    public void deleteDraftScanword(UUID id) {
        draftScanwordRepository.deleteById(id);
    }
}
