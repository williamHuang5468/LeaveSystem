package system.model;

import java.util.Date;

public class LeaveModel {
	String name;
	Date timeFrom;
	Date timeEnd;
	
	public LeaveModel(String name){
		this.name = name;
	}
	
	public LeaveModel(String name, Date timeFrom, Date timeEnd){
		this.name = name;
		this.timeFrom = timeFrom;
		this.timeEnd = timeEnd;
	}
	
	/*
	public void printOut(){
		System.out.println(name);
		System.out.println(timeFrom);
		System.out.println(timeEnd);
	}
	*/
}