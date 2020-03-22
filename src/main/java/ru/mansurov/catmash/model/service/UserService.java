package ru.mansurov.catmash.model.service;

import ru.mansurov.catmash.model.User;

public interface UserService {

    public boolean addUser(User user);

    void save(User user);

    User findByUser(User User);

}
