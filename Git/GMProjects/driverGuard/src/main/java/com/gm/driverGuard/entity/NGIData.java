package com.gm.driverGuard.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import com.datastax.driver.core.Row;

public class NGIData implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4112043359374937291L;
	
	public NGIData(){}
	
	
	
	public NGIData(int metricId, int vin_id, Date createdDate,Date createdTime,
			BigDecimal metricValueX, BigDecimal metricValueY,
			BigDecimal metricValueZ,UUID tripUUID) {
		super();
		this.metricId = metricId;
		this.vin_id = vin_id;
		this.createdDate = createdDate;
		this.metricValueX = metricValueX;
		this.metricValueY = metricValueY;
		this.metricValueZ = metricValueZ;
		this.createdTime = createdTime;
		this.tripUUID=tripUUID;
	}



	public static ObjectConverter<NGIData> getConverter(){
		return new ObjectConverter<NGIData>(){
			public NGIData convert(Row row) {
				return new NGIData(
						row.getInt("metric"),
						row.getInt("vin_id"),
						row.getDate("created_date"),
						row.getDate("created_time"),
						row.getDecimal("valueX"),
						row.getDecimal("valueY"),
						row.getDecimal("valueZ"),
						row.getUUID("trip_uuid")
				);
			}
		};
	}

	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	
	private int metricId;
	private int vin_id;
	private Date createdDate;
	private BigDecimal metricValueX;
	private BigDecimal metricValueY;
	private BigDecimal metricValueZ;
	private Date createdTime;
	private UUID tripUUID;

	

	public BigDecimal getMetricValueX() {
		return metricValueX;
	}

	public void setMetricValueX(BigDecimal metricValueX) {
		this.metricValueX = metricValueX;
	}

	public BigDecimal getMetricValueY() {
		return metricValueY;
	}

	public void setMetricValueY(BigDecimal metricValueY) {
		this.metricValueY = metricValueY;
	}

	public BigDecimal getMetricValueZ() {
		return metricValueZ;
	}

	public void setMetricValueZ(BigDecimal metricValueZ) {
		this.metricValueZ = metricValueZ;
	}

	public int getMetricId() {
		return metricId;
	}

	public void setMetricId(int metricId) {
		this.metricId = metricId;
	}

	public int getVin_id() {
		return vin_id;
	}

	public void setVin_id(int vin_id) {
		this.vin_id = vin_id;
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
				+ ((createdDate == null) ? 0 : createdDate.hashCode());
		result = prime * result
				+ ((createdTime == null) ? 0 : createdTime.hashCode());
		result = prime * result + metricId;
		result = prime * result
				+ ((metricValueX == null) ? 0 : metricValueX.hashCode());
		result = prime * result
				+ ((metricValueY == null) ? 0 : metricValueY.hashCode());
		result = prime * result
				+ ((metricValueZ == null) ? 0 : metricValueZ.hashCode());
		result = prime * result
				+ ((tripUUID == null) ? 0 : tripUUID.hashCode());
		result = prime * result + vin_id;
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
		NGIData other = (NGIData) obj;
		if (createdDate == null) {
			if (other.createdDate != null)
				return false;
		} else if (!createdDate.equals(other.createdDate))
			return false;
		if (createdTime == null) {
			if (other.createdTime != null)
				return false;
		} else if (!createdTime.equals(other.createdTime))
			return false;
		if (metricId != other.metricId)
			return false;
		if (metricValueX == null) {
			if (other.metricValueX != null)
				return false;
		} else if (!metricValueX.equals(other.metricValueX))
			return false;
		if (metricValueY == null) {
			if (other.metricValueY != null)
				return false;
		} else if (!metricValueY.equals(other.metricValueY))
			return false;
		if (metricValueZ == null) {
			if (other.metricValueZ != null)
				return false;
		} else if (!metricValueZ.equals(other.metricValueZ))
			return false;
		if (tripUUID == null) {
			if (other.tripUUID != null)
				return false;
		} else if (!tripUUID.equals(other.tripUUID))
			return false;
		if (vin_id != other.vin_id)
			return false;
		return true;
	}



	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("NGIData [metricId=");
		builder.append(metricId);
		builder.append(", vin_id=");
		builder.append(vin_id);
		builder.append(", createdDate=");
		builder.append(createdDate);
		builder.append(", metricValueX=");
		builder.append(metricValueX);
		builder.append(", metricValueY=");
		builder.append(metricValueY);
		builder.append(", metricValueZ=");
		builder.append(metricValueZ);
		builder.append(", createdTime=");
		builder.append(createdTime);
		builder.append(", tripUUID=");
		builder.append(tripUUID);
		builder.append("]");
		return builder.toString();
	}
	
}
