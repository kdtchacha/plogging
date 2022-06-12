package com.namoonhee.plogging.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.namoonhee.plogging.model.Activity;
import com.namoonhee.plogging.model.User;
import com.namoonhee.plogging.repository.ActivityRepository;
import com.namoonhee.plogging.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ActivityRepository activityRepository;

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
    @Transactional
    public void deleteAccount(User user) {
        userRepository.deleteByEmailAndPwd(user.getEmail(), user.getPwd());

    }

    @Override
    public List<Activity> myActList(User user, int page) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createDate");
        Pageable p = PageRequest.of(page - 1, 4, sort);
        List<Activity> opt = activityRepository.findByUser_id(user.getId(), p);

        return opt;
    }

}
