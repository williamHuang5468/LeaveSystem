package system;

import java.text.SimpleDateFormat;
import java.util.List;

import system.model.LeaveModel;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class MongoDB {
	public void add(LeaveModel model){
		try {
			MongoClient mongo = new MongoClient("localhost", 27017);
 
			DB db = mongo.getDB("takeleave");
			DBCollection tables = db.getCollection("leave");
			DBObject doc = toDBObject(model);
			tables.insert(doc);
			System.out.println("Insert success");
			
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}
	public void listAll() {
		try {
			MongoClient mongo = new MongoClient("localhost", 27017);
 
			DB db = mongo.getDB("takeleave");
			DBCollection tables = db.getCollection("leave");
			DBCursor cursor = tables.find();
			List<DBObject> results = cursor.toArray();
			for( DBObject item : results){
				System.out.println((String)item.get("name"));
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				System.out.println(item.get("dateFrom").toString());
				System.out.println(item.get("dateEnd").toString());
			}
			
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}
	

	public DBObject toDBObject(LeaveModel leave) {
	    return new BasicDBObject("name", leave.getName())
	                     .append("dateFrom", leave.getDateFrom())
	                     .append("dateEnd", leave.getDateEnd());
	}
}
