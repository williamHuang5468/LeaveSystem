package system.controller.command;

import java.util.List;

import org.bson.Document;

import system.MongoDB;
import system.view.LeaveView;

public abstract class Command {
	protected MongoDB mongo = new MongoDB();
	protected List<Document> results;
	protected boolean status;
	protected LeaveView view = new LeaveView();

	public Command() {
	}

	public void execute() {
	}
}
