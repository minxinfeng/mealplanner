package com.threeone.mealplanner.model;

/**
 * 返回地图上的餐厅基本信息
 * @author fengxiangmin
 *
 */
public class RestInfoForMap {

	private int restId;
	private String restName;
	private double longitude;//经度
	private double latitude;//纬度

	public String toString() {
		return "RestInfoForMap: restId=" + restId + ", restName=" + restName + ", longitude=" + longitude + ", latitude=" + latitude;
	}
	public int getRestId() {
		return restId;
	}
	public void setRestId(int restId) {
		this.restId = restId;
	}
	public String getRestName() {
		return restName;
	}
	public void setRestName(String restName) {
		this.restName = restName;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
}
