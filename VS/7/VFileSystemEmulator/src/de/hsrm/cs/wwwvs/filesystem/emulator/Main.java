package de.hsrm.cs.wwwvs.filesystem.emulator;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import de.hsrm.cs.wwwvs.filesystem.Filesystem;
import de.hsrm.cs.wwwvs.filesystem.ServerImpl;

public class Main {

	/**
	 * @param args
	 * @throws AlreadyBoundException 
	 */
	public static void main(String[] args) throws AlreadyBoundException {

		Filesystem fs = (Filesystem) new Emulator();

		//bei args[1], damit ich in eclipse die run configs für server und client nicht immer ändern muss.
		int port = 0;
		try {
			port = Integer.parseInt(args[1]);
		} catch (NumberFormatException e) {
			System.err.println("Port ist keine Zahl");
			System.exit(-1);
		}
		
		// hier RMI und Thrift initialisieren, oder weitere Hilfsklassen
		try {
			ServerImpl server = new ServerImpl(fs);
			Registry registry = LocateRegistry.createRegistry(port);
			registry.bind("MyRMI", server);
			System.out.println("bound");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}