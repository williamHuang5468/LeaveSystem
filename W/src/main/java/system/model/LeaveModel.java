package system.model;

import java.util.Date;

public class LeaveModel {
	String name;
	Date dateFrom;
	Date dateEnd;
	
	public LeaveModel(String name){
		this.name = name;
	}
	
	public LeaveModel(String name, Date timeFrom, Date timeEnd){
		this.name = name;
		this.dateFrom = timeFrom;
		this.dateEnd = timeEnd;
	}
	
	public String getName(){
		return name;
	}
	
	public String getDateEnd(){
		return dateEnd.toString();
	}
	
	public String getDateFrom(){
		return dateFrom.toString(); 
	}
}