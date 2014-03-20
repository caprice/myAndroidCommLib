package com.gm.driverGuard.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import com.datastax.driver.core.Row;

public class DateSummary implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6171669809991213806L;
	
	private int vin_id;
	private int summary_type;
	private Date summaryDate;
	private Map<Integer,BigDecimal> summaryMetrics;
	
	public DateSummary(){}
	
	public DateSummary(int vin_id,int summary_type,Date summaryDate, Map<Integer, BigDecimal> summaryMetrics) {
		this.vin_id=vin_id;
		this.summary_type=summary_type;
		this.summaryDate = summaryDate;
		this.summaryMetrics = summaryMetrics;
	}
	
	public static ObjectConverter<DateSummary> getConverter(){
		return new ObjectConverter<DateSummary>(){
			public DateSummary convert(Row row) {
				DateSummary obj=new DateSummary(
							row.getInt("vin_id"),
							row.getInt("summary_type"),
							row.getDate("summary_date"),
							row.getMap("values", Integer.class, BigDecimal.class)
						);
				return obj;
			}
		};
	}
	
	public Date getSummaryDate() {
		return summaryDate;
	}
	public void setSummaryDate(Date summaryDate) {
		this.summaryDate = summaryDate;
	}
	public Map<Integer,BigDecimal> getSummaryMetrics() {
		return summaryMetrics;
	}
	public void setSummaryMetrics(Map<Integer,BigDecimal> summaryMetrics) {
		this.summaryMetrics = summaryMetrics;
	}

	public int getVin_id() {
		return vin_id;
	}

	public void setVin_id(int vin_id) {
		this.vin_id = vin_id;
	}

	public int getSummary_type() {
		return summary_type;
	}

	public void setSummary_type(int summary_type) {
		this.summary_type = summary_type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((summaryDate == null) ? 0 : summaryDate.hashCode());
		result = prime * result
				+ ((summaryMetrics == null) ? 0 : summaryMetrics.hashCode());
		result = prime * result + summary_type;
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
		DateSummary other = (DateSummary) obj;
		if (summaryDate == null) {
			if (other.summaryDate != null)
				return false;
		} else if (!summaryDate.equals(other.summaryDate))
			return false;
		if (summaryMetrics == null) {
			if (other.summaryMetrics != null)
				return false;
		} else if (!summaryMetrics.equals(other.summaryMetrics))
			return false;
		if (summary_type != other.summary_type)
			return false;
		if (vin_id != other.vin_id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DateSummary [vin_id=" + vin_id + ", summary_type="
				+ summary_type + ", summaryDate=" + summaryDate
				+ ", summaryMetrics=" + summaryMetrics + "]";
	}
	
	
	
}
