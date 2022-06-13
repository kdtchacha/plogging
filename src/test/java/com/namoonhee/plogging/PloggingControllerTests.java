package com.namoonhee.plogging;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.namoonhee.plogging.model.ActFile;
import com.namoonhee.plogging.model.Activity;
import com.namoonhee.plogging.repository.ActFileRepository;


@SpringBootTest
class PloggingControllerTests {
    // @Autowired
    // ActFileRepository actFileRepository;

    // @Test @Transactional
    // void test1(){
    //     Activity act = new Activity();
    //     act.setId(74L);
    //     actFileRepository.deleteByActivity(act);
    // }
    
}
