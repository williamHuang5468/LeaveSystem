package system;

public class TakeLeave {
	public static void main(String[] args) {
		//System.out.println(args[0]);
		// add
		String command = args[0];
		if(command.equals("add")){
			print(command);
		}
		else if(command == "list"){
			print(command);
		}
		else if(command == "listall"){
			print(command);
		}
		// list <name>
		// listall
		/*
		for (String s: args) {
			print(s);
        }
        */
	}
	
	public static void print(String input){
		System.out.println(input);
	}
}
