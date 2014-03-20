package com.gm;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.gm.driverGuard.helper.CassandraSessionHelper;

public class SimpleClient {
	   private Cluster cluster;

	   public void connect(String node) {
	      cluster = Cluster.builder()
	            .addContactPoint(node).build();
	      Metadata metadata = cluster.getMetadata();
	      System.out.printf("Connected to cluster: %s\n", 
	            metadata.getClusterName());
	      for ( Host host : metadata.getAllHosts() ) {
	         System.out.printf("Datatacenter: %s; Host: %s; Rack: %s\n",
	               host.getDatacenter(), host.getAddress(), host.getRack());
	      }
	   }

	   public void close() {
	      cluster.close();
	   }
	   
	   public void getData(){
		   String sql="select * from ngi.data";
		   Session session=cluster.connect();
		   ResultSet rs=session.execute(sql);
		   for(Row row:rs){
			   System.out.println("get row:"+row);
		   }
	   }

	   public static void main(String[] args) {
		   ApplicationContext context=new ClassPathXmlApplicationContext(new String[]{
				"spring/applicationContext.xml"   
		   });
		  CassandraSessionHelper sessionHelper=(CassandraSessionHelper)context.getBean("cassandraSessionHelper");
	      Session session=sessionHelper.getSession();
	      
	      ResultSet rs=session.execute("select * from ngi.data");
		   for(Row row:rs){
			   System.out.println("get row:"+row);
		   }
	      
	      session.close();
	      ((ClassPathXmlApplicationContext)context).registerShutdownHook();
	      ((ClassPathXmlApplicationContext)context).close();
	   }
	}
