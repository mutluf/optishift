package com.example.optishift.service;

import com.example.optishift.dto.ShiftCreateRequest;
import com.example.optishift.dto.ShiftResponse;
import com.example.optishift.dto.ShiftUpdateRequest;
import com.example.optishift.entity.Shift;
import com.example.optishift.mapper.ShiftMapper;
import com.example.optishift.repository.ShiftRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShiftService {
    
    private final ShiftRepository shiftRepository;
    private final ShiftMapper shiftMapper;

    @Transactional
    public ShiftResponse createShift(ShiftCreateRequest request) {
        Shift shift = shiftMapper.toEntity(request);
        
        Shift savedShift = shiftRepository.save(shift);
        return shiftMapper.toResponse(savedShift);
    }
    
    @Transactional
    public ShiftResponse updateShift(Long id, ShiftUpdateRequest request) {
        Shift shift = shiftRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Shift not found with id: " + id));
        
        shiftMapper.updateEntityFromRequest(request, shift);
        
        Shift updatedShift = shiftRepository.save(shift);
        return shiftMapper.toResponse(updatedShift);
    }

    @Transactional(readOnly = true)
    public ShiftResponse getShiftById(Long id) {
        Shift shift = shiftRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Shift not found with id: " + id));
        return shiftMapper.toResponse(shift);
    }

    @Transactional(readOnly = true)
    public List<ShiftResponse> getAllShifts() {
        return shiftRepository.findAll().stream()
                .map(shiftMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteShift(Long id) {
        if (!shiftRepository.existsById(id)) {
            throw new EntityNotFoundException("Shift not found with id: " + id);
        }
        shiftRepository.deleteById(id);
    }
}
