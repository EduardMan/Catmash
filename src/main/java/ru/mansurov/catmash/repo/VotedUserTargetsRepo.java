package ru.mansurov.catmash.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mansurov.catmash.model.VotedUserTargets;

public interface VotedUserTargetsRepo extends JpaRepository<VotedUserTargets, Long> {
}
