package com.namoonhee.plogging.repository;



import com.namoonhee.plogging.model.ActAnswer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActAnswerRepository extends JpaRepository<ActAnswer, Long>{
    
}
