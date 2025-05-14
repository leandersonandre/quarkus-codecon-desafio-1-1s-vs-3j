package com.leandersonandre.dto;

import java.util.List;

public record UserDTO(String id
        , String name
        , int age
        , int score
        , boolean active
        , String country
        , TeamDTO team
        , List<LogDTO> logs) {
}
