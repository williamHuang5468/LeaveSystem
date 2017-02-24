package system.controller.command;


public class ListCommand extends Command {
	private String name;

	public ListCommand(String name) {
		this.name = name;
	}

	public void execute() {
		super.results = mongo.list(name);
		view.printList(super.results);
	}
}