package system.controller.command;

import system.MongoDB;

public class ListAllCommand extends Command {
	public ListAllCommand(MongoDB mongo) {
		super(mongo);
	}

	public void execute() {
		super.results = mongo.listAll();
		super.view.printListAll(super.results);
	}
}