package ru.mansurov.catmash.model.service;

import ru.mansurov.catmash.model.Mash;
import ru.mansurov.catmash.model.Target;
import ru.mansurov.catmash.model.User;

import java.util.List;

public interface TargetService {

    void save(Target target);

    List<Target> getTop10ByRating(Mash mash);

    void increaseRating(Target target);

    List<Target> get2RandomTargets(Mash mash, List<Target> targets);

    List<Target> get2RandomTargets(Mash mash, User user);

    List<Target> findAllByIdIn(List<Long> ids);

    void deleteTargetsByMash(Mash mash);

    List<Target> getTargetsByMash(Mash mash);

    Target getTargetById(Long id);

}
