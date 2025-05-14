package com.leandersonandre.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class Log {
    LocalDate date;
    String action;
}
