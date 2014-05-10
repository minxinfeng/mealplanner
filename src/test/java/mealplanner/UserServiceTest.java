package mealplanner;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JApplet;

import org.aspectj.weaver.NewConstructorTypeMunger;
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
	
	public static void main(String[] args) {
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		try {
			Date mealtime = format1.parse("2013-11-3 23:02:00");
//			Date currentTime = new Date();
//			  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			  String dateString = formatter.format(currentTime);
//			  System.err.println(dateString);
//			  System.err.println(dateString.split(" ")[0]);
//			  System.err.println(mealtime.getHours());
			System.err.println(System.currentTimeMillis());
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}