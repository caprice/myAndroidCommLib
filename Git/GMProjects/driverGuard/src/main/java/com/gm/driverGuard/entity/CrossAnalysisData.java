package com.gm.driverGuard.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.datastax.driver.core.Row;

public class CrossAnalysisData implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7447869070355816330L;
	private int vinId;
	private Date startDate;
	private int summaryType;
	private int crossType;
	private int idx;
	private BigDecimal valueX;
	private BigDecimal valueY;
	private int samples;
	
	public static ObjectConverter<CrossAnalysisData> getConverter(){
		return new ObjectConverter<CrossAnalysisData>(){
			public CrossAnalysisData convert(Row row) {
				CrossAnalysisData obj=new CrossAnalysisData();
				obj.setVinId(row.getInt("vin_id"));
				obj.setStartDate(row.getDate("summary_sdate"));
				obj.setSummaryType(row.getInt("summary_type"));
				obj.setCrossType(row.getInt("cross_type"));
				obj.setIdx(row.getInt("idx"));
				obj.setValueX(row.getDecimal("value_x"));
				obj.setValueY(row.getDecimal("value_y"));
				obj.setSamples(row.getInt("samples"));
				
				return obj;
			}
		};
	}
	
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date sDate) {
		this.startDate = sDate;
	}
	
	public int getSummaryType() {
		return summaryType;
	}
	public void setSummaryType(int summaryType) {
		this.summaryType = summaryType;
	}
	public int getCrossType() {
		return crossType;
	}
	public void setCrossType(int crossType) {
		this.crossType = crossType;
	}
	public BigDecimal getValueX() {
		return valueX;
	}
	public void setValueX(BigDecimal valueX) {
		this.valueX = valueX;
	}
	public BigDecimal getValueY() {
		return valueY;
	}
	public void setValueY(BigDecimal valueY) {
		this.valueY = valueY;
	}
	public int getSamples() {
		return samples;
	}
	public void setSamples(int samples) {
		this.samples = samples;
	}
	public int getVinId() {
		return vinId;
	}
	public void setVinId(int vinId) {
		this.vinId = vinId;
	}

	public int getIdx() {
		return idx;
	}


	public void setIdx(int idx) {
		this.idx = idx;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + crossType;
		result = prime * result + idx;
		result = prime * result + samples;
		result = prime * result
				+ ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + summaryType;
		result = prime * result + ((valueX == null) ? 0 : valueX.hashCode());
		result = prime * result + ((valueY == null) ? 0 : valueY.hashCode());
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
		CrossAnalysisData other = (CrossAnalysisData) obj;
		if (crossType != other.crossType)
			return false;
		if (idx != other.idx)
			return false;
		if (samples != other.samples)
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (summaryType != other.summaryType)
			return false;
		if (valueX == null) {
			if (other.valueX != null)
				return false;
		} else if (!valueX.equals(other.valueX))
			return false;
		if (valueY == null) {
			if (other.valueY != null)
				return false;
		} else if (!valueY.equals(other.valueY))
			return false;
		if (vinId != other.vinId)
			return false;
		return true;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CrossAnalysisData [vinId=");
		builder.append(vinId);
		builder.append(", startDate=");
		builder.append(startDate);
		builder.append(", summaryType=");
		builder.append(summaryType);
		builder.append(", crossType=");
		builder.append(crossType);
		builder.append(", idx=");
		builder.append(idx);
		builder.append(", valueX=");
		builder.append(valueX);
		builder.append(", valueY=");
		builder.append(valueY);
		builder.append(", samples=");
		builder.append(samples);
		builder.append("]");
		return builder.toString();
	}
}
