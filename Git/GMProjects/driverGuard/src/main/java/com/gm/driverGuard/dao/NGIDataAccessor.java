package com.gm.driverGuard.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.gm.driverGuard.entity.DateSummary;
import com.gm.driverGuard.entity.MetricObject;
import com.gm.driverGuard.entity.NGIData;
import com.gm.driverGuard.entity.NGITriggerData;
import com.gm.driverGuard.entity.TripSummaryObject;
import com.gm.driverGuard.entity.Vehicle;

public interface NGIDataAccessor {
	
	public static final int CROSS_ANALYSIS_DATA_TYPE_DAY=0;
	public static final int CROSS_ANALYSIS_DATA_TYPE_MONTH=1;
	public static final int CROSS_ANALYSIS_DATA_TYPE_YEAR=2;
	
	public static final int DATA_SUMMARY_TYPE_DAY=0;
	public static final int DATA_SUMMARY_TYPE_MONTH=1;
	public static final int DATA_SUMMARY_TYPE_YEAR=2;
	/**
	 * insert the NGI data collected from vehicle
	 * @param metricName the metric name
	 * @param vin the vehicle ID(VIN)
	 * @param createdTime the timestamp
	 * @param valueX the metric valueX
	 * @param valueY the metric valueY
	 * @param valueZ the metric valueZ
	 */
	public void insertData(String metricName, String vin, Date createdTime, BigDecimal valueX,BigDecimal valueY,BigDecimal valueZ);
	
	
	/**
	 * get the time series within the given time ranges. only year-month-day part is reserved for sDate and eDate
	 * @param metricName the metric name
	 * @param vin the VIN
	 * @param sDate the start date range
	 * @param eDate the end date range
	 * @param resultData the time series
	 */
	public void getDataSeries(String metricName,String vin,Date sDate, Date eDate,List<NGIData> resultData);
	
	/**
	 * insert the trigger data
	 * @param vin the vehicle ID(VIN)
	 * @param createdTime the time of the trigger
	 * @param triggerType the type of trigger
	 * @param values map of <metric name, metric value>
	 */
	public void insertTriggerData(String vin, Date createdTime, String triggerType, Map<String,BigDecimal> values);
	
	/**
	 * get the time series for the trigger data. only year-month-day part is reserved for sDate and eDate
	 * @param vin
	 * @param sDate start date
	 * @param eDate end date
	 * @param triggerType the type of trigger. return all trigger data if -1
	 * @param resultData time series of trigger data
	 */
	public void getTriggerDataSeries(String vin,Date sDate, Date eDate, String triggerType,List<NGITriggerData> resultData);
	
	/**
	 * get the trip summary
	 * @param vin the Vehicle ID(VIN)
	 * @param tripStartRange the date range to search for the trip
	 * @param tripEndRange the date range to search for the trip
	 * @param resultTrips the result trips
	 */
	public void getTripSummary(String vin, Date tripStartRange, Date tripEndRange,List<TripSummaryObject> resultTrips);
	
	/**
	 * get the date summary. only year-month-day part is reserved for sDate and eDate
	 * @param vin the VIN for the vehicle
	 * @param sDate the date range start
	 * @param eDate the date range end
	 * @param summaryType the summary type. 0-by day, 1-by month, 2-by year
	 * @param metrics the metric names interested
	 * @param resultData the time series data to return
	 */
	public void getDateSummary(String vin, Date sDate, Date eDate, int summaryType, List<String> metrics,List<DateSummary> resultData);
	
	
	/**
	 * cross analysis for metric_x to metric_y. only year-month-day part is reserved for sDate and eDate
	 * @param vin the VIN
	 * @param sDate the date range start
	 * @param eDate the date range end
	 * @param crossType the cross type. i.e. <metric_x,metric_y> combination. TBD
	 * @param resultData the <metric_x,metric_y>
	 */
	public void crossAnalysis(String vin,Date sDate, Date eDate, int crossType,Map<BigDecimal, BigDecimal> resultData);
	
	/**
	 * get metric Id by name
	 * @param metricName
	 * @return
	 */
	public int getMetricId(String metricName);
	
	/**
	 * get the metric name by ID
	 * @param metricId the metric id
	 * @return the metric name
	 */
	public String getMetricName(int metricId);
	
	/**
	 * get the vehicle internal ID by VIN
	 * @param vin the VIN for the vehicle
	 * @return the vehicle internal ID
	 */
	public int getIDByVin(String vin);
	
	/**
	 * add metric in the store
	 * @param metric
	 */
	public void addMetric(MetricObject metric);
	
	public void addVehicle(Vehicle vehicle);
	
}
