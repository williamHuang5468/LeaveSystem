package system.controller;

import system.controller.command.Command;
import system.controller.command.CommandFactory;
import system.view.LeaveView;

public class TakeLeaveController {
	String[] commands;
	LeaveView leaveView;

	public TakeLeaveController(String[] command) {
		this.commands = command;
		leaveView = new LeaveView();
	}

	public void execute() {	
		CommandFactory factory = new CommandFactory();
		Command command = factory.createCommand(commands);
		command.execute();
	}
}
