package system.view;

import system.model.LeaveModel;
import utility.Utility;

public class LeaveView {
	LeaveModel model;
	public LeaveView(LeaveModel model){
		this.model = model;
	}
	
	public void print(){
		Utility.print(model.getName());
		Utility.print(model.getDateEndString());
		Utility.print(model.getDateFromString());
	}
}
