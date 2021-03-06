package de.hsrm.cs.wwwvs.filesystem.webservice.client;


import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import de.hsrm.cs.wwwvs.filesystem.Filesystem;
import de.hsrm.cs.wwwvs.filesystem.cli.ClientCLI;
import de.hsrm.cs.wwwvs.filesystem.webservice.FileSystemWS;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		if (args.length != 3) {
			System.err.println("Falsche Anzahl Argumente: <host> <port> <client1/client2>");
			System.exit(-1);
		}

		String hostname = args[0];
		int port = 0;
		String myClient = args[2];

		try {
			port = Integer.parseInt(args[1]);
		} catch (NumberFormatException e) {
			System.err.println("Port ist keine Zahl");
			System.exit(-1);
		}
	
		// Hier Webservice zum Server aufbauen
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(FileSystemWS.class);
		factory.setAddress("http://"+hostname+":"+port+"/filesystem");
		FileSystemWS fsci = (FileSystemWS) factory.create();
		
		Filesystem fs = new FilesystemClientImpl(fsci);
		new ClientCLI(fs);

	}

}
