package com.leandersonandre.mapper;

import com.leandersonandre.dto.UserDTO;
import com.leandersonandre.entity.User;

import java.util.List;
import java.util.UUID;

public class UserMapper {

    public static User toEntity(UserDTO dto){
        User u = new User();
        u.setId(UUID.fromString(dto.id()));
        u.setName(dto.name());
        u.setAge(dto.age());
        u.setScore(dto.score());
        u.setActive(dto.active());
        u.setCountry(dto.country());
        u.setTeam(TeamMapper.toEntity(dto.team()));
        u.setLogs(LogMapper.toEntity(dto.logs()));
        return u;
    }

    public static List<User> toEntity(List<UserDTO> list){
        return list.stream().map(UserMapper::toEntity).toList();
    }

}
