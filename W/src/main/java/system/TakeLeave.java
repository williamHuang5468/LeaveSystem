package system;

import system.model.LeaveModel;

public class TakeLeave {
	public static void main(String[] args) {
		// System.out.println(args[0]);
		// add
		String command = args[0];
		MongoDB mongo = new MongoDB();
		print(args[1]);
		print(args[2]);
		print(args[3]);
		if (command.equals("add")) {
			try{
				LeaveModel model = new LeaveModel(args[1], args[2], args[3]);
				print("---print---");
				model.print();
				mongo.add(model);
				mongo.listAll();
			}catch (Exception e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
			}
		} else if (command == "list") {
			print(command);
		} else if (command == "listall") {
			print(command);
		}
		// list <name>
		// listall
		/*
		 * for (String s: args) { print(s); }
		 */
	}

	public static void print(String input) {
		System.out.println(input);
	}
}
