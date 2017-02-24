package system.controller.command;


public class DeleteCommand extends Command {
	private String leaveId;
	
	public DeleteCommand(String id) {
		leaveId = id;
	}

	public void execute() {
		super.status = mongo.delete(leaveId);
		view.printStatus(super.status);
	}
}