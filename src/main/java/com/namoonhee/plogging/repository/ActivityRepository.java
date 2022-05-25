package com.namoonhee.plogging.repository;

import com.namoonhee.plogging.model.Activity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
    
}
