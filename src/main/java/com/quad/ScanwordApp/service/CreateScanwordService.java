package com.quad.ScanwordApp.service;

import com.quad.ScanwordApp.dto.DictionaryDto;
import com.quad.ScanwordApp.dto.ImageDto;
import com.quad.ScanwordApp.dto.MelodyDto;
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

    public FilteredData getFilteredData(int length, String regex, UUID dictionaryId) {
        DictionaryDto dictionary = dictionaryService.findDictionaryById(dictionaryId);
        List<ImageDto> images = imageService.findAllImages();
        List<MelodyDto> melodies = melodyService.findAllMelodies();
        Pattern pattern = Pattern.compile(regex);

        List<DictionaryData> filteredDictionaryData = dictionary.getDictionaryData()
                .stream()
                .filter(item -> pattern.matcher(item.getWord()).matches() && item.getWord().length() == length)
                .toList();
        List<ImageDto> filteredImages = images
                .stream()
                .filter(item -> pattern.matcher(item.getAnswer()).matches() && item.getAnswer().length() == length)
                .toList();
        List<MelodyDto> filteredMelodies = melodies
                .stream()
                .filter(item -> pattern.matcher(item.getAnswer()).matches() && item.getAnswer().length() == length)
                .toList();
        return FilteredData.builder()
                .dictionaryData(filteredDictionaryData)
                .images(filteredImages)
                .melodies(filteredMelodies)
                .build();
    }
}
