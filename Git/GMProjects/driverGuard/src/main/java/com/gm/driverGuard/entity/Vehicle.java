package com.gm.driverGuard.entity;

import java.io.Serializable;

import com.datastax.driver.core.Row;

public class Vehicle implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8266045623079454759L;
	private int id;
	private String vin;
	
	
	public static ObjectConverter<Vehicle> getConverter(){
		return new ObjectConverter<Vehicle>(){
			public Vehicle convert(Row row) {
				Vehicle vehicle=new Vehicle();
				vehicle.setVin(row.getString("vin"));
				vehicle.setId(row.getInt("vin_id"));
				return vehicle;
			}
		};
	}
	
	public Vehicle(){
		
	}
	
	public Vehicle(int id, String vin) {
		super();
		this.id = id;
		this.vin = vin;
	}



	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((vin == null) ? 0 : vin.hashCode());
		result = prime * result + id;
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
		Vehicle other = (Vehicle) obj;
		if (vin == null) {
			if (other.vin != null)
				return false;
		} else if (!vin.equals(other.vin))
			return false;
		if (id != other.id)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Vehicle [vin_id=" + id + ", vin=" + vin + "]";
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	
}
