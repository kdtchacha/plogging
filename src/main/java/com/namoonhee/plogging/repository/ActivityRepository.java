package com.namoonhee.plogging.repository;

import java.util.List;

import com.namoonhee.plogging.model.Activity;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

    List<Activity> findByUser_id(Long id);

    List<Activity> findByVisibility(int i, Pageable p);

    List<Activity> findByUser_id(Long id, Pageable p);

    void deleteById(Long id);
    
}
