package system.view;

import java.util.List;

import org.bson.Document;

import system.model.LeaveModel;
import utility.Utility;

public class LeaveView {
	LeaveModel model;
	public LeaveView(LeaveModel model){
		this.model = model;
	}
	
	public LeaveView(){
	}
	
	// TODO
	public void printList(List<Document> results){
		for (Document item : results) {
			System.out.println(item.get("_id").toString());
			System.out.println(item.get("name"));
			System.out.println(item.get("dateFrom").toString());
			System.out.println(item.get("dateEnd").toString());
		}
	}
	// TODO
	// Another print

	public void print(){
		Utility.print(model.getName());
		Utility.print(model.getDateEndString());
		Utility.print(model.getDateFromString());
	}
}
