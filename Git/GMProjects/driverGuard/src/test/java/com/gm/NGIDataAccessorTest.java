package com.gm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import junit.framework.TestCase;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;

import com.gm.driverGuard.dao.NGIDataAccessor;
import com.gm.driverGuard.entity.DateSummary;
import com.gm.driverGuard.entity.MetricObject;
import com.gm.driverGuard.entity.NGIData;
import com.gm.driverGuard.entity.NGITriggerData;
import com.gm.driverGuard.entity.TripSummaryObject;
import com.gm.driverGuard.entity.Vehicle;
import com.gm.driverGuard.helper.CassandraSessionHelper;

public class NGIDataAccessorTest extends TestCase{
	private ApplicationContext context;
	private NGIDataAccessor dataAccessor;
	private CassandraSessionHelper sessionHelper;
	
	@Override
	public void setUp(){
		context=new ClassPathXmlApplicationContext(new String[]{
				"spring/applicationContext.xml"   
		   });
		dataAccessor=(NGIDataAccessor)context.getBean("ngiDataAccessor");
		sessionHelper=(CassandraSessionHelper)context.getBean("cassandraSessionHelper");
	}
	@Override
	public void tearDown(){
		((CassandraSessionHelper)context.getBean("cassandraSessionHelper")).close();
		((ClassPathXmlApplicationContext)context).close();
	}
	
	public void testInsertData(){
		Date startDate=DateUtils.truncate(new Date(), java.util.Calendar.DAY_OF_MONTH);
		int rand=Math.abs(new Random(new Date().getTime()).nextInt());
		String metricName="metric_"+rand;
		String vin="vin_"+rand;
		
		//insert metric
		dataAccessor.addMetric(new MetricObject(rand,metricName,-1,"%",0,"example metric"));
		try{
			dataAccessor.getMetricId(metricName);
		}catch(DataAccessException e){
			assertTrue(false);
		}
		
		//insert vehicle
		dataAccessor.addVehicle(new Vehicle(rand,vin));
		try{
			dataAccessor.getIDByVin(vin);
		}catch(DataAccessException e){
			assertTrue(false);
		}
		
		//insert data
		dataAccessor.insertData(metricName, vin, new Date(), new BigDecimal(1.0f),null,null);
		dataAccessor.insertData(metricName, vin, new Date(), new BigDecimal(2.0f),null,null);
		dataAccessor.insertData(metricName, vin, new Date(), new BigDecimal(3.0f),null,null);
		
		List<NGIData> resultData=new ArrayList<NGIData>();
		dataAccessor.getDataSeries(metricName,vin,startDate,startDate,resultData);
		assertTrue(resultData.size()==3);
		System.out.println("result NGI data:"+resultData);
	}
	
	public void testInsertTrigger(){
		Date startDate=DateUtils.truncate(new Date(), java.util.Calendar.DAY_OF_MONTH);
		int rand=Math.abs(new Random(new Date().getTime()).nextInt());
		String metricName="metric_"+rand;
		String triggerType="trigger_"+(rand+1);
		String vin="vin_"+rand;
		
		//insert metric
		dataAccessor.addMetric(new MetricObject(rand,metricName,-1,"%",0,"example metric"));
		try{
			dataAccessor.getMetricId(metricName);
		}catch(DataAccessException e){
			assertTrue(false);
		}
		
		//insert trigger type
		dataAccessor.addMetric(new MetricObject(rand+1,triggerType,-1,"%",0,"example trigger"));
		try{
			dataAccessor.getMetricId(triggerType);
		}catch(DataAccessException e){
			assertTrue(false);
		}
		
		//insert vehicle
		dataAccessor.addVehicle(new Vehicle(rand,vin));
		try{
			dataAccessor.getIDByVin(vin);
		}catch(DataAccessException e){
			assertTrue(false);
		}
		
		//insert data
		Map<String,BigDecimal> triggerData=new HashMap<String,BigDecimal>();
		triggerData.put(metricName, new BigDecimal(1.0f));
		dataAccessor.insertTriggerData(vin, new Date(), triggerType, triggerData);
		
		
		List<NGITriggerData> resultData=new ArrayList<NGITriggerData>();
		dataAccessor.getTriggerDataSeries(vin, startDate, startDate, triggerType, resultData);
		assertTrue(resultData.size()==1);
		System.out.println("result trigger data:"+resultData);
	}
	
	
	public void testGetTripSummary(){
		Date startDate=DateUtils.truncate(new Date(), java.util.Calendar.DAY_OF_MONTH);
		int rand=Math.abs(new Random(new Date().getTime()).nextInt());
		String metricName="metric_"+rand;
		String vin="vin_"+rand;
		
		//insert metric
		dataAccessor.addMetric(new MetricObject(rand,metricName,-1,"%",0,"example metric"));
		try{
			dataAccessor.getMetricId(metricName);
		}catch(DataAccessException e){
			assertTrue(false);
		}
		
		//insert vehicle
		dataAccessor.addVehicle(new Vehicle(rand,vin));
		try{
			dataAccessor.getIDByVin(vin);
		}catch(DataAccessException e){
			assertTrue(false);
		}
		
		Map<Integer,BigDecimal> data=new HashMap<Integer,BigDecimal>();
		data.put(rand, new BigDecimal(1.0f));
		//insert summary data
		sessionHelper.insert("INSERT INTO ngi.trip_summary(vin_id,trip_id,trip_stime,trip_etime,values) VALUES(?,?,?,?,?)",
				rand,1,startDate,new Date(),data
				);
		
		List<TripSummaryObject> resultData=new ArrayList<TripSummaryObject>();
		dataAccessor.getTripSummary(vin, startDate, startDate, resultData);
		assertTrue(resultData.size()==1);
		System.out.println("result Trip data:"+resultData);
	}
	
	
	public void testDataTimeSummary(){
		Date startDate=DateUtils.truncate(new Date(), java.util.Calendar.DAY_OF_MONTH);
		Date startMonth=DateUtils.truncate(new Date(), java.util.Calendar.MONTH);
		Date startYear=DateUtils.truncate(new Date(), java.util.Calendar.YEAR);
		
		int rand=Math.abs(new Random(new Date().getTime()).nextInt());
		String metricName="metric_"+rand;
		String vin="vin_"+rand;
		
		//insert metric
		dataAccessor.addMetric(new MetricObject(rand,metricName,-1,"%",0,"example metric"));
		try{
			dataAccessor.getMetricId(metricName);
		}catch(DataAccessException e){
			assertTrue(false);
		}
		
		//insert vehicle
		dataAccessor.addVehicle(new Vehicle(rand,vin));
		try{
			dataAccessor.getIDByVin(vin);
		}catch(DataAccessException e){
			assertTrue(false);
		}
		
		Map<Integer,BigDecimal> data=new HashMap<Integer,BigDecimal>();
		data.put(rand, new BigDecimal(1.0f));
		//insert data summary
		sessionHelper.insert("INSERT INTO ngi.data_time_summary(vin_id,summary_type,summary_date,values) VALUES(?,?,?,?)",
				rand,0,startDate,data
				);
		sessionHelper.insert("INSERT INTO ngi.data_time_summary(vin_id,summary_type,summary_date,values) VALUES(?,?,?,?)",
				rand,1,startMonth,data
				);
		sessionHelper.insert("INSERT INTO ngi.data_time_summary(vin_id,summary_type,summary_date,values) VALUES(?,?,?,?)",
				rand,2,startYear,data
				);
		
		List<DateSummary> resultData=new ArrayList<DateSummary>();
		dataAccessor.getDateSummary(vin, startDate, startDate, NGIDataAccessor.DATA_SUMMARY_TYPE_DAY, new ArrayList<String>(), resultData);
		assertTrue(resultData.size()==1);
		System.out.println("data summary by date:"+resultData);
		
		resultData.clear();
		dataAccessor.getDateSummary(vin, startMonth, startMonth, NGIDataAccessor.DATA_SUMMARY_TYPE_MONTH, new ArrayList<String>(), resultData);
		assertTrue(resultData.size()==1);
		System.out.println("data summary by month:"+resultData);
		
		resultData.clear();
		dataAccessor.getDateSummary(vin, startYear, startYear, NGIDataAccessor.DATA_SUMMARY_TYPE_YEAR, new ArrayList<String>(), resultData);
		assertTrue(resultData.size()==1);
		System.out.println("data summary by year:"+resultData);
	}
	
	public void testGetCrossAnalysisResult(){
		Date startDate=DateUtils.truncate(new Date(), java.util.Calendar.DAY_OF_MONTH);		
		Date startMonth=DateUtils.truncate(new Date(), java.util.Calendar.MONTH);
		Date lastMonth=DateUtils.addMonths(startMonth, -1);
		
		Date startYear=DateUtils.truncate(new Date(), java.util.Calendar.YEAR);
		Date lastYear=DateUtils.addYears(startYear, -1);
		
		int rand=Math.abs(new Random(new Date().getTime()).nextInt());
		String vin="vin_"+rand;
		
		//insert vehicle
		dataAccessor.addVehicle(new Vehicle(rand,vin));
		try{
			dataAccessor.getIDByVin(vin);
		}catch(DataAccessException e){
			assertTrue(false);
		}
		
		//insert last year cross analysis data
		sessionHelper.insert("INSERT INTO ngi.data_cross_analysis(vin_id,summary_sdate,summary_type,cross_type,idx,value_x,value_y,samples) VALUES(?,?,?,?,?,?,?,?)",
				rand,lastYear,NGIDataAccessor.CROSS_ANALYSIS_DATA_TYPE_YEAR,rand,1,new BigDecimal(100),new BigDecimal(200),100
				);
		sessionHelper.insert("INSERT INTO ngi.data_cross_analysis(vin_id,summary_sdate,summary_type,cross_type,idx,value_x,value_y,samples) VALUES(?,?,?,?,?,?,?,?)",
				rand,lastYear,NGIDataAccessor.CROSS_ANALYSIS_DATA_TYPE_YEAR,rand,2,new BigDecimal(200),new BigDecimal(200),100
				);
		
		//insert last month cross analysis data
		sessionHelper.insert("INSERT INTO ngi.data_cross_analysis(vin_id,summary_sdate,summary_type,cross_type,idx,value_x,value_y,samples) VALUES(?,?,?,?,?,?,?,?)",
				rand,lastMonth,NGIDataAccessor.CROSS_ANALYSIS_DATA_TYPE_MONTH,rand,1,new BigDecimal(100),new BigDecimal(200),100
				);
		sessionHelper.insert("INSERT INTO ngi.data_cross_analysis(vin_id,summary_sdate,summary_type,cross_type,idx,value_x,value_y,samples) VALUES(?,?,?,?,?,?,?,?)",
				rand,lastMonth,NGIDataAccessor.CROSS_ANALYSIS_DATA_TYPE_MONTH,rand,2,new BigDecimal(200),new BigDecimal(200),100
				);
		
		//insert for today
		//insert last month cross analysis data
		sessionHelper.insert("INSERT INTO ngi.data_cross_analysis(vin_id,summary_sdate,summary_type,cross_type,idx,value_x,value_y,samples) VALUES(?,?,?,?,?,?,?,?)",
				rand,startDate,NGIDataAccessor.CROSS_ANALYSIS_DATA_TYPE_DAY,rand,1,new BigDecimal(100),new BigDecimal(200),100
				);
		sessionHelper.insert("INSERT INTO ngi.data_cross_analysis(vin_id,summary_sdate,summary_type,cross_type,idx,value_x,value_y,samples) VALUES(?,?,?,?,?,?,?,?)",
				rand,startDate,NGIDataAccessor.CROSS_ANALYSIS_DATA_TYPE_DAY,rand,2,new BigDecimal(200),new BigDecimal(200),100
				);
		
		Map<BigDecimal,BigDecimal> resultData=new HashMap<BigDecimal,BigDecimal>();
		dataAccessor.crossAnalysis(vin, lastYear, startDate, rand, resultData);
		assertTrue(resultData.size()==2);
		System.out.println("result Cross Analysis data:"+resultData);
	}
}
