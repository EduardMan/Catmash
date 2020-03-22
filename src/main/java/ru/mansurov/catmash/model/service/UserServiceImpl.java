package ru.mansurov.catmash.model.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.mansurov.catmash.model.Role;
import ru.mansurov.catmash.model.User;
import ru.mansurov.catmash.repo.UserRepo;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean addUser(User user) {

        try {
            if (userRepo.findByUsername(user.getUsername()) != null)
                return false;
            // set Role USER for all new users
            user.setRoles(new HashSet<Role>(Arrays.asList(Role.USER)));
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepo.save(user);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if (user != null) return user;
        else {
            throw new UsernameNotFoundException("User not found.");
        }
    }

    @Override
    public void save(User user) {
        userRepo.save(user);
    }

    @Override
    public User findByUser(User user) {
        return userRepo.findById(user.getId()).get();
    }
}
