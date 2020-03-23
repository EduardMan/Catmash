package ru.mansurov.catmash.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.mansurov.catmash.model.Target;
import ru.mansurov.catmash.model.User;

import java.util.Set;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
