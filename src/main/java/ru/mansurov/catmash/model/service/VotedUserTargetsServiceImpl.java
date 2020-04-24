package ru.mansurov.catmash.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mansurov.catmash.model.VotedUserTargets;
import ru.mansurov.catmash.repo.VotedUserTargetsRepo;

@Service
public class VotedUserTargetsServiceImpl implements VotedUserTargetsService {
    @Autowired
    VotedUserTargetsRepo votedUserTargetsRepo;

    @Override
    public void save(VotedUserTargets votedUserTargets) {
        votedUserTargetsRepo.save(votedUserTargets);
    }
}
