package ru.mansurov.catmash.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mansurov.catmash.model.Mash;
import ru.mansurov.catmash.repo.MashRepo;

import java.util.List;

@Service
public class MashServiceImpl implements MashService {

    @Autowired
    MashRepo mashRepo;

    @Override
    public List<Mash> findAll() {
        return mashRepo.findAll();
    }

    @Override
    public void save(Mash mash) {
        mashRepo.save(mash);
    }

    @Override
    public Mash findByName(String name) {
        return mashRepo.findByName(name);
    }
}
