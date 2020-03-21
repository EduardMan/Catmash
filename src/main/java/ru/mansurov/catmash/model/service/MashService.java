package ru.mansurov.catmash.model.service;

import ru.mansurov.catmash.model.Mash;
import ru.mansurov.catmash.model.User;

import java.util.List;

public interface MashService {

    List<Mash> findAll();

    void save(Mash mash);

    Mash findByName(String name);

    List<Mash> findNotVotedMashesByUser(User user);

    void deleteById(Mash mash);

}
