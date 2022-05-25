package com.namoonhee.plogging.service;

import java.util.Optional;

import com.namoonhee.plogging.model.User;
import com.namoonhee.plogging.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public void signup(User user) {
        userRepository.save(user);
        
    }

    @Override
    public Optional<User> signin(User user) {
        
        Optional<User> opt = userRepository.findByEmailAndPwd(user.getEmail(), user.getPwd());
        
        return opt;
    }

    @Override
    public User userCheck(String email) {
        User user = userRepository.findByEmail(email);
        return user;
    }

    @Override
    public void deleteAccount(User user) {
        userRepository.deleteByEmail(user.getEmail());
        
    }
    
}
