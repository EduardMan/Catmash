package ru.mansurov.catmash.model.service;

import ru.mansurov.catmash.model.Mash;
import ru.mansurov.catmash.model.User;

public interface UserService {

    void save(User user);

    User findByUser(User User);

}
