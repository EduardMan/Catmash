package ru.mansurov.catmash.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mansurov.catmash.model.Mash;

import java.util.List;

public interface MashRepo extends JpaRepository<Mash, Long> {
    Mash findByName(String name);
}
