package com.quad.ScanwordApp.model.json;

import com.quad.ScanwordApp.dto.ImageDto;
import com.quad.ScanwordApp.dto.MelodyDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Data {

    private List<DictionaryData> dictionaryData;

    private List<ImageDto> images;

    private List<MelodyDto> melodies;
}
