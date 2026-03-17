package com.example.optishift.mapper;

import com.example.optishift.dto.ShiftCreateRequest;
import com.example.optishift.dto.ShiftResponse;
import com.example.optishift.dto.ShiftUpdateRequest;
import com.example.optishift.entity.Shift;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ShiftMapper {
    ShiftResponse toResponse(Shift shift);
    Shift toEntity(ShiftCreateRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(ShiftUpdateRequest request, @MappingTarget Shift shift);
}
