package com.namoonhee.plogging;

import com.namoonhee.plogging.model.ActFile;
import com.namoonhee.plogging.repository.ActAnswerRepository;
import com.namoonhee.plogging.repository.ActFileRepository;
import com.namoonhee.plogging.repository.ActivityRepository;
import com.namoonhee.plogging.repository.UserRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PloggingRepositoryTests {

	@Autowired
	ActivityRepository activityRepository;
	
	@Autowired
	ActAnswerRepository actAnswerRepository;

	@Autowired
	ActFileRepository actFileRepository;

	@Autowired
	UserRepository userRepository;




	@Test
	void contextLoads() {
	}

}
