package com.example.optishift.mapper;

import com.example.optishift.dto.ProjectCreateRequest;
import com.example.optishift.dto.ProjectResponse;
import com.example.optishift.dto.ProjectUpdateRequest;
import com.example.optishift.entity.Project;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    ProjectResponse toResponse(Project project);
    Project toEntity(ProjectCreateRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(ProjectUpdateRequest request, @MappingTarget Project project);
}
