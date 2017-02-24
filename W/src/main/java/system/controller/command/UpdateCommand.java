package system.controller.command;

import system.MongoDB;
import system.model.LeaveModel;

public class UpdateCommand extends Command {
	private LeaveModel model;
	public UpdateCommand(MongoDB mongo, LeaveModel model) {
		super(mongo);
		this.model = model;
	}

	public void execute() {
		status = mongo.update(model);
		view.printStatus(status);
	}
}