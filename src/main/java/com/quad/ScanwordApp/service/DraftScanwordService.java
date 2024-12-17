package com.quad.ScanwordApp.service;

import com.quad.ScanwordApp.dto.DraftScanwordDto;
import com.quad.ScanwordApp.exception.NotFoundException;
import com.quad.ScanwordApp.mapper.DraftScanwordMapper;
import com.quad.ScanwordApp.mapper.ScanwordMapper;
import com.quad.ScanwordApp.model.DraftScanword;
import com.quad.ScanwordApp.model.User;
import com.quad.ScanwordApp.model.json.Cell;
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
    private final UserService userService;

    private final ScanwordMapper scanwordMapper;

    private final ScanwordService scanwordService;

    public List<DraftScanwordDto> findAllDraftScanwords() {
        return draftScanwordRepository.findAll()
                .stream()
                .map(draftScanwordMapper::toDto)
                .toList();
    }

    public DraftScanwordDto findDraftScanwordById(UUID id) {
        return draftScanwordMapper
                .toDto(draftScanwordRepository
                        .findById(id)
                        .orElseThrow(() -> new NotFoundException(String.format("Сканворд с id %s не найден", id))));
    }

    public DraftScanwordDto addDraftScanword(DraftScanwordDto draftScanwordDto) {
        User user = userService.getCurrentUser();
        //if(draftScanwordRepository.findAllByOwnerId(user.getId()).stream().noneMatch(draftScanword -> draftScanword.getScanword().getId().equals(draftScanwordDto.getScanwordId()))) {
            draftScanwordDto.setContent(draftScanwordDto.getContent().stream().map(cell -> Cell.builder()
                    .row(cell.getRow())
                    .col(cell.getCol())
                    .word(cell.getWord())
                    .task(cell.isTask())
                    .taskType(cell.getTaskType())
                    .letter(null)
                    .build()).toList());
            DraftScanword draftScanword = draftScanwordMapper.toDraftScanword(draftScanwordDto);
            draftScanword.setOwner(user);
            draftScanword.setScanword(scanwordMapper.toScanword(scanwordService.findScanwordById(draftScanwordDto.getScanwordId())));
            user.getDraftScanwords().add(draftScanword);
            return draftScanwordMapper.toDto(draftScanwordRepository.save(draftScanword));
        //}
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
