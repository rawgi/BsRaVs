package de.hsrm.cs.wwwvs.filesystem.thriftclient;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import de.hsrm.cs.wwwvs.filesystem.Filesystem;
import de.hsrm.cs.wwwvs.filesystem.cli.ClientCLI;
import de.hsrm.cs.wwwvs.filesystem.thriftclient.VFileServer.Client;

public class Main {

	public static void main(String[] args) {

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
		
		// TODO Über Thrift eine Verbindung zum Server aufbauen
		
		
		TTransport transport = new TSocket(hostname, port);
		transport.open();
		
		TProtocol protocol = new TBinaryProtocol(transport);
		Client client = new Client(protocol);
		Filesystem fs = new ThriftClientImpl(client);
		new ClientCLI(fs);
	}
}
