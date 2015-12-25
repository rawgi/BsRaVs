package de.hsrm.cs.wwwvs.filesystem.rmiclient;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import de.hsrm.cs.wwwvs.filesystem.Filesystem;
import de.hsrm.cs.wwwvs.filesystem.cli.ClientCLI;

public class Main {

	public static void main(String[] args) throws RemoteException, NotBoundException {

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

		// TODO RMI-Stub von der Registry holen	
		Registry registry = LocateRegistry.getRegistry(hostname, port);
		Filesystem fs = (Filesystem) registry.lookup("MyRMI");
		System.out.println("1");
		new ClientCLI(fs);
	}
}
