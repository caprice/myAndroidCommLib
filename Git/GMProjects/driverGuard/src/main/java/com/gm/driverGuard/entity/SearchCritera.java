package com.gm.driverGuard.entity;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;


public class SearchCritera {
	private String vinStr;
	private String date;
	private String vin_2_9;
	private String vin_10_17;
	private String year;
	private String day;
	private String month;
	private String hours;
	private String minutes;
	private String seconds;
	private String uploadTime;
	private String type;
	private String interval;
	private String params;
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	public String getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}
	public final String getDate() {
		return date;
	}
	public final void setDate(String date) {
		this.date = date;
	}
	public final String getVinStr() {
		return vinStr;
	}
	public final void setVinStr(String vinStr) {
		this.vinStr = vinStr;
	}
	public String getVin2_9(){
		String[] arr = vinStr.split(";");
		if(arr.length>1){
			return arr[0];
		}
		return "";
	}
	public String getVin10_17(){
		String[] arr = vinStr.split(";");
		if(arr.length>1){
			return arr[1];
		}
		return "";
	}
	
	public DateTime getDateTime(){
		return DateTime.parse(date, DateTimeFormat.forPattern("MM/dd/yyyy"));
	}
	public final String getVin_2_9() {
		return vin_2_9;
	}
	public final void setVin_2_9(String vin_2_9) {
		this.vin_2_9 = vin_2_9;
	}
	public final String getVin_10_17() {
		return vin_10_17;
	}
	public final void setVin_10_17(String vin_10_17) {
		this.vin_10_17 = vin_10_17;
	}
	public final String getYear() {
		return year;
	}
	public final void setYear(String year) {
		this.year = year;
	}
	public final String getDay() {
		return day;
	}
	public final void setDay(String day) {
		this.day = day;
	}
	public final String getMonth() {
		return month;
	}
	public final void setMonth(String month) {
		this.month = month;
	}
	public final String getHours() {
		return hours;
	}
	public final void setHours(String hours) {
		this.hours = hours;
	}
	public final String getMinutes() {
		return minutes;
	}
	public final void setMinutes(String minutes) {
		this.minutes = minutes;
	}
	public final String getSeconds() {
		return seconds;
	}
	public final void setSeconds(String seconds) {
		this.seconds = seconds;
	}
	public final String getType() {
		return type;
	}
	public final void setType(String type) {
		this.type = type;
	}
	public final String getInterval() {
		return interval;
	}
	public final void setInterval(String interval) {
		this.interval = interval;
	}
	
}
