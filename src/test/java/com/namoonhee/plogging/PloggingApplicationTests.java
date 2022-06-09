package com.namoonhee.plogging;

import com.namoonhee.plogging.repository.ActAnswerRepository;
import com.namoonhee.plogging.repository.ActivityRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PloggingApplicationTests {

	@Autowired
	ActAnswerRepository actAnswerRepository;

	@Autowired
	ActivityRepository activityRepository;

	@Autowired
	



	@Test
	void contextLoads() {
	}

}
