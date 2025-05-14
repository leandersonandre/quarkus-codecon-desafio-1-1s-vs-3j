package com.leandersonandre.dto;

public record TeamStatsDTO(String team
        , long totalMembers
        , long leaders
        , long completedProjects
        , double activePercentage) {
}
