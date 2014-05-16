package com.threeone.mealplanner.service.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.threeone.mealplanner.common.InternalException;
import com.threeone.mealplanner.service.SequenceService;
import com.threeone.mealplanner.test.common.AbstractSpringCommonTest;

public class SequenceServiceTest extends AbstractSpringCommonTest {

	@Autowired
	private SequenceService sequenceService;
	@Test
	@Rollback(true)
	public void test(){
		try {
			sequenceService.changeToEating(20);
		} catch (InternalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
