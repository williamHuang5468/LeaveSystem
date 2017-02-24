package system.controller.command;

import system.MongoDB;
import system.model.LeaveModel;



public class AddCommand extends Command{
	LeaveModel model;
	public AddCommand(MongoDB mongo, LeaveModel model){
		super(mongo);
		this.model = model;
	}
	
	public void execute() {
		super.status = mongo.add(model);
	}
}
