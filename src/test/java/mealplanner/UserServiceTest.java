package mealplanner;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.threeone.mealplanner.service.UserService;

public class UserServiceTest {

	@Autowired
	private UserService userService;

	@Test
	public void test(){

	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}