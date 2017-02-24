package system;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Filters.lt;
import static com.mongodb.client.model.Filters.and;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import system.model.LeaveModel;

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
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
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
		for (Document doc : leaveTable.find(eq("name", name))) {
			results.add(doc);
		}
		mongo.close();
		return results;
	}

	public boolean delete(String leaveID) {
		connect();
		try {
			leaveTable.deleteOne(eq("_id", new ObjectId(leaveID)));
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return false;
		} finally {
			mongo.close();
		}
		return true;
	}

	public boolean update(LeaveModel model) {
		connect();
		try {
			Document updateDocument = new Document().append("$set",
					new Document().append("dateFrom", model.getDateFrom())
							.append("dateEnd", model.getDateEnd()));
			leaveTable.updateOne(eq("_id", model.getId()), updateDocument);
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return false;
		} finally {
			mongo.close();
		}
		return true;
	}
	
	public List<Document> queryByNameLess(String name, Date dateFrom) {
		connect();
		List<Document> result = new ArrayList<Document>();
		try {
			Bson filter = and(eq("name", name), lt("dateFrom", dateFrom));
			for (Document doc : leaveTable.find(filter)) {
				result.add(doc);
			}
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		} finally {
			mongo.close();
		}
		return result;
	}
	
	public List<Document> queryByNameGreater(String name, Date dateFrom) {
		connect();
		List<Document> result = new ArrayList<Document>();
		try {
			Bson filter = and(eq("name", name), gt("dateFrom", dateFrom));
			for (Document doc : leaveTable.find(filter)) {
				result.add(doc);
			}
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		} finally {
			mongo.close();
		}
		return result;
	}

	public List<Document> queryByNameBetween(String name, Date dateFrom,
			Date dateEnd) {
		connect();
		List<Document> result = new ArrayList<Document>();
		Bson filter = and(eq("name", name), gt("dateFrom", dateFrom),lt("dateEnd", dateEnd));
		try {
			for (Document doc : leaveTable.find(filter)) {
				result.add(doc);
			}
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		} finally {
			mongo.close();
		}
		return result;
	}
	
	public Document toDocument(LeaveModel leave) {
		return new Document("name", leave.getName()).append("dateFrom",
				leave.getDateFrom()).append("dateEnd", leave.getDateEnd());
	}
}
