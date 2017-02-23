package system;

import java.text.ParseException;

import system.controller.TakeLeaveController;

public class TakeLeave {
	public static void main(String[] args) throws ParseException {
		TakeLeaveController controller = new TakeLeaveController(args);
		controller.execute();
	}
}
