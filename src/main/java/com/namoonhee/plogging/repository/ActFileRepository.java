package com.namoonhee.plogging.repository;

import java.util.List;

import com.namoonhee.plogging.model.ActFile;
import com.namoonhee.plogging.model.Activity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ActFileRepository extends JpaRepository<ActFile, Long> {
    
    List<ActFile> findByActivity(Activity act);

    void deleteByActivity(Activity act);


}
