package system.controller.command;


public class NullCommand extends Command{
	
	public void execute() {
		System.err.println("Does not suport this command");
	}
}
