package com.leandersonandre.mapper;

import com.leandersonandre.dto.LogDTO;
import com.leandersonandre.entity.Log;

import java.time.LocalDate;
import java.util.List;

public class LogMapper {

    public static Log toEntity(LogDTO dto){
        Log l = new Log();
        l.setDate(LocalDate.parse(dto.date()));
        l.setAction(dto.action());
        return l;
    }

    public static List<Log> toEntity(List<LogDTO> list){
        return list.stream().map(LogMapper::toEntity).toList();
    }

}
