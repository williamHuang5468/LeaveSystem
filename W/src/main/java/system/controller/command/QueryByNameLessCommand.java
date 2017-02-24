package system.controller.command;

import java.util.Date;

import system.MongoDB;

public class QueryByNameLessCommand extends Command {
	private String name;
	private String field;
	private Date dateFrom;

	public QueryByNameLessCommand(MongoDB mongo, String name, String field, Date dateFrom) {
		super(mongo);
		this.name = name;
		this.field = field;
		this.dateFrom = dateFrom;
	}

	public void execute() {
		super.results = mongo.queryByNameLess(name, field, dateFrom);
		view.printAll(super.results);
	}
}
