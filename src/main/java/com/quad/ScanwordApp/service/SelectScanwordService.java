package com.quad.ScanwordApp.service;


import com.quad.ScanwordApp.dto.SelectScanwordDto;
import com.quad.ScanwordApp.model.DraftScanword;
import com.quad.ScanwordApp.model.Scanword;
import com.quad.ScanwordApp.repository.DraftScanwordRepository;
import com.quad.ScanwordApp.repository.ScanwordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SelectScanwordService {

    private final ScanwordRepository scanwordRepository;

    private final DraftScanwordRepository draftScanwordRepository;
    private final UserService userService;

    public List<SelectScanwordDto> findScanwordsForUser(String type){
        UUID userId = userService.getCurrentUser().getId();
        List<SelectScanwordDto> selectScanwordDtos = new ArrayList<>();
        switch (type){
            case "NEW":{
                List<Scanword> newScanwords = scanwordRepository.findAllNewScanwordsForUser(userId);
                for (Scanword scanword : newScanwords){
                    selectScanwordDtos.add(SelectScanwordDto.builder()
                                    .scanwordId(scanword.getId())
                                    .name(scanword.getName())
                                    .numberOfHints(scanword.getNumberOfHints())
                                    .preview(scanword.getPreview())
                                    .content(scanword.getContent())
                            .build());
                }
                return selectScanwordDtos;
            }
            case "STARTED":{
                List<DraftScanword> userScanwords = draftScanwordRepository.findAllByOwnerId(userId).stream().filter(draftScanword -> !draftScanword.getSolved()).toList();
                for (DraftScanword draftScanword : userScanwords){
                    selectScanwordDtos.add(SelectScanwordDto.builder()
                                    .scanwordId(draftScanword.getScanword().getId())
                                    .draftId(draftScanword.getId())
                                    .name(draftScanword.getScanword().getName())
                                    .numberOfHints(draftScanword.getNumberOfHints())
                                    .preview(draftScanword.getPreview())
                                    .content(draftScanword.getContent())
                            .build());
                }
                return selectScanwordDtos;
            }
        }
        return selectScanwordDtos;
    }

}
