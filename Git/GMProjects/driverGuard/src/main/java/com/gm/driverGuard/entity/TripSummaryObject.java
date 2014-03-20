package com.gm.driverGuard.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import com.datastax.driver.core.Row;

public class TripSummaryObject implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6525031721412570213L;
	
	private int vinId;
	private int tripId;
	private Date tripStartTime;
	private Date tripEndTime;
	private UUID tripUUID;
	private Map<Integer,BigDecimal> tripMetrics;
	
	public TripSummaryObject(){}
	
	
	public TripSummaryObject(int vinId,int tripId, Date tripStartTime, Date tripEndTime,
			Map<Integer, BigDecimal> tripMetrics,UUID tripUUID) {
		super();
		this.vinId=vinId;
		this.tripId = tripId;
		this.tripStartTime = tripStartTime;
		this.tripEndTime = tripEndTime;
		this.tripMetrics = tripMetrics;
		this.tripUUID=tripUUID;
	}
	
	
	public static ObjectConverter<TripSummaryObject> getConverter(){
		return new ObjectConverter<TripSummaryObject>(){
			public TripSummaryObject convert(Row row) {
				return new TripSummaryObject(
						row.getInt("vin_id"),
						row.getInt("trip_id"),
						row.getDate("trip_stime"),
						row.getDate("trip_etime"),
						row.getMap("values",Integer.class,BigDecimal.class),
						row.getUUID("trip_uuid")
				);
			}
		};
	}
	
	public int getTripId() {
		return tripId;
	}
	public void setTripId(int tripId) {
		this.tripId = tripId;
	}
	public Date getTripStartTime() {
		return tripStartTime;
	}
	public void setTripStartTime(Date tripStartTime) {
		this.tripStartTime = tripStartTime;
	}
	public Date getTripEndTime() {
		return tripEndTime;
	}
	public void setTripEndTime(Date tripEndTime) {
		this.tripEndTime = tripEndTime;
	}
	public Map<Integer, BigDecimal> getTripMetrics() {
		return tripMetrics;
	}
	public void setTripMetrics(Map<Integer, BigDecimal> tripMetrics) {
		this.tripMetrics = tripMetrics;
	}
	
	public int getVinId() {
		return vinId;
	}


	public void setVinId(int vinId) {
		this.vinId = vinId;
	}




	public UUID getTripUUID() {
		return tripUUID;
	}


	public void setTripUUID(UUID tripUUID) {
		this.tripUUID = tripUUID;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((tripEndTime == null) ? 0 : tripEndTime.hashCode());
		result = prime * result + tripId;
		result = prime * result
				+ ((tripMetrics == null) ? 0 : tripMetrics.hashCode());
		result = prime * result
				+ ((tripStartTime == null) ? 0 : tripStartTime.hashCode());
		result = prime * result
				+ ((tripUUID == null) ? 0 : tripUUID.hashCode());
		result = prime * result + vinId;
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
		TripSummaryObject other = (TripSummaryObject) obj;
		if (tripEndTime == null) {
			if (other.tripEndTime != null)
				return false;
		} else if (!tripEndTime.equals(other.tripEndTime))
			return false;
		if (tripId != other.tripId)
			return false;
		if (tripMetrics == null) {
			if (other.tripMetrics != null)
				return false;
		} else if (!tripMetrics.equals(other.tripMetrics))
			return false;
		if (tripStartTime == null) {
			if (other.tripStartTime != null)
				return false;
		} else if (!tripStartTime.equals(other.tripStartTime))
			return false;
		if (tripUUID == null) {
			if (other.tripUUID != null)
				return false;
		} else if (!tripUUID.equals(other.tripUUID))
			return false;
		if (vinId != other.vinId)
			return false;
		return true;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TripSummaryObject [vinId=");
		builder.append(vinId);
		builder.append(", tripId=");
		builder.append(tripId);
		builder.append(", tripStartTime=");
		builder.append(tripStartTime);
		builder.append(", tripEndTime=");
		builder.append(tripEndTime);
		builder.append(", tripUUID=");
		builder.append(tripUUID);
		builder.append(", tripMetrics=");
		builder.append(tripMetrics);
		builder.append("]");
		return builder.toString();
	}
}
