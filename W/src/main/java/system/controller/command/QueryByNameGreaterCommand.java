package system.controller.command;

import java.util.Date;

public class QueryByNameGreaterCommand extends Command {
	private String name;
	private String field;
	private Date dateFrom;
	

	public QueryByNameGreaterCommand(String name, String field, Date dateFrom) {
		this.name = name;
		this.field = field;
		this.dateFrom = dateFrom;
	}

	public void execute() {
		super.results = mongo.queryByNameGreater(name, field, dateFrom);
		view.printAll(super.results);
	}
}
