package system.view;

import java.util.List;

import org.bson.Document;

public class LeaveView {
	
	public LeaveView(){
	}
	
	public void printList(List<Document> results){
		for (Document item : results) {
			System.out.println(item.get("_id").toString());
			System.out.println(item.get("dateFrom").toString());
			System.out.println(item.get("dateEnd").toString());
		}
	}
	
	public void printListAll(List<Document> results){
		for (Document item : results) {
			System.out.println(item.get("name"));
			System.out.println(item.get("dateFrom").toString());
			System.out.println(item.get("dateEnd").toString());
		}
	}

	public void printStatus(boolean status) {
		System.out.println("status : " + status);
	}
}
