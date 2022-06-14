package com.namoonhee.plogging.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.namoonhee.plogging.model.ActLike;
import com.namoonhee.plogging.model.Activity;
import com.namoonhee.plogging.model.User;

public interface ActLikeRepository extends JpaRepository<ActLike, Long> {

    Optional<ActLike> findByActivityAndUser(Activity dbact, User suser);

    
}
