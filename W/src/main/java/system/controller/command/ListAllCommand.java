package system.controller.command;


public class ListAllCommand extends Command {
	public void execute() {
		super.results = mongo.listAll();
		super.view.printListAll(super.results);
	}
}