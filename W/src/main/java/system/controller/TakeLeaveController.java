package system.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.mongodb.DBObject;

import system.MongoDB;
import system.model.LeaveModel;
import system.view.LeaveView;
import utility.Utility;

public class TakeLeaveController {
	String[] commands;
	MongoDB mongo;
	LeaveView leaveView;

	public TakeLeaveController(String[] command) {
		mongo = new MongoDB();
		this.commands = command;
		leaveView = new LeaveView();
	}

	public void execute() {
		String command = this.commands[0].toLowerCase();
		if (command.equals("add")) {
			if (this.commands.length == 4) {
				Date dateFrom = stringToDate(this.commands[2]);
				Date dateEnd = stringToDate(this.commands[3]);
				LeaveModel model = new LeaveModel(this.commands[1], dateFrom,
						dateEnd);
				mongo.add(model);
			} else {
				Utility.print("Wrong args");
			}
		} else if (command.equals("list")) {
			if (this.commands.length == 2) {
				List<DBObject> results = mongo.list(this.commands[1]);
				leaveView.printList(results);
			} else {
				Utility.print("Wrong args");
			}
		} else if (command.equals("listall")) {
			if (this.commands.length == 1) {
				List<DBObject> results = mongo.listAll();
				leaveView.printList(results);
			} else {
				Utility.print("Wrong args");
			}
		} else if (command.equals("delete")) {
			if (this.commands.length == 2) {
				mongo.delete(this.commands[1]);
				mongo.listAll();
			} else {
				Utility.print("Wrong args");
			}
		} else if (command.equals("update")) {
			if (this.commands.length == 4) {
				Date dateFrom = stringToDate(this.commands[2]);
				Date dateEnd = stringToDate(this.commands[3]);
				mongo.update(this.commands[1], dateFrom, dateEnd);
				mongo.listAll();
			} else {
				Utility.print("Wrong args");
			}
		} else {
			Utility.print("Doesn't support this command");
		}
	}

	public Date stringToDate(String dateString) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return dateFormat.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
