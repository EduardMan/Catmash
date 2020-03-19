package ru.mansurov.catmash.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.mansurov.catmash.model.Mash;
import ru.mansurov.catmash.model.Target;

import java.util.List;

public interface TargetRepo extends JpaRepository<Target, Long> {

    @Query(value = "SELECT * FROM target WHERE mash_id = ?1",
            nativeQuery = true)
    List<Target> getTop10ByRating(Mash mash);

//    @Query(value = "SELECT * FROM target WHERE mash_id = ?1 AND id NOT IN ?2 ORDER BY random() LIMIT 2",
//    nativeQuery = true)
    @Query(value = "SELECT * FROM target WHERE mash_id = ?1 AND id NOT IN (SELECT id FROM target WHERE id IN ?2) ORDER BY random() LIMIT 2",
    nativeQuery = true)
    List<Target> get2RandomTargets(Mash mash, List<Target> targets);

    List<Target> findAllByIdIn(List<Long> ids);
}
