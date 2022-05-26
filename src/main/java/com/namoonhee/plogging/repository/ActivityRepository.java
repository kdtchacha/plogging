package com.namoonhee.plogging.repository;

import java.util.List;

import com.namoonhee.plogging.model.Activity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

    List<Activity> findByUser_id(Long id);
    
}
