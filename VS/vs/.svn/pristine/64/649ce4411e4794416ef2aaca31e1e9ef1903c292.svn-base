package de.hsrm.cs.wwwvs.filesystem.ws.server;

import de.hsrm.cs.wwwvs.filesystem.emulator.Emulator;
import de.hsrm.cs.wwwvs.filesystem.webservice.FileSystemWS;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		if (args.length != 1) {
			System.err.println("Falsche Anzahl Argumente: <port>");
			System.exit(-1);
		}

		int port = 0;

		try {
			port = Integer.parseInt(args[0]);
		} catch (NumberFormatException e) {
			System.err.println("Port ist keine Zahl");
			System.exit(-1);
		}

		// Hier Emulator aufsetzen und als Webservice anbieten
		
	}

}
