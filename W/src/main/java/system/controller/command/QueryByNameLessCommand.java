package system.controller.command;

import java.util.Date;

public class QueryByNameLessCommand extends Command {
	private String name;
	private String field;
	private Date dateFrom;

	public QueryByNameLessCommand(String name, String field, Date dateFrom) {
		this.name = name;
		this.field = field;
		this.dateFrom = dateFrom;
	}

	public void execute() {
		super.results = mongo.queryByNameLess(name, field, dateFrom);
		view.printAll(super.results);
	}
}
