package system.model;

import java.util.Date;

import org.bson.types.ObjectId;

public class LeaveModel {
	String name;
	Date dateFrom;
	Date dateEnd;
	
	// TODO Gernerate ObjectId(), add
	public LeaveModel(ObjectId id, String name, Date dateFrom, Date dateEnd){
		
	}
	public LeaveModel(String name, Date dateFrom, Date dateEnd){
		this.name = name;
		this.dateFrom = dateFrom;
		this.dateEnd = dateEnd;
	}
	
	public String getName(){
		return name;
	}
	
	public Date getDateEnd(){
		return dateEnd;
	}
	
	public Date getDateFrom(){
		return dateFrom; 
	}
	
	public String getDateEndString(){
		return dateEnd.toString();
	}
	
	public String getDateFromString(){
		return dateFrom.toString(); 
	}
}