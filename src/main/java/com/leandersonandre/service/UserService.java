package com.leandersonandre.service;

import com.leandersonandre.dto.ActiveUsersPerDayDTO;
import com.leandersonandre.dto.CountryDTO;
import com.leandersonandre.dto.TeamStatsDTO;
import com.leandersonandre.dto.UserDTO;
import com.leandersonandre.entity.Log;
import com.leandersonandre.entity.Project;
import com.leandersonandre.entity.User;
import com.leandersonandre.mapper.UserMapper;
import com.leandersonandre.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserService {

    @Inject
    UserRepository repository;

    public void save(List<UserDTO> users){
        var list = UserMapper.toEntity(users);
        list.forEach(repository::save);
    }

    public List<User> getSuperUsers() {
        return repository.findAllByActiveTrueAndScoreEqualsGreaterThan(900);
    }

    public List<CountryDTO> getTopCountries() {
        var superUsers = getSuperUsers();
        return superUsers.stream()
                .collect(Collectors
                        .groupingBy(User::getCountry, Collectors.counting()))
                .entrySet().stream()
                .map( e -> new CountryDTO(e.getKey(),e.getValue()))
                .sorted((e,j) -> Long.compare(j.total(),e.total()) ).limit(5).toList();
    }

    public List<TeamStatsDTO> getTeamInsights() {
        var list = repository.findAll();
        var mapByTeam = list.stream()
                .collect(Collectors.groupingBy(user -> user.getTeam().getName(),
                        Collectors.collectingAndThen(Collectors.toList(), users-> {
                            var totalMembers = users.size();
                            var leaders = users.stream().filter( u -> u.getTeam().isLeader()).toList().size();
                            var compledProjects = users.stream().filter(u-> u.getTeam().getProjects().stream().anyMatch(Project::isCompleted)).toList().size();
                            var activePercentage = users.stream().filter(User::isActive).count() / (double) totalMembers;
                            return new TeamStatsDTO("",totalMembers,leaders,compledProjects,activePercentage);
                                }
                                )));
        return mapByTeam.entrySet().stream().map( e -> new TeamStatsDTO(e.getKey()
                ,e.getValue().totalMembers(),e.getValue().leaders()
                ,e.getValue().completedProjects(),e.getValue().activePercentage())).toList();
    }

    public List<ActiveUsersPerDayDTO> getActiveUsersPerDay(Long min) {
        min = min == null ? 0 : min;
        var list = repository.findAll();
        var map = list.stream().flatMap(u->u.getLogs().stream().filter( l-> l.getAction().equals("login"))).collect(Collectors.groupingBy(Log::getDate,Collectors.counting()));
        Long finalMin = min;
        return map.entrySet().stream().filter(u -> u.getValue() >= finalMin).map(u -> new ActiveUsersPerDayDTO(u.getKey(),u.getValue())).toList();
    }
}
