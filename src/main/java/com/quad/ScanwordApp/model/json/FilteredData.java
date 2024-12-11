package com.quad.ScanwordApp.model.json;


import com.quad.ScanwordApp.dto.ImageDto;
import com.quad.ScanwordApp.dto.MelodyDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FilteredData {

    private List<DictionaryData> dictionaryData;

    private List<ImageDto> images;

    private List<MelodyDto> melodies;
}
