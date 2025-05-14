package com.leandersonandre.repository;

import com.leandersonandre.entity.User;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.*;

@ApplicationScoped
public class UserRepository {

    private Map<UUID,User> map = new HashMap<>();

    public void save(User user){
        map.put(user.getId(), user);
    }

    public List<User> findAllByActiveTrueAndScoreEqualsGreaterThan(int score) {
        return map.values().stream()
                .filter(user -> user.isActive()
                        && user.getScore() >= score).toList();
    }

    public List<User> findAll() {
        return new ArrayList<>(map.values());
    }
}
