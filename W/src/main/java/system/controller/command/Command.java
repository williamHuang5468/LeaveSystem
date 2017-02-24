package system.controller.command;

import java.util.List;

import org.bson.Document;

import system.MongoDB;
import system.view.LeaveView;

public abstract class Command {
	protected MongoDB mongo;
	protected List<Document> results;
	protected boolean status;
	protected LeaveView view = new LeaveView();

	public Command() {
	}

	public Command(MongoDB mongo) {
		this.mongo = mongo;
	}

	public void execute() {
	}
}
