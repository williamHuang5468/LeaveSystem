package system.controller.command;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bson.types.ObjectId;

import system.model.LeaveModel;

public class CommandFactory {

	public Command createCommand(String[] args) {
		String commandString = args[0].toLowerCase();
		Command command = new NullCommand();
		if (commandString.equals("add") && args.length == 4) {
			command = createAddCommand(args, command);
		} else if (commandString.equals("list") && args.length == 2) {
			command = new ListCommand(args[1]);
		} else if (commandString.equals("listall") && args.length == 1) {
			command = new ListAllCommand();
		} else if (commandString.equals("delete") && args.length == 2) {
			command = new DeleteCommand(args[1]);
		} else if (commandString.equals("update") && args.length == 4) {
			command = createUpdateCommand(args);
		} else if (commandString.equals("querybyname") && args.length == 3) {
			command = queryCommandOperation(args);
		}
		return command;
	}

	private Command queryCommandOperation(String[] args) {
		try {
			String condition = args[2];
			String name = args[1];
			return conditionOperation(condition, name);
		} catch (ParseException e) {
			System.err.println("You has wrong format, like `2016-03-10`");
		}
		return new NullCommand();
	}

	private Command createUpdateCommand(String[] args) {
		try {
			Date dateFrom = stringToDate(args[2]);
			Date dateEnd = stringToDate(args[3]);
			LeaveModel model = new LeaveModel(new ObjectId(args[1]), dateFrom,
					dateEnd);
			return new UpdateCommand(model);
		} catch (ParseException e) {
			System.err.println("You has wrong format, like `2016-03-10`");
		}
		return new NullCommand();
	}

	private Command createAddCommand(String[] args, Command command) {
		try {
			Date dateFrom = stringToDate(args[2]);
			Date dateEnd = stringToDate(args[3]);
			LeaveModel model = new LeaveModel(args[1], dateFrom, dateEnd);
			return new AddCommand(model);
		} catch (ParseException e) {
			System.err.println("You has wrong format, like `2016-03-10`");
		}
		return new NullCommand();
	}

	private Command conditionOperation(String condition, String name)
			throws ParseException {
		Command command = new NullCommand();
		boolean equal = condition.contains("=");
		boolean greater = condition.contains(">");
		boolean less = condition.contains("<");
		if (equal) {
			command = queryByNameEqualOperation(condition, name);
		} else if (greater) {
			command = queryByNameGreaterOperation(condition, name);
		} else if (less) {
			command = queryByNameLessOperation(condition, name);
		}
		return command;
	}

	private Command queryByNameEqualOperation(String arg, String name)
			throws ParseException {
		String[] values = arg.split("=");
		boolean isRightField = values[0].toLowerCase().equals("daterange");
		if (isRightField) {
			String[] conditions = values[1].split(",");
			Date dateFrom = stringToDate(conditions[0]);
			Date dateEnd = stringToDate(conditions[1]);
			return new QueryByNameBetweenCommand(name, dateFrom, dateEnd);
		} else {
			return new NullCommand();
		}
	}

	private Command queryByNameGreaterOperation(String arg, String name)
			throws ParseException {
		String[] values = arg.split(">");
		String field = values[0];
		boolean isRightField = field.toLowerCase().equals("datefrom")
				|| field.toLowerCase().equals("dateend");
		if (isRightField) {
			String condition = values[1];
			Date fieldValue = stringToDate(condition);
			return new QueryByNameGreaterCommand(name, field, fieldValue);
		} else {
			return new NullCommand();
		}
	}

	private Command queryByNameLessOperation(String arg, String name)
			throws ParseException {
		String[] values = arg.split("<");
		String field = values[0];
		boolean isRightField = field.toLowerCase().equals("datefrom")
				|| field.toLowerCase().equals("dateend");
		if (isRightField) {
			String condition = values[1];
			Date fieldValue = stringToDate(condition);
			return new QueryByNameLessCommand(name, field, fieldValue);
		} else {
			return new NullCommand();
		}
	}

	public Date stringToDate(String dateString) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.parse(dateString);
	}
}
