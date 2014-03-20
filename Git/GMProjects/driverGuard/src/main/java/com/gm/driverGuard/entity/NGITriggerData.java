package com.gm.driverGuard.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import com.datastax.driver.core.Row;

public class NGITriggerData implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1866685748071060513L;
	
	private int vinId;
	private Date triggerDate;
	private int triggerType;
	private Date triggerTime;
	private Map<Integer,BigDecimal> sensorData;
	
	public static ObjectConverter<NGITriggerData> getConverter(){
		return new ObjectConverter<NGITriggerData>(){
			public NGITriggerData convert(Row row) {
				return new NGITriggerData(
						row.getInt("vin_id"),
						row.getInt("trigger_type"),
						row.getDate("created_date"),
						row.getDate("created_time"),
						row.getMap("values",Integer.class,BigDecimal.class)
				);
			}
		};
	}
	
	public NGITriggerData(){}
	
	public NGITriggerData(int vin_id,int triggerType, Date triggerDate,Date triggerTime,
			Map<Integer, BigDecimal> sensorData) {
		this.vinId=vin_id;
		this.triggerDate=triggerDate;
		this.triggerType = triggerType;
		this.triggerTime = triggerTime;
		this.sensorData = sensorData;
	}
	
	public int getTriggerType() {
		return triggerType;
	}
	public void setTriggerType(int triggerType) {
		this.triggerType = triggerType;
	}
	public Date getTriggerTime() {
		return triggerTime;
	}
	public void setTriggerTime(Date triggerTime) {
		this.triggerTime = triggerTime;
	}
	public Map<Integer, BigDecimal> getSensorData() {
		return sensorData;
	}
	public void setSensorData(Map<Integer, BigDecimal> sensorData) {
		this.sensorData = sensorData;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((sensorData == null) ? 0 : sensorData.hashCode());
		result = prime * result
				+ ((triggerTime == null) ? 0 : triggerTime.hashCode());
		result = prime * result + triggerType;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NGITriggerData other = (NGITriggerData) obj;
		if (sensorData == null) {
			if (other.sensorData != null)
				return false;
		} else if (!sensorData.equals(other.sensorData))
			return false;
		if (triggerTime == null) {
			if (other.triggerTime != null)
				return false;
		} else if (!triggerTime.equals(other.triggerTime))
			return false;
		if (triggerType != other.triggerType)
			return false;
		return true;
	}

	public int getVinId() {
		return vinId;
	}

	public void setVinId(int vinId) {
		this.vinId = vinId;
	}

	public Date getTriggerDate() {
		return triggerDate;
	}

	public void setTriggerDate(Date triggerDate) {
		this.triggerDate = triggerDate;
	}

	@Override
	public String toString() {
		return "NGITriggerData [vinId=" + vinId + ", triggerDate="
				+ triggerDate + ", triggerType=" + triggerType
				+ ", triggerTime=" + triggerTime + ", sensorData=" + sensorData
				+ "]";
	}
}
