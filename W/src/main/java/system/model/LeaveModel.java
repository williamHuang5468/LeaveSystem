package system.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LeaveModel {
	String name;
	Date dateFrom;
	Date dateEnd;
	
	public LeaveModel(String name, String dateFrom, String dateEnd) throws ParseException{
		this.name = name;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date from = dateFormat.parse(dateFrom);
		Date end = dateFormat.parse(dateEnd);
		this.dateFrom = from;
		this.dateEnd = end;
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
	
	public void print(){
		System.out.println("---Model---");
		System.out.println(getName());
		System.out.println(getDateEnd());
		System.out.println(getDateFrom());
		System.out.println("---End---");
	}
}