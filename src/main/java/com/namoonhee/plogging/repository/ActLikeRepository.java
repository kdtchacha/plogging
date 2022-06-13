package com.namoonhee.plogging.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.namoonhee.plogging.model.ActLike;

public interface ActLikeRepository extends JpaRepository<ActLike, Long> {
    
}
