package messpace.ServerController.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Commands {
	
	public static String jarLocation = "minecraft_server.jar";
	public static String memoryAmount = "2048" + "m";
	public static boolean isGuiOn = true;
	public static String startupCommand;
	
	public static void rethread() throws IOException {
		Main.main(null);
	}
	
	public static void help() throws IOException {
		System.out.println();
		System.out.println("Available Commands Are: ");
		System.out.println("Setjar - Sets Server Jar Location");
		System.out.println("Setmemory - Sets The Amount Of Memory");
		System.out.println("Setgui - Sets whether the server gui will start up on server start");
		System.out.println("Help - Displays This Text");
		System.out.println("Start - Starts The Server");
		try {
		    Thread.sleep(3000);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		Main.main(null);
	}
	
	public static void setJar() throws IOException{
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		System.out.println();
		System.out.println("Enter Jar Location: ");
		jarLocation = scan.nextLine();
		System.out.println("Jar Location Set To: " + jarLocation);
		try {
			Thread.sleep(3000);
		} catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
		Main.main(null);
	}
	
	public static void setMemory() throws IOException{
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		System.out.println();
		System.out.println("Would you like to set memory in");
		System.out.println("Megabytes Or Gigabytes [m/g]?");
		String gorm = scan.nextLine();
		switch(gorm) {
			case "m":
				System.out.println();
				System.out.println("Type The Amount Of Memory In Megabytes: ");
				memoryAmount = scan.nextLine() + "m";
				System.out.println("The Memory Amount Is Now Set To: " + memoryAmount);
				break;
			case "g":
				System.out.println();
				System.out.println("Type The Amount Of Memory In Gigabytes: ");
				memoryAmount = scan.nextLine() + "g";
				System.out.println("The Memory Amount It Now Set To: " + memoryAmount);
				break;
		}
		Main.main(null);
	}
	
	public static void isGui() throws IOException{
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		System.out.println();
		System.out.println("Would You Like To Run The Minecraft Server GUI [y/n]?");
		String yorno = scan.nextLine();
		switch(yorno) {
			case "y":
				isGuiOn = true;
				System.out.println("The Minecraft Server GUI Will Show Up When The Start Command Is Run!");
				break;
			case "n":
				isGuiOn = false;
				System.out.println("The Minecraft Server GUI Will Not Show Up When The Start Command Is Run!");
				break;
		}
		Main.main(null);
	}
	
	public static void generateCommand() throws IOException{
		System.out.println();
		System.out.println("Generating Server Startup Command Based On Current Settings...");
		if(isGuiOn == true) {
			startupCommand = "java -Xmx" + memoryAmount + " -Xms" + memoryAmount + " -jar " + jarLocation;
			System.out.println("Command Generated!");
		}else{
			startupCommand = "java -Xmx" + memoryAmount + " -Xms" + memoryAmount + " -jar " + jarLocation + " nogui";
			System.out.println("Command Generated!");
		}
		System.out.println();
		System.out.println("Starting Minecraft Server...");
		Main.rethread();
	}
	
	public static void startServer() throws IOException{
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		System.out.println();
		System.out.println("Running Minecraft Server At Location: " + jarLocation + "...");
		System.out.println("Warning! Depending On Your Minecraft Configurations, It May Take Time To Load!");
		Process p = Runtime.getRuntime().exec(startupCommand);
		OutputStream os = p.getOutputStream();
		InputStream is = p.getInputStream();
		BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(os));
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String currentLine = br.readLine();
		boolean isDone = true;
		System.out.println();
		System.out.println("Now That The Server Is Online, You Can Carry Out The Server Related Commands!");
		while(isDone == true) {
			System.out.println();
			System.out.println("Enter A Server Related Command: ");
			String serverRelatedCommand = scan.nextLine();
			switch(serverRelatedCommand) {
				case "help":
					System.out.println("Available Commands For The Server Are: ");
					System.out.println("save - Saves The Server");
					System.out.println("stop - Stops The Server");
					System.out.println("op - OPs the player of your choice");
					System.out.println("message - Sends a message in the chat");
					System.out.println("command - Executes an external command");
					try {
						Thread.sleep(3000);
					} catch(InterruptedException ex) {
						Thread.currentThread().interrupt();
					}
					break;
				case "save":
					System.out.println("Saving Server...");
					bf.write("say Server auto-save starting! Server going readonly...");
					bf.newLine();
					bf.flush();
					bf.write("save-off");
					bf.newLine();
					bf.flush();
					bf.write("save-all");
					bf.newLine();
					bf.flush();
					try {
						Thread.sleep(10000);
					} catch(InterruptedException ex) {
						Thread.currentThread().interrupt();
					}
					bf.write("save-on");
					bf.newLine();
					bf.flush();
					bf.write("say Server auto-save ending! Server going read-write...");
					bf.newLine();
					bf.flush();
					System.out.println("Server Saved.");
					break;
				case "stop":
					System.out.println("Server Shutdown Sequence Initiated...");
					bf.write("say Server shutting down in 10 seconds!. Saving world...");
					bf.newLine();
					bf.flush();
					bf.write("save-all");
					bf.newLine();
					bf.flush();
					try {
						Thread.sleep(10000);
					} catch(InterruptedException ex) {
						Thread.currentThread().interrupt();
					}
					bf.write("stop");
					bf.newLine();
					bf.flush();
					isDone = false;
					System.out.println("Server Has Been Told To Shut Down.");
					break;
				case "op":
					System.out.println("Who Should Be Opped?");
					String whoOp = scan.nextLine();
					System.out.println("Opping " + whoOp);
					bf.write("op " + whoOp);
					bf.newLine();
					bf.flush();
					System.out.println(whoOp + " Was Opped!");
					break;
				case "message":
					System.out.println("What Is Your Message?");
					String message = scan.nextLine();
					System.out.println("Sending Message: " + message + " In Chat...");
					bf.write("say " + message);
					bf.newLine();
					bf.flush();
					System.out.println("Message Sent!");
					break;
				case "command":
					System.out.println("Enter Custom Command: ");
					String command = scan.nextLine();
					System.out.println("Executing " + command);
					bf.write(command);
					bf.newLine();
					bf.flush();
					System.out.println("Command Executed!");
					break;
			}
			
		}
		System.out.println();
		System.out.println("The Server Is No Longer Bound As Stream, Reloading Main Menu...");
		try {
			Thread.sleep(5000);
		} catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
		System.out.println("Programs Shut Down, Load Complete!");
		Main.main(null);
		
	}
	
	

}
