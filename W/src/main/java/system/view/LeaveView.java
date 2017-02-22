package system.view;

import system.model.LeaveModel;

public class LeaveView {
	LeaveModel model;
	public LeaveView(LeaveModel model){
		this.model = model;
	}
	
	public static void print(){
		System.out.println(model.getName());
		System.out.println(model.getDateFrom());
		System.out.println(model.getDateEnd());
	}
}
