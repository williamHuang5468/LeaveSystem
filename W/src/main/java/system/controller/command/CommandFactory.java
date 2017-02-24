package system.controller.command;

import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.bson.types.ObjectId;

import system.MongoDB;
import system.model.LeaveModel;

public class CommandFactory {
	MongoDB mongo;

	public CommandFactory() {
		this.mongo = new MongoDB();
	}

	public Command createCommand(String[] args) {
		String commandString = args[0].toLowerCase();
		Command command = null;
		if (commandString.equals("add") && args.length == 4) {
			try {
				Date dateFrom = stringToDate(args[2]);
				Date dateEnd = stringToDate(args[3]);
				LeaveModel model = new LeaveModel(args[1], dateFrom, dateEnd);
				command = new AddCommand(mongo, model);
			} catch (ParseException e) {
				System.err.println("You has wrong format, like `2016-03-10`");
			}
		} else if (commandString.equals("list") && args.length == 2) {
			command = new ListCommand(mongo, args[1]);
		} else if (commandString.equals("listall") && args.length == 1) {
			command = new ListAllCommand(mongo);
		} else if (commandString.equals("delete") && args.length == 2) {
			command = new DeleteCommand(mongo, args[1]);
		} else if (commandString.equals("update") && args.length == 4) {
			try {
				Date dateFrom = stringToDate(args[2]);
				Date dateEnd = stringToDate(args[3]);
				LeaveModel model = new LeaveModel(new ObjectId(args[1]), dateFrom, dateEnd);
				command = new UpdateCommand(mongo, model);				
			} catch (ParseException e) {
				System.err.println("You has wrong format, like `2016-03-10`");
			}
		} else if (commandString.equals("querybyname") && args.length == 3){
			try {
				String str = "2016-01-01";
				String str2 = "2016-02-12";
				String name = "john";
				String condition = ">"; // < , >
				Date dateFrom = stringToDate(str);
				Date dateEnd = stringToDate(str2);
				//if(){
					command = new QueryByNameLessCommand(mongo, name, dateFrom);
				/*else if(){
					command = new QueryByNameGreaterCommand(mongo, name, dateFrom);
				} else if(){
					command = new QueryByNameBetweenCommand(mongo, name, dateFrom, dateEnd);
				} else{
					command = new NullCommand();
				}*/
			} catch (ParseException e) {
				System.err.println("You has wrong format, like `2016-03-10`");
			}
		} else {
			command = new NullCommand();
		}
		return command;
	}

	public Date stringToDate(String dateString) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.parse(dateString);
	}
}
