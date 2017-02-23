package system.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.bson.Document;

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

	// TODO 
	public void execute() {
		String command = this.commands[0].toLowerCase();
		// add william 2016-10-10 2016-10-12  5
		if (command.equals("add")) {
			if (this.commands.length == 4) {
				try {
					Date dateFrom = stringToDate(this.commands[2]);
					Date dateEnd = stringToDate(this.commands[3]);
					LeaveModel model = new LeaveModel(this.commands[1],
							dateFrom, dateEnd);
					mongo.add(model);
				} catch (ParseException e) {
					System.err.println("You has wrong format, like `2016-03-10`");
				}
			} else {
				Utility.print("Wrong args");
			}
		} else if (command.equals("list")) {
			if (this.commands.length == 2) {
				List<Document> results = mongo.list(this.commands[1]);
				leaveView.printList(results);
			} else {
				Utility.print("Wrong args");
			}
		} else if (command.equals("listall")) {
			if (this.commands.length == 1) {
				List<Document> results = mongo.listAll();
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
				try {
					Date dateFrom = stringToDate(this.commands[2]);
					Date dateEnd = stringToDate(this.commands[3]);
					mongo.update(this.commands[1], dateFrom, dateEnd);
					mongo.listAll();
				} catch (ParseException e) {
					System.err.println(e.getClass().getName() + ": "
							+ e.getMessage());
				}
			} else {
				Utility.print("Wrong args");
			}
		} else {
			Utility.print("Doesn't support this command");
		}
	}

	public Date stringToDate(String dateString) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.parse(dateString);
	}
}
