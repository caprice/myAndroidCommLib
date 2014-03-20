package com.gm.driverGuard.entity;

import java.io.Serializable;

import com.datastax.driver.core.Row;
public class MetricObject implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8749183589485713883L;
	public static final int METRIC_DATA_TYPE_FLOAT=0;
	public static final int METRIC_DATA_TYPE_INT=1;
	
	private int metricId;
	private String metricValue;
	private int metricType;
	private String metricUnit;
	private int metricDataType;
	private String metricDescription;
	
	public static ObjectConverter<MetricObject> getConverter(){
		return new ObjectConverter<MetricObject>(){
			public MetricObject convert(Row row) {
				MetricObject metric=new MetricObject();
				metric.setMetricId(row.getInt("metric_key"));
				metric.setMetricValue(row.getString("metric_value"));
				metric.setMetricType(row.getInt("metric_type"));
				
				metric.setMetricUnit(row.getString("metric_unit"));
				metric.setMetricDataType(row.getInt("metric_data_type"));
				metric.setMetricDescription(row.getString("metric_description"));
				
				return metric;
			}
		};
	}
	
	public MetricObject(){
		
	}
	
	
	public MetricObject(int metricId, String metricValue, int metricType,
			String metricUnit, int metricDataType, String metricDescription) {
		this.metricId = metricId;
		this.metricValue = metricValue;
		this.metricType = metricType;
		this.metricUnit = metricUnit;
		this.metricDataType = metricDataType;
		this.metricDescription = metricDescription;
	}

	public int getMetricId() {
		return metricId;
	}
	public void setMetricId(int metricId) {
		this.metricId = metricId;
	}
	public String getMetricValue() {
		return metricValue;
	}
	public void setMetricValue(String metricValue) {
		this.metricValue = metricValue;
	}
	public int getMetricType() {
		return metricType;
	}
	public void setMetricType(int metricType) {
		this.metricType = metricType;
	}
	public String getMetricUnit() {
		return metricUnit;
	}
	public void setMetricUnit(String metricUnit) {
		this.metricUnit = metricUnit;
	}
	public int getMetricDataType() {
		return metricDataType;
	}
	public void setMetricDataType(int metricDataType) {
		this.metricDataType = metricDataType;
	}
	public String getMetricDescription() {
		return metricDescription;
	}
	public void setMetricDescription(String metricDescription) {
		this.metricDescription = metricDescription;
	}
	public static int getMetricDataTypeFloat() {
		return METRIC_DATA_TYPE_FLOAT;
	}
	public static int getMetricDataTypeInt() {
		return METRIC_DATA_TYPE_INT;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + metricDataType;
		result = prime
				* result
				+ ((metricDescription == null) ? 0 : metricDescription
						.hashCode());
		result = prime * result + metricId;
		result = prime * result + metricType;
		result = prime * result
				+ ((metricUnit == null) ? 0 : metricUnit.hashCode());
		result = prime * result
				+ ((metricValue == null) ? 0 : metricValue.hashCode());
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
		MetricObject other = (MetricObject) obj;
		if (metricDataType != other.metricDataType)
			return false;
		if (metricDescription == null) {
			if (other.metricDescription != null)
				return false;
		} else if (!metricDescription.equals(other.metricDescription))
			return false;
		if (metricId != other.metricId)
			return false;
		if (metricType != other.metricType)
			return false;
		if (metricUnit == null) {
			if (other.metricUnit != null)
				return false;
		} else if (!metricUnit.equals(other.metricUnit))
			return false;
		if (metricValue == null) {
			if (other.metricValue != null)
				return false;
		} else if (!metricValue.equals(other.metricValue))
			return false;
		return true;
	}	
}
