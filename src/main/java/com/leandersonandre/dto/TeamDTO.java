package com.leandersonandre.dto;

import java.util.List;

public record TeamDTO(String name
        , boolean leader
        , List<ProjectDTO> projects) {
}
