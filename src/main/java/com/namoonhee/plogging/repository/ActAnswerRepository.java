package com.namoonhee.plogging.repository;

import java.util.List;

import com.namoonhee.plogging.model.ActAnswer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ActAnswerRepository extends JpaRepository<ActAnswer, Long>{
    
    List<ActAnswer> findByUser_id(Long id);
}
