package system.controller;

import system.controller.command.Command;
import system.controller.command.CommandFactory;

public class TakeLeaveController {
	String[] commands;

	public TakeLeaveController(String[] args) {
		commands = args;
	}

	public void execute() {	
		String commandString = commands[0].toLowerCase();
		CommandFactory factory = new CommandFactory();
		Command command = factory.createNullCommand();
		if (commandString.equals("add") && commands.length == 4) {
			command = factory.createAddCommand(commands, command);
		} else if (commandString.equals("list") && commands.length == 2) {
			command = factory.createListCommand(commands[1]);
		} else if (commandString.equals("listall") && commands.length == 1) {
			command = factory.createListAllCommand();
		} else if (commandString.equals("delete") && commands.length == 2) {
			command = factory.createDeleteCommand(commands[1]);
		} else if (commandString.equals("update") && commands.length == 4) {
			command = factory.createUpdateCommand(commands);
		} else if (commandString.equals("querybyname") && commands.length == 3) {
			command = factory.queryCommandOperation(commands);
		}
		command.execute();
	}
}
