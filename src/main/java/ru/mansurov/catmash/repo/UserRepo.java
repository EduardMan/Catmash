package ru.mansurov.catmash.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mansurov.catmash.model.User;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
