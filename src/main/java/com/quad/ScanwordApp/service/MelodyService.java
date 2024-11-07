package com.quad.ScanwordApp.service;

import com.quad.ScanwordApp.dto.MelodyDto;
import com.quad.ScanwordApp.exception.NotFoundException;
import com.quad.ScanwordApp.mapper.MelodyMapper;
import com.quad.ScanwordApp.model.Melody;
import com.quad.ScanwordApp.repository.MelodyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MelodyService {

    private final MelodyRepository melodyRepository;

    private final MelodyMapper melodyMapper;

    public List<MelodyDto> findAllMelodies() {
        return melodyRepository.findAll()
                .stream()
                .map(melodyMapper::toDto)
                .toList();
    }

    public MelodyDto findMelodyById(UUID id) {
        return melodyMapper.toDto(melodyRepository
                .findById(id)
                .orElseThrow(()->new NotFoundException(String.format("Мелодия с id %s не найдена",id))));
    }

    public MelodyDto addMelody(MelodyDto melodyDto) {
        Melody melody = melodyMapper.toMelody(melodyDto);
        return melodyMapper.toDto(melodyRepository.save(melody));
    }

    public MelodyDto updateMelody(MelodyDto melodyDto, UUID id) {
        Melody melody = melodyRepository.findById(id).orElseThrow(()-> new NotFoundException(String.format("Мелодия с id %s не найдена",id)));
        melody.setMelody(melodyDto.getMelody());
        melody.setQuestion(melodyDto.getQuestion());
        melody.setAnswer(melodyDto.getAnswer());
        return melodyMapper.toDto(melodyRepository.save(melody));
    }

    public void deleteMelody(UUID id) {
        melodyRepository.deleteById(id);
    }
}
