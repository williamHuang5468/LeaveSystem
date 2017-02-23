package system;

import java.text.ParseException;

import system.model.LeaveModel;

public class TakeLeave {
	public static void main(String[] args) throws ParseException {
		// add
		String command = args[0];
		MongoDB mongo = new MongoDB();
		if (command.equals("add")) {
			LeaveModel model = new LeaveModel(args[1], args[2], args[3]);
			mongo.add(model);
		} else if (command.equals("list")) {
			mongo.list(args[1]);
		} else if (command.equals("listall")) {
			mongo.listAll();
		} else if (command.equals("delete")) {
			mongo.delete(args[1]);
			mongo.listAll();
		}
	}

	public static void print(String input) {
		System.out.println(input);
	}
}
