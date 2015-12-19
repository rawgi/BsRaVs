package de.hsrm.cs.wwwvs.filesystem.emulator;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.annotation.processing.Processor;

import de.hsrm.cs.wwwvs.filesystem.Filesystem;

import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

public class Main {

	/**
	 * @param args
	 * @throws AlreadyBoundException
	 */
	public static void main(String[] args) throws AlreadyBoundException {

		//bei args[1], damit ich in eclipse die run configs für server und client nicht immer ändern muss.
		int port = 0;
		try {
			port = Integer.parseInt(args[1]);
		} catch (NumberFormatException e) {
			System.err.println("Port ist keine Zahl");
			System.exit(-1);
		}
		
		// hier RMI und Thrift initialisieren, oder weitere Hilfsklassen
		Filesystem fs = (Filesystem) new Emulator();
		try {
			Registry registry = LocateRegistry.createRegistry(port);
			registry.bind("MyRMI", fs);
			System.out.println("bound");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		VFileSystemThrift vfsThrift = new VFileSystemThrift(fs);
		Processor<Iface> processor = new Processor<Iface>(vfsThrift);
		
		try{
			TServerTransport transport = new TServerSocket(9090);
			TServer server = new TSimpleServer(new TServer.Args(transport).processor(processor));
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
	}
}