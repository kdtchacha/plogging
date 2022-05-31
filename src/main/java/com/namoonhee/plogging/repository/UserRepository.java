package com.namoonhee.plogging.repository;

import java.util.Optional;

import com.namoonhee.plogging.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailAndPwd(String email, String pwd);

    User findByEmail(String email);

    void deleteByEmailAndPwd(String email, String pwd);

    Optional<User> findById(Long id);

}
