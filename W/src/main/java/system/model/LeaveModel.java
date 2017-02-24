package system.model;

import java.util.Date;

import org.bson.types.ObjectId;

public class LeaveModel {
	ObjectId leaveId;
	String name;
	Date dateFrom;
	Date dateEnd;

	public LeaveModel(ObjectId id, Date dateFrom, Date dateEnd) {
		leaveId = id;
		this.dateFrom = dateFrom;
		this.dateEnd = dateEnd;
	}

	public LeaveModel(String name, Date dateFrom, Date dateEnd) {
		leaveId = new ObjectId();
		this.name = name;
		this.dateFrom = dateFrom;
		this.dateEnd = dateEnd;
	}

	public ObjectId getId() {
		return leaveId;
	}

	public String getName() {
		return name;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public String getDateEndString() {
		return dateEnd.toString();
	}

	public String getDateFromString() {
		return dateFrom.toString();
	}
}