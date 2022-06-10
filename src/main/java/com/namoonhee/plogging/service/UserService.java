package com.namoonhee.plogging.service;

import java.util.List;
import java.util.Optional;

import com.namoonhee.plogging.model.Activity;
import com.namoonhee.plogging.model.User;

public interface UserService {

    public void signup(User user);

    public Optional<User> signin(User user);

    public User userCheck(String email);

    public void deleteAccount(User user);

    public List<Activity> myActList(User user, int page);
}
