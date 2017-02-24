package system.controller;

import system.controller.command.Command;
import system.controller.command.CommandFactory;

public class TakeLeaveController {
	String[] commands;

	public TakeLeaveController(String[] args) {
		commands = args;
	}

	public void execute() {	
		Command command = new CommandFactory().createCommand(commands);
		command.execute();
	}
	
	/*
	// TODO backup
	public void executeFat(){
		String commandString = commands[0].toLowerCase();
		Command command = new NullCommand();
		CommandFactory factory = new CommandFactory();
		if (commandString.equals("add") && commands.length == 4) {
			command = factory.createAddCommand(commands, command);
		} else if (commandString.equals("list") && commands.length == 2) {
			command = new ListCommand(commands[1]);
		} else if (commandString.equals("listall") && commands.length == 1) {
			command = new ListAllCommand();
		} else if (commandString.equals("delete") && commands.length == 2) {
			command = new DeleteCommand(commands[1]);
		} else if (commandString.equals("update") && commands.length == 4) {
			command = factory.createUpdateCommand(commands);
		} else if (commandString.equals("querybyname") && commands.length == 3) {
			command = factory.queryCommandOperation(commands);
		}
		command.execute();
	}
	*/
}
