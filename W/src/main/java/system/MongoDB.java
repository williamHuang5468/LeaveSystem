package system;

import java.text.SimpleDateFormat;
import java.util.List;

import org.bson.types.ObjectId;

import system.model.LeaveModel;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class MongoDB {
	DBCollection leaveTable;

	public MongoDB() {
		MongoClient mongo = new MongoClient("localhost", 27017);
		DB db = mongo.getDB("takeleave");
		leaveTable = db.getCollection("leave");
	}

	public void add(LeaveModel model) {
		try {
			DBObject doc = toDBObject(model);
			leaveTable.insert(doc);
			System.out.println("Insert success");

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	public void listAll() {
		try {
			DBCursor cursor = leaveTable.find();
			List<DBObject> results = cursor.toArray();
			for (DBObject item : results) {
				System.out.println(item.get("_id").toString());
				System.out.println((String) item.get("name"));
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				System.out.println(item.get("dateFrom").toString());
				System.out.println(item.get("dateEnd").toString());
			}
			System.out.println("List End");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	public void list(String name) {
		try {
			DBObject doc = new BasicDBObject("name", name);
			DBCursor cursor = leaveTable.find(doc);
			List<DBObject> results = cursor.toArray();
			for (DBObject item : results) {
				System.out.println(item.get("_id").toString());
				System.out.println((String) item.get("name"));
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				System.out.println(item.get("dateFrom").toString());
				System.out.println(item.get("dateEnd").toString());
			}
			System.out.println("List End");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	public void delete(String leaveID) {
		try {
			BasicDBObject document = new BasicDBObject();
			document.put("_id", new ObjectId(leaveID));
			leaveTable.remove(document);
			System.out.println("Delete success");

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	// TODO
	public void edit() {

	}

	public DBObject toDBObject(LeaveModel leave) {
		return new BasicDBObject("name", leave.getName()).append("dateFrom",
				leave.getDateFrom()).append("dateEnd", leave.getDateEnd());
	}
}
