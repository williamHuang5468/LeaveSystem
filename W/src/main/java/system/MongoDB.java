package system;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Filters.lt;

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
		return queryTemplate();
	}

	public List<Document> list(String name) {
		Bson query = eq("name", name);
		return queryTemplate(query);
	}

	public boolean delete(String leaveID) {
		try {
			connect();
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
		try {
			connect();
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

	public List<Document> queryByNameLess(String name, String field,
			Date dateValue) {
		Bson query = and(eq("name", name), lt(field, dateValue));
		return queryTemplate(query);
	}

	public List<Document> queryByNameGreater(String name, String field,
			Date dateValue) {
		Bson query = and(eq("name", name), gt(field, dateValue));
		return queryTemplate(query);
	}

	public List<Document> queryByNameBetween(String name, Date dateFrom,
			Date dateEnd) {
		Bson query = and(eq("name", name), gt("dateFrom", dateFrom),
				lt("dateEnd", dateEnd));
		return queryTemplate(query);
	}

	private List<Document> queryTemplate() {
		connect();
		List<Document> result = new ArrayList<Document>();
		for (Document doc : leaveTable.find()) {
			result.add(doc);
		}
		mongo.close();
		return result;
	}

	private List<Document> queryTemplate(Bson query) {
		connect();
		List<Document> result = new ArrayList<Document>();
		for (Document doc : leaveTable.find(query)) {
			result.add(doc);
		}
		mongo.close();
		return result;
	}

	private Document toDocument(LeaveModel leave) {
		return new Document("name", leave.getName()).append("dateFrom",
				leave.getDateFrom()).append("dateEnd", leave.getDateEnd());
	}
}
