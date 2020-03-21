package ru.mansurov.catmash.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.mansurov.catmash.model.Mash;
import ru.mansurov.catmash.model.Target;
import ru.mansurov.catmash.model.User;

import java.util.List;

public interface TargetRepo extends JpaRepository<Target, Long> {

    @Query(value = "SELECT * FROM target WHERE mash_id = ?1 LIMIT 10",
            nativeQuery = true)
    List<Target> getTop10ByRating(Mash mash);

    @Query(value = "SELECT * FROM target WHERE mash_id = ?1 AND id NOT IN (SELECT id FROM target WHERE id IN ?2) ORDER BY random() LIMIT 2",
            nativeQuery = true)
    List<Target> get2RandomTargets(Mash mash, List<Target> targets);

    @Query(value = "SELECT * FROM target WHERE mash_id = ?1 AND " +
            "id NOT IN (SELECT target_id FROM voted_user_targets WHERE user_id = ?2) ORDER BY random() LIMIT 2;",
    nativeQuery = true)
    List<Target> get2RandomTargets(Mash mash, User user);

    List<Target> findAllByIdIn(List<Long> ids);

    @Transactional
    void deleteTargetsByMash(Mash mash);

    List<Target> getTargetsByMash(Mash mash);
}
