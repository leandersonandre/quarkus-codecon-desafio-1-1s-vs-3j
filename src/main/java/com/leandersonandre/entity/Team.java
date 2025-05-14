package com.leandersonandre.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Team {
    String name;
    boolean leader;
    List<Project> projects;
}
