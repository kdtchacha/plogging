package com.namoonhee.plogging;

import com.namoonhee.plogging.model.Activity;
import com.namoonhee.plogging.model.User;
import com.namoonhee.plogging.repository.ActAnswerRepository;
import com.namoonhee.plogging.repository.ActFileRepository;
import com.namoonhee.plogging.repository.ActivityRepository;
import com.namoonhee.plogging.repository.UserRepository;

import java.util.Date;

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

	// @Test
	// void activityRepositoryCreateTest() {

	// 	Activity act = new Activity();
	// 	act.setActDistance("1234m");
	// 	act.setActMemo("test_memo");
	// 	act.setActName("test_name");
	// 	act.setActTime("xx:xx:xx");
	// 	act.setCreateDate(new Date());

	// 	User user = new User();
	// 	user.setId(1L);
	// 	act.setUser(user);

	// 	activityRepository.save(act);
	// }
	
}
