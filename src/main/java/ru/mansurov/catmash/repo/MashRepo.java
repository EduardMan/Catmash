package ru.mansurov.catmash.repo;

import org.apache.catalina.LifecycleState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.mansurov.catmash.model.Mash;
import ru.mansurov.catmash.model.User;

import java.util.List;

public interface MashRepo extends JpaRepository<Mash, Long> {
    Mash findByName(String name);

    @Query(value = "SELECT DISTINCT m.id FROM mash as m INNER JOIN (SELECT t.id, vut.target_id, vut.user_id, mash_id " +
            "FROM target t LEFT JOIN (SELECT * FROM voted_user_targets WHERE user_id = ?1) AS vut ON t.id = vut.target_id " +
            "WHERE vut.target_id ISNULL) AS res ON m.id=res.mash_id", nativeQuery = true)
    List<Mash> findNotVotedMashesByUser(Long userId);

    void deleteById(Long id);
}
