package com.gm.driverGuard.helper;

import java.util.ArrayList;
import java.util.List;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Cluster.Builder;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.policies.ConstantReconnectionPolicy;
import com.datastax.driver.core.policies.DowngradingConsistencyRetryPolicy;
import com.gm.driverGuard.entity.ObjectConverter;

/**
 * helper class to create/close the Cassandra session
 * @author DZ5YVJ
 *
 */
public class CassandraSessionHelper {
	
	private List<String> nodeServers;
	private Long retryInterval=100L;
	
	private Cluster cluster;
	private Session session;
	
	public CassandraSessionHelper(){
	
	}
	
	private void buildCluster(){
		Builder builder = Cluster.builder();
		for(String nodeServer:nodeServers){
			builder.addContactPoint(nodeServer);
		}
	    cluster=builder.withRetryPolicy(DowngradingConsistencyRetryPolicy.INSTANCE)
	    	.withReconnectionPolicy(new ConstantReconnectionPolicy(retryInterval))
	    	.build();
	}
	
	/**
	 * open the session or just get the session if it's already opened
	 * @return
	 */
	public synchronized Session getSession(){
		if(session!=null) return session;
		
		if(cluster==null) buildCluster();
		
		session=cluster.connect();
		return session;
	}
	
	/**
	 * close the session
	 */
	public void close(){
		if(session!=null) session.close();
		
		session=null;
	}

	public List<String> getNodeServers() {
		return nodeServers;
	}

	public void setNodeServers(List<String> nodeServers) {
		this.nodeServers = nodeServers;
	}

	public Long getRetryInterval() {
		return retryInterval;
	}

	public void setRetryInterval(Long retryInterval) {
		this.retryInterval = retryInterval;
	}
	
	public Object getOne(String sql,ObjectConverter<?> converter, Object... values){
		Session session=getSession();
		PreparedStatement stmt=session.prepare(sql);
		ResultSet rs=session.execute(stmt.bind(values));
		Object obj=null;
		for(Row row:rs){
			obj=converter.convert(row);
		}
		return obj;
	}
	
	@SuppressWarnings("unchecked")
	public List<?> getAll(String sql,ObjectConverter<?> converter, Object... values){
		Session session=getSession();
		PreparedStatement stmt=session.prepare(sql);
		ResultSet rs=session.execute(stmt.bind(values));
		
		@SuppressWarnings("rawtypes")
		List results=new ArrayList();
		for(Row row:rs){
			results.add(converter.convert(row));
		}
		return results;
	}
	
	public void insert(String sql,Object... values){
		Session session=getSession();
		PreparedStatement stmt=session.prepare(sql);
		session.execute(stmt.bind(values));
	}
	
	
	
}
