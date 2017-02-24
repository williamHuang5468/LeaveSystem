package system.controller.command;

import system.MongoDB;

public class DeleteCommand extends Command {
	private String leaveId;
	
	public DeleteCommand(MongoDB mongo, String id) {
		super(mongo);
		leaveId = id;
	}

	public void execute() {
		super.status = mongo.delete(leaveId);
		view.printStatus(super.status);
	}
}