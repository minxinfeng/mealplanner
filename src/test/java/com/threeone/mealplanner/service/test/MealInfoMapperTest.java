package com.threeone.mealplanner.service.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.threeone.mealplanner.mapper.MealInfoMapper;
import com.threeone.mealplanner.model.MealFriendWithStatus;
import com.threeone.mealplanner.test.common.AbstractSpringCommonTest;

public class MealInfoMapperTest extends AbstractSpringCommonTest {

	@Autowired
	private MealInfoMapper mealInfoMapper;
	
	@Test
	public void test(){
		List<MealFriendWithStatus> list =mealInfoMapper.getMealFriendWithStatus(4);
		System.err.println(list.size());
	}

	public void setMealInfoMapper(MealInfoMapper mealInfoMapper) {
		this.mealInfoMapper = mealInfoMapper;
	}
}
