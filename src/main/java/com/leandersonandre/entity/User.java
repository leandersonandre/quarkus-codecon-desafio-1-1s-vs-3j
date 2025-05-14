package com.leandersonandre.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class User {
    UUID id;
    String name;
    int age;
    int score;
    boolean active;
    String country;
    Team team;
    List<Log> logs;
}
