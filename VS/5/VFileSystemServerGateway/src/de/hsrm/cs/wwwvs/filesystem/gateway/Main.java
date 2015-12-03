package de.hsrm.cs.wwwvs.filesystem.gateway;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import de.hsrm.cs.wwwvs.filesystem.Filesystem;
import de.hsrm.cs.wwwvs.filesystem.cli.ClientCLI;

public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		if (args.length != 2) {
			System.err.println("Falsche Anzahl Argumente: <host> <port>");
			System.exit(-1);
		}

		String hostname = args[0];
		int port = 0;

		try {
			port = Integer.parseInt(args[1]);
		} catch (NumberFormatException e) {
			System.err.println("Port ist keine Zahl");
			System.exit(-1);
		}

		Socket socket = new Socket(hostname, port);		
		
		Connection con = new Connection(socket);
		Filesystem fs = new FilesystemImpl(con);
		
		new ClientCLI(fs);
		
	}

}
