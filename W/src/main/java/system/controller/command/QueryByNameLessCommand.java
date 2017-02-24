package system.controller.command;

import java.util.Date;

import system.MongoDB;

public class QueryByNameLessCommand extends Command {
	private String name;
	private Date dateFrom;

	public QueryByNameLessCommand(MongoDB mongo, String name, Date dateFrom) {
		super(mongo);
		this.name = name;
		this.dateFrom = dateFrom;
	}

	public void execute() {
		super.results = mongo.queryByNameLess(name, dateFrom);
		view.printList(super.results);
	}
}
