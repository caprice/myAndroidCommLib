package com.gm.driverGuard.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.apache.commons.lang3.time.DateUtils;

import com.gm.driverGuard.dao.NGIDataAccessor;
import com.gm.driverGuard.entity.CrossAnalysisData;
import com.gm.driverGuard.entity.DateSummary;
import com.gm.driverGuard.entity.MetricObject;
import com.gm.driverGuard.entity.NGIData;
import com.gm.driverGuard.entity.NGITriggerData;
import com.gm.driverGuard.entity.TripSummaryObject;
import com.gm.driverGuard.entity.Vehicle;
import com.gm.driverGuard.exception.DataAccessException;
import com.gm.driverGuard.helper.CacheHelper;
import com.gm.driverGuard.helper.CassandraSessionHelper;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class NGIDataAccessorImpl implements NGIDataAccessor {

	private final CacheLoader<Object, MetricObject> metricCacheLoaderKey = new CacheLoader<Object, MetricObject>() {
		public MetricObject load(Object key) {
			return getMetricFromStore(key);
		}
	};

	private CassandraSessionHelper sessionHelper;

	private LoadingCache<Object, MetricObject> cache;

	private static final String metricFromIdSql = "SELECT * FROM ngi.metric WHERE metric_key=?";
	private static final String metricFromNameSql = "SELECT * FROM ngi.metric WHERE metric_value=?";
	private static final String getIdByVinSql = "SELECT * FROM ngi.vehicles WHERE vin=?";
	private static final String insertDataSql = "INSERT INTO ngi.data(metric,vin_id,created_date,created_time,valueX,valueY,valueZ) VALUES(?,?,?,?,?,?,?)";
	private static final String queryDataSql = "SELECT * FROM ngi.data WHERE created_date>=? AND created_date<=? AND metric=? AND vin_id=?";
	private static final String insertTriggerSql = "INSERT INTO ngi.trigger_data(vin_id,created_date,trigger_type,created_time,values) VALUES(?,?,?,?,?)";
	private static final String getTriggerDataSql = "SELECT * FROM ngi.trigger_data WHERE trigger_type=? AND vin_id=? AND created_date>=? AND created_date<=?";
	private static final String getTripSummarySql = "SELECT * FROM ngi.trip_summary WHERE vin_id=? AND trip_stime>=? AND trip_stime<=?";
	private static final String getDateSummarySql = "SELECT * FROM ngi.data_time_summary WHERE vin_id=? AND summary_type=? AND summary_date>=? AND summary_date<=?";
	private static final String getCrossAnalysisDataSql = "SELECT * FROM ngi.data_cross_analysis WHERE vin_id=? AND summary_type=? AND cross_type=? AND summary_sdate>=? AND summary_sdate<=?";
	private static final String insertMetricSql = "INSERT INTO ngi.metric(metric_key,metric_value,metric_type,metric_unit,metric_data_type,metric_description) values(?,?,?,?,?,?)";
	private static final String insertVehicleSql = "INSERT INTO ngi.vehicles(vin_id,vin) VALUES(?,?)";

	@SuppressWarnings("unchecked")
	public NGIDataAccessorImpl() {
		this.cache = CacheHelper.getCache(metricCacheLoaderKey);
	}

	public void insertData(String metricName, String vin, Date createdTime, BigDecimal valueX, BigDecimal valueY, BigDecimal valueZ) {

		Date createdDate = DateUtils.truncate(createdTime, java.util.Calendar.DAY_OF_MONTH);

		sessionHelper.insert(insertDataSql, getMetricId(metricName), this.getIDByVin(vin), createdDate, createdTime, valueX, valueY, valueZ);
	}

	@SuppressWarnings("unchecked")
	public void getDataSeries(String metricName, String vin, Date sDate, Date eDate, List<NGIData> resultData) {
		sDate = DateUtils.truncate(sDate, Calendar.DAY_OF_MONTH);
		eDate = DateUtils.truncate(eDate, Calendar.DAY_OF_MONTH);

		resultData.addAll((List<NGIData>) sessionHelper.getAll(queryDataSql, NGIData.getConverter(), sDate, eDate, this.getMetricId(metricName), this.getIDByVin(vin)));
	}

	public void insertTriggerData(String vin, Date createdTime, String trigger_type, Map<String, BigDecimal> values) {

		// convert to id map
		Map<Integer, BigDecimal> convertedValues = new HashMap<Integer, BigDecimal>();
		for (String value : values.keySet())
			convertedValues.put(this.getMetricId(value), values.get(value));

		Date createdDate = DateUtils.truncate(createdTime, java.util.Calendar.DAY_OF_MONTH);
		sessionHelper.insert(insertTriggerSql, this.getIDByVin(vin), createdDate, this.getMetricId(trigger_type), createdTime, convertedValues);
	}

	@SuppressWarnings("unchecked")
	public void getTriggerDataSeries(String vin, Date sDate, Date eDate, String triggerType, List<NGITriggerData> resultData) {
		sDate = DateUtils.truncate(sDate, Calendar.DAY_OF_MONTH);
		eDate = DateUtils.truncate(eDate, Calendar.DAY_OF_MONTH);

		resultData.addAll((List<NGITriggerData>) sessionHelper.getAll(getTriggerDataSql, NGITriggerData.getConverter(), this.getMetricId(triggerType), this.getIDByVin(vin), sDate, eDate));
	}

	@SuppressWarnings("unchecked")
	public void getTripSummary(String vin, Date tripStartRange, Date tripEndRange, List<TripSummaryObject> resultTrips) {
		resultTrips.addAll((List<TripSummaryObject>) sessionHelper.getAll(getTripSummarySql, TripSummaryObject.getConverter(), this.getIDByVin(vin), tripStartRange, tripEndRange));

	}

	@SuppressWarnings("unchecked")
	public void getDateSummary(String vin, Date sDate, Date eDate, int summaryType, List<String> metrics, List<DateSummary> resultData) {
		sDate = DateUtils.truncate(sDate, Calendar.DAY_OF_MONTH);
		eDate = DateUtils.truncate(eDate, Calendar.DAY_OF_MONTH);

		resultData.addAll((List<DateSummary>) sessionHelper.getAll(getDateSummarySql, DateSummary.getConverter(), this.getIDByVin(vin), summaryType, sDate, eDate));
	}

	public void crossAnalysis(String vin, Date sDate, Date eDate, int crossType, Map<BigDecimal, BigDecimal> resultData) {
		List<CrossAnalysisData> resultList = new ArrayList<CrossAnalysisData>();

		sDate = DateUtils.truncate(sDate, Calendar.DAY_OF_MONTH);
		eDate = DateUtils.truncate(eDate, Calendar.DAY_OF_MONTH);
		crossAnalysisFull(vin, sDate, eDate, crossType, resultList);

		// clear the result data first
		resultData.clear();
		Map<BigDecimal, Integer> samples = new HashMap<BigDecimal, Integer>();
		Map<BigDecimal, Float> totalValueY = new HashMap<BigDecimal, Float>();
		int origSamples = 0;
		float origTotalValueY = 0f;
		for (CrossAnalysisData data : resultList) {
			origSamples = samples.get(data.getValueX()) == null ? 0 : samples.get(data.getValueX());
			origTotalValueY = totalValueY.get(data.getValueX()) == null ? 0f : totalValueY.get(data.getValueX());

			origSamples += data.getSamples();
			origTotalValueY += data.getValueY().floatValue() * data.getSamples();

			samples.put(data.getValueX(), origSamples);
			totalValueY.put(data.getValueX(), origTotalValueY);
		}

		for (BigDecimal key : totalValueY.keySet()) {
			resultData.put(key, new BigDecimal(totalValueY.get(key) / samples.get(key)));
		}
	}

	/**
	 * get the full year range covered by [startDate,endDate]
	 * 
	 * @param startDate
	 * @param endDate
	 * @return the full year range [value(0),value(1)] or null
	 */
	private Date[] getFullYearRange(Date startDate, Date endDate) {
		Date[] results = new Date[2];
		Date startYearStart = DateUtils.truncate(startDate, java.util.Calendar.YEAR);
		results[0] = (startYearStart.equals(startDate) ? startYearStart : DateUtils.addYears(startYearStart, 1));

		Date endYearStart = DateUtils.truncate(endDate, java.util.Calendar.YEAR);
		results[1] = DateUtils.addDays(endYearStart, -1);

		if (results[0].compareTo(results[1]) > 0)
			return null;

		else
			return results;
	}

	/**
	 * get the full month range covered by [startDate,endDate]
	 * 
	 * @param startDate
	 * @param endDate
	 * @return the full month range [value(0),value(1)] or null
	 */
	private Date[] getFullMonthRange(Date startDate, Date endDate) {
		Date results[] = new Date[2];
		Date startMonthStart = DateUtils.truncate(startDate, Calendar.MONTH);
		results[0] = (startMonthStart.equals(startDate) ? startMonthStart : DateUtils.addMonths(startMonthStart, 1));

		Date endMonthStart = DateUtils.truncate(endDate, java.util.Calendar.MONTH);
		results[1] = DateUtils.addDays(endMonthStart, -1);

		if (results[0].compareTo(results[1]) > 0)
			return null;

		return results;
	}

	/**
	 * get the analytics data within range of [sDate,eDate]. Note: only date
	 * part is reserved
	 * 
	 * @param vin
	 * @param sDate
	 * @param eDate
	 * @param crossType
	 * @param results
	 */
	private void crossAnalysisFull(String vin, Date sDate, Date eDate, int crossType, List<CrossAnalysisData> results) {
		Date[] fullYears = getFullYearRange(sDate, eDate);
		if (fullYears == null) {
			// not covered by full years
			crossAnalysisMonthDays(vin, sDate, eDate, crossType, results);
			return;
		}

		if (!sDate.equals(fullYears[0])) {
			// get the starting days
			crossAnalysisMonthDays(vin, sDate, fullYears[0], crossType, results);
		}

		// get the full year
		crossAnalysis(vin, fullYears[0], fullYears[1], crossType, NGIDataAccessor.CROSS_ANALYSIS_DATA_TYPE_YEAR, results);

		if (!eDate.equals(fullYears[1])) {
			// get the ending days
			crossAnalysisMonthDays(vin, fullYears[1], eDate, crossType, results);
		}
	}

	/**
	 * get the analytic data which is not covered by full year
	 * 
	 * @param vin
	 * @param sDate
	 * @param eDate
	 * @param crossType
	 * @param results
	 */
	private void crossAnalysisMonthDays(String vin, Date sDate, Date eDate, int crossType, List<CrossAnalysisData> results) {
		Date[] fullMonthRange = getFullMonthRange(sDate, eDate);
		if (fullMonthRange == null) {
			// not covered by full month
			crossAnalysis(vin, sDate, eDate, crossType, NGIDataAccessor.CROSS_ANALYSIS_DATA_TYPE_DAY, results);
			return;
		}

		// calculate the starting days
		if (!fullMonthRange[0].equals(sDate)) {
			crossAnalysis(vin, sDate, DateUtils.addDays(fullMonthRange[0], -1), crossType, NGIDataAccessor.CROSS_ANALYSIS_DATA_TYPE_DAY, results);
		}

		// calcuate the full months
		crossAnalysis(vin, fullMonthRange[0], fullMonthRange[1], crossType, NGIDataAccessor.CROSS_ANALYSIS_DATA_TYPE_MONTH, results);

		// calculate the ending days
		if (!fullMonthRange[1].equals(eDate)) {
			crossAnalysis(vin, DateUtils.addDays(fullMonthRange[1], 1), eDate, crossType, NGIDataAccessor.CROSS_ANALYSIS_DATA_TYPE_DAY, results);
		}
	}

	/**
	 * cross analysis by data type
	 * 
	 * @param vin
	 * @param sDate
	 * @param eDate
	 * @param crossType
	 * @param crossDataType
	 * @param results
	 */
	@SuppressWarnings("unchecked")
	private void crossAnalysis(String vin, Date sDate, Date eDate, int crossType, int crossDataType, List<CrossAnalysisData> results) {
		results.addAll((List<CrossAnalysisData>) sessionHelper.getAll(getCrossAnalysisDataSql, CrossAnalysisData.getConverter(), this.getIDByVin(vin), crossDataType, crossType, sDate, eDate));
	}

	public int getMetricId(String metricName) {
		try {
			MetricObject metric = cache.get(metricName);
			if (metric == null)
				throw new DataAccessException("metric name not found:" + metricName);
			return metric.getMetricId();
		} catch (ExecutionException e) {
			throw new DataAccessException(e);
		}

	}

	public String getMetricName(int metricId) {
		try {
			MetricObject metric = cache.get(metricId);
			if (metric == null)
				throw new DataAccessException("metric Id not found:" + metricId);
			return metric.getMetricValue();
		} catch (ExecutionException e) {
			throw new DataAccessException(e);
		}
	}

	public int getIDByVin(String vin) {
		Vehicle vehicle = (Vehicle) sessionHelper.getOne(getIdByVinSql, Vehicle.getConverter(), vin);
		if (vehicle == null)
			throw new DataAccessException("Vin not found:" + vin);

		return vehicle.getId();
	}

	private MetricObject getMetricFromStore(Object metricIdOrName) {
		String sql = null;

		if (metricIdOrName instanceof Integer) {
			sql = metricFromIdSql;
		} else if (metricIdOrName instanceof String) {
			sql = metricFromNameSql;
		}
		return (MetricObject) sessionHelper.getOne(sql, MetricObject.getConverter(), metricIdOrName);
	}

	public void addMetric(MetricObject metric) {
		// insertMetricSql="INSERT INTO ngi.metric(metric_key,metric_value,metric_type,metric_unit,metric_data_type,metric_description) values(?,?,?,?,?,?)";
		sessionHelper.insert(insertMetricSql, metric.getMetricId(), metric.getMetricValue(), metric.getMetricType(), metric.getMetricUnit(), metric.getMetricDataType(), metric.getMetricDescription());
	}

	public void addVehicle(Vehicle vehicle) {
		// insertVehicleSql="INSERT INTO ngi.vehicles(vin_id,vin) VALUES(?,?)";
		sessionHelper.insert(insertVehicleSql, vehicle.getId(), vehicle.getVin());

	}

	public CassandraSessionHelper getSessionHelper() {
		return sessionHelper;
	}

	public void setSessionHelper(CassandraSessionHelper sessionHelper) {
		this.sessionHelper = sessionHelper;
	}
}
