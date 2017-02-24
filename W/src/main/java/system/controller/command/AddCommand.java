package system.controller.command;

import system.model.LeaveModel;

public class AddCommand extends Command {
	LeaveModel model;

	public AddCommand(LeaveModel model) {
		this.model = model;
	}

	public void execute() {
		super.status = mongo.add(model);
	}
}
