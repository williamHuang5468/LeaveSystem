package system.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import system.MongoDB;
import system.model.LeaveModel;

public class TakeLeaveController {
	String[] commands;
	MongoDB mongo;

	public TakeLeaveController(String[] command) {
		mongo = new MongoDB();
		this.commands = command;
	}

	public void execute() {
		String command = this.commands[0];
		if (command.equals("add")) {
			Date dateFrom= stringToDate(this.commands[2]);
			Date dateEnd = stringToDate(this.commands[3]);
			LeaveModel model = new LeaveModel(this.commands[1], dateFrom, dateEnd);
			mongo.add(model);
		} else if (command.equals("list")) {
			mongo.list(this.commands[1]);
		} else if (command.equals("listall")) {
			mongo.listAll();
		} else if (command.equals("delete")) {
			mongo.delete(this.commands[1]);
			mongo.listAll();
		}
	}
	
	public Date stringToDate(String dateString){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return dateFormat.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
