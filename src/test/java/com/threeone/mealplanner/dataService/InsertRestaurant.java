package com.threeone.mealplanner.dataService;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.threeone.mealplanner.model.entity.RestaurantInfo;
import com.threeone.mealplanner.service.RestaurantService;
import com.threeone.mealplanner.test.common.AbstractSpringCommonTest;

public class InsertRestaurant extends AbstractSpringCommonTest{
	
	public static String url = "https://maps.googleapis.com/maps/api/place/search/json?location=39.9508854,116.3341516&radius=500&types=food&language=zh_CN&sensor=true&key=AIzaSyAZbSZ3PPodBqqxzJKsyFtitoprzIRIQWI";
	//private static String url = "https://maps.googleapis.com/maps/api/place/search/json?location=39.9494,116.3498&radius=100&types=food&language=zh_CN&sensor=false&key=AIzaSyAZbSZ3PPodBqqxzJKsyFtitoprzIRIQWI";
	@Autowired
	private RestaurantService restaurantService;
	
	@Test
	@Rollback(false)
	public void insertRest(){
		Map<String, String> map = new HashMap<String, String>();

		String str = HttpUtils.postData(url, map);
		System.err.println(str);
		try {
			JSONObject returnedJsonObject = new JSONObject(str);
			if(returnedJsonObject.getString("status").equalsIgnoreCase("OK")){
				JSONArray jPlaces = returnedJsonObject.getJSONArray("results");
				int placesCount = jPlaces.length();	
				String latString = "";
				String lngString = "";
				for(int i=0; i<placesCount;i++) {
					JSONObject objPlace = jPlaces.getJSONObject(i);
					RestaurantInfo restaurantInfo = new RestaurantInfo();
					if(!objPlace.isNull("name")) 		
						restaurantInfo.setRestname(objPlace.getString("name"));
					if(!objPlace.isNull("vicinity")) 		
						restaurantInfo.setRestaddress(objPlace.getString("vicinity"));
					latString = objPlace.getJSONObject("geometry").getJSONObject("location").getString("lat");
					lngString = objPlace.getJSONObject("geometry").getJSONObject("location").getString("lng");
					restaurantInfo.setLatitude(Double.parseDouble(latString));
					restaurantInfo.setLongitude(Double.parseDouble(lngString));
					restaurantService.registRestaurant(restaurantInfo);
					System.out.println(restaurantInfo.getRestname());
				}

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}

}
