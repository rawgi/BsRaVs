package de.hsrm.cs.wwwvs.filesystem;

import de.hsrm.cs.wwwvs.filesystem.Filesystem;

import java.rmi.*;
import java.rmi.server.*;

public class ServerImpl extends UnicastRemoteObject implements Server{
	
	Filesystem fs;
	
	public ServerImpl(Filesystem fs) throws RemoteException{
		this.fs = (Filesystem)fs;
	}

	@Override
	public Filesystem getFS() throws RemoteException {
		System.out.println("tadaa");
		return (Filesystem)fs;
	}

	@Override
	public String test() throws RemoteException {
		return "test";
	}
}
