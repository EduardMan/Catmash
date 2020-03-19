package ru.mansurov.catmash.model.service;

import ru.mansurov.catmash.model.Mash;

import java.util.List;

public interface MashService {

    List<Mash> findAll();

    void save(Mash mash);

    Mash findByName(String name);
}
