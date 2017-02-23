package system.view;

import java.util.List;

import com.mongodb.DBObject;

import system.model.LeaveModel;
import utility.Utility;

public class LeaveView {
	LeaveModel model;
	public LeaveView(LeaveModel model){
		this.model = model;
	}
	
	public LeaveView(){
	}
	
	public void printList(List<DBObject> results){
		for (DBObject item : results) {
			System.out.println(item.get("_id").toString());
			System.out.println(item.get("name"));
			System.out.println(item.get("dateFrom").toString());
			System.out.println(item.get("dateEnd").toString());
		}
	}

	public void print(){
		Utility.print(model.getName());
		Utility.print(model.getDateEndString());
		Utility.print(model.getDateFromString());
	}
}
