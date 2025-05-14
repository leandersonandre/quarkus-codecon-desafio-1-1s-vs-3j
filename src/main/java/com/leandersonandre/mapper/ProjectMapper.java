package com.leandersonandre.mapper;

import com.leandersonandre.dto.ProjectDTO;
import com.leandersonandre.entity.Project;

import java.util.List;

public class ProjectMapper {

    public static Project toEntity(ProjectDTO dto) {
        Project p = new Project();
        p.setName(dto.name());
        p.setCompleted(dto.completed());
        return p;
    }

    public static List<Project> toEntity(List<ProjectDTO> list){
        return list.stream().map(ProjectMapper::toEntity).toList();
    }

}
