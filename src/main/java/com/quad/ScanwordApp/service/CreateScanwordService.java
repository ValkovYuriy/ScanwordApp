package com.quad.ScanwordApp.service;

import com.quad.ScanwordApp.dto.ImageDto;
import com.quad.ScanwordApp.dto.MelodyDto;
import com.quad.ScanwordApp.model.json.Data;
import com.quad.ScanwordApp.model.json.DictionaryData;
import com.quad.ScanwordApp.model.json.FilteredData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class CreateScanwordService {

    private final DictionaryService dictionaryService;

    private final ImageService imageService;

    private final MelodyService melodyService;

    public Data getData(UUID dictionaryId) {
        return Data.builder()
                .dictionaryData(dictionaryService.findDictionaryById(dictionaryId).getDictionaryData())
                .images(imageService.findAllImages())
                .melodies(melodyService.findAllMelodies())
                .build();
    }

    public FilteredData getFilteredData(String regex, Data data) {
        List<DictionaryData> dictionaryData = data.getDictionaryData();
        List<ImageDto> images = data.getImages();
        List<MelodyDto> melodies = data.getMelodies();
        Pattern pattern = Pattern.compile(regex);

        List<DictionaryData> filteredDictionaryData = dictionaryData
                .stream()
                .filter(item -> pattern.matcher(item.getWord()).matches())
                .toList();
        List<ImageDto> filteredImages = images
                .stream()
                .filter(item -> pattern.matcher(item.getAnswer()).matches())
                .toList();
        List<MelodyDto> filteredMelodies = melodies
                .stream()
                .filter(item -> pattern.matcher(item.getAnswer()).matches())
                .toList();
        return FilteredData.builder()
                .dictionaryData(filteredDictionaryData)
                .images(filteredImages)
                .melodies(filteredMelodies)
                .build();
    }
}
