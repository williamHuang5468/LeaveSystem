package system.controller.command;

import java.util.Date;

import system.MongoDB;

public class QueryByNameBetweenCommand extends Command {
	private String name;
	private Date dateFrom;
	private Date dateEnd;

	public QueryByNameBetweenCommand(MongoDB mongo, String name, Date dateFrom, Date dateEnd) {
		super(mongo);
		this.name = name;
		this.dateFrom = dateFrom;
		this.dateEnd = dateEnd;
	}

	public void execute() {
		super.results = mongo.queryByNameBetween(name, dateFrom, dateEnd);
		view.printAll(super.results);
	}
}
