package com.leandersonandre.mapper;

import com.leandersonandre.dto.TeamDTO;
import com.leandersonandre.entity.Team;

import java.util.List;

public class TeamMapper {

    public static Team toEntity(TeamDTO dto){
        Team t = new Team();
        t.setName(dto.name());
        t.setLeader(dto.leader());
        t.setProjects(ProjectMapper.toEntity(dto.projects()));
        return t;
    }

    public static List<Team> toEntity(List<TeamDTO> list){
        return list.stream().map(TeamMapper::toEntity).toList();
    }

}
