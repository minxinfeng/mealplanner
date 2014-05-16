package com.threeone.mealplanner.model;

/**
 * ���ص�ͼ�ϵĲ���������Ϣ
 * @author fengxiangmin
 *
 */
public class RestInfoForMap {

	private int restId;
	private String restName;
	private double longitude;//����
	private double latitude;//γ��

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
