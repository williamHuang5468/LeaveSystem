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
				LeaveModel model = new LeaveModel(new ObjectId(args[1]),
						dateFrom, dateEnd);
				command = new UpdateCommand(mongo, model);
			} catch (ParseException e) {
				System.err.println("You has wrong format, like `2016-03-10`");
			}
		} else if (commandString.equals("querybyname") && args.length == 3) {
			try {
				// String arg = "daterange=2016-01-01,2016-05-05";
				String condition = args[2];
				String name = args[1];
				command = conditionOperation(condition, name);
			} catch (ParseException e) {
				System.err.println("You has wrong format, like `2016-03-10`");
			}
		} else {
			command = new NullCommand();
		}
		return command;
	}

	private Command conditionOperation(String condition, String name)
			throws ParseException {
		Command command;
		boolean equal = condition.contains("=");
		boolean greater = condition.contains(">");
		boolean less = condition.contains("<");
		if (equal) {
			command = queryByNameEqualOperation(condition, name);
		} else if (greater) {
			command = queryByNameGreaterOperation(condition, name);
		} else if (less) {
			command = queryByNameLessOperation(condition, name);
		} else {
			command = new NullCommand();
		}
		return command;
	}

	private Command queryByNameEqualOperation(String arg, String name)
			throws ParseException {
		String[] values = arg.split("=");
		boolean isRightField = values[0].equals("daterange");
		if (isRightField) {
			String[] conditions = values[1].split(",");
			Date dateFrom = stringToDate(conditions[0]);
			Date dateEnd = stringToDate(conditions[1]);
			return new QueryByNameBetweenCommand(mongo, name, dateFrom, dateEnd);
		} else {
			return new NullCommand();
		}
	}

	private Command queryByNameGreaterOperation(String arg, String name)
			throws ParseException {
		String[] values = arg.split(">");
		String field = values[0];
		boolean isRightField = field.equals("dateFrom")
				|| field.equals("dateEnd");
		if (isRightField) {
			String condition = values[1];
			Date fieldValue = stringToDate(condition);
			return new QueryByNameGreaterCommand(mongo, name, field, fieldValue);
		} else {
			return new NullCommand();
		}
	}

	private Command queryByNameLessOperation(String arg, String name)
			throws ParseException {
		String[] values = arg.split("<");
		String field = values[0];
		boolean isRightField = field.equals("dateFrom")
				|| field.equals("dateEnd");
		if (isRightField) {
			String condition = values[1];
			Date fieldValue = stringToDate(condition);
			return new QueryByNameLessCommand(mongo, name, field, fieldValue);
		} else {
			return new NullCommand();
		}
	}

	public Date stringToDate(String dateString) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.parse(dateString);
	}
}
