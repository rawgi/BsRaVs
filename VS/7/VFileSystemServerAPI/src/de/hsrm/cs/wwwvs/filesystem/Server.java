package de.hsrm.cs.wwwvs.filesystem;

import java.rmi.*;

public interface Server extends Remote{
	public Filesystem getFS() throws java.rmi.RemoteException;
	public String test() throws java.rmi.RemoteException;
}
