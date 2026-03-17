package com.example.optishift.mapper;

import com.example.optishift.dto.TaskCreateRequest;
import com.example.optishift.dto.TaskResponse;
import com.example.optishift.dto.TaskUpdateRequest;
import com.example.optishift.entity.Task;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", uses = { ProjectMapper.class, ShiftMapper.class })
public interface TaskMapper {

    TaskResponse toResponse(Task task);

    @Mapping(target = "project", ignore = true)
    @Mapping(target = "shift", ignore = true)
    Task toEntity(TaskCreateRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "project", ignore = true)
    @Mapping(target = "shift", ignore = true)
    void updateEntityFromRequest(TaskUpdateRequest request, @MappingTarget Task task);
}
