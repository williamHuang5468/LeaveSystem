package system.controller.command;

import system.MongoDB;

public class ListCommand extends Command {
	private String name;

	public ListCommand(MongoDB mongo, String name) {
		super(mongo);
		this.name = name;
	}

	public void execute() {
		super.results = mongo.list(name);
		view.printList(super.results);
	}
}