package system;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import system.model.LeaveModel;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDB {
	MongoCollection<Document> leaveTable;
	MongoClient mongo;

	public MongoDB() {
		connect();
	}

	public void connect() {
		mongo = new MongoClient("localhost", 27017);
		MongoDatabase db = mongo.getDatabase("takeleave");
		leaveTable = db.getCollection("leave");
	}

	public boolean add(LeaveModel model) {
		try {
			connect();
			Document doc = toDocument(model);
			leaveTable.insertOne(doc);
		} catch (Exception e) {
			return false;
		} finally {
			mongo.close();
		}
		return true;
	}

	public List<Document> listAll() {
		connect();
		List<Document> result = new ArrayList<Document>();
		for (Document doc : leaveTable.find()) {
			result.add(doc);
		}
		mongo.close();

		return result;
	}

	public List<Document> list(String name) {
		connect();
		List<Document> results = new ArrayList<Document>();
		for (Document document : leaveTable.find(eq("name", name))) {
			results.add(document);
		}
		mongo.close();
		return results;
	}

	// TODO, return True or false
	public void delete(String leaveID) {
		connect();
		try {
			leaveTable.deleteOne(eq("_id", new ObjectId(leaveID)));
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		} finally {
			mongo.close();
		}
	}

	// TODO, Return ture
	public void update(String id, Date dateFrom, Date dateEnd) {
		connect();
		try {
			Document updateDocument = new Document().append(
					"$set",
					new BasicDBObject().append("dateFrom", dateFrom).append(
							"dateEnd", dateEnd));
			leaveTable.updateOne(eq("_id", new ObjectId(id)), updateDocument);
			System.out.println("update success");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		} finally {
			mongo.close();
		}
	}

	public Document toDocument(LeaveModel leave) {
		return new Document("name", leave.getName()).append("dateFrom",
				leave.getDateFrom()).append("dateEnd", leave.getDateEnd());
	}
}
