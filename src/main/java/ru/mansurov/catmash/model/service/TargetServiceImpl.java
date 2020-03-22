package ru.mansurov.catmash.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mansurov.catmash.model.Mash;
import ru.mansurov.catmash.model.Target;
import ru.mansurov.catmash.model.User;
import ru.mansurov.catmash.repo.TargetRepo;

import java.util.List;

@Service
public class TargetServiceImpl implements TargetService {

    @Autowired
    TargetRepo targetRepo;

    @Override
    public void save(Target target) {
        targetRepo.save(target);
    }

    @Override
    public List<Target> getTop10ByRating(Mash mash) {
        return targetRepo.getTop10ByRating(mash);
    }

    @Override
    public void increaseRating(Target target) {
        target.setRating(target.getRating() + 1);
        targetRepo.save(target);
    }

    @Override
    public List<Target> get2RandomTargets(Mash mash, List<Target> targets) {
        return targetRepo.get2RandomTargets(mash, targets);
    }

    @Override
    public List<Target> get2RandomTargets(Mash mash, User user) {
        return targetRepo.get2RandomTargets(mash, user);
    }

    @Override
    public List<Target> findAllByIdIn(List<Long> ids) {
        return targetRepo.findAllByIdIn(ids);
    }

    @Override
    public void deleteTargetsByMash(Mash mash) {
        targetRepo.deleteTargetsByMash(mash);
    }

    @Override
    public List<Target> getTargetsByMash(Mash mash) {
        return targetRepo.getTargetsByMash(mash);
    }

    @Override
    public Target getTargetById(Long id) {
        return targetRepo.findById(id).get();
    }
}
