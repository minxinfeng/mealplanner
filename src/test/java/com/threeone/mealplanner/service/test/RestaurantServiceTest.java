package com.threeone.mealplanner.service.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.threeone.mealplanner.common.InternalException;
import com.threeone.mealplanner.service.RestaurantService;
import com.threeone.mealplanner.test.common.AbstractSpringCommonTest;

public class RestaurantServiceTest extends AbstractSpringCommonTest {

	@Autowired
	private RestaurantService restaurantService;
	
	@Test
	public void test(){
		try {
			restaurantService.getRestInfoForMaps("阿里食府,西贝,ddd");
		} catch (InternalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
