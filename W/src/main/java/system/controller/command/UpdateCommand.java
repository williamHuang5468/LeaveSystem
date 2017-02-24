package system.controller.command;

import system.model.LeaveModel;

public class UpdateCommand extends Command {
	private LeaveModel model;
	public UpdateCommand(LeaveModel model) {
		this.model = model;
	}

	public void execute() {
		status = mongo.update(model);
		view.printStatus(status);
	}
}