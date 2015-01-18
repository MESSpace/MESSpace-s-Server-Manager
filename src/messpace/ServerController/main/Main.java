package messpace.ServerController.main;

import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		System.out.println();
		System.out.println("Welcome To MESSpace's Server Utility!");
		System.out.println("Please Enter A Valid Command: ");
		String commandInput = scan.nextLine();
		switch(commandInput) {
			case "Help":
				Commands.help();
				break;
			case "Setjar":
				Commands.setJar();
				break;
			case "Setmemory":
				Commands.setMemory();
				break;
			case "Start":
				Commands.generateCommand();
				break;
			case "Setgui":
				Commands.isGui();
				break;
			case "Exit":
				System.out.println("Closing Program!");
				break;
			default:
				System.out.println("Unknown Command...");
				Commands.rethread();
		}
	}
	
	public static void rethread() throws IOException {
		Commands.startServer();
	}
	
	public static void rebootServer() throws IOException {
		Commands.startServer();
	}

}
