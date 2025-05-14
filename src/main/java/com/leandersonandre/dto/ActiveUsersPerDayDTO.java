package com.leandersonandre.dto;

import java.time.LocalDate;

public record ActiveUsersPerDayDTO(LocalDate date, long total) {
}
