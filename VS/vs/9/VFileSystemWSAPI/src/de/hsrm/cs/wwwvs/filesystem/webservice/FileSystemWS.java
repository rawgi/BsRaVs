package de.hsrm.cs.wwwvs.filesystem.webservice;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface FileSystemWS {

	public int get_root_folder();
	public int new_file(@WebParam(name="name") String name, @WebParam(name="parent") int parent) throws FilesystemFaultException;
	public int new_folder(@WebParam(name="name") String name, @WebParam(name="parent") int parent) throws FilesystemFaultException;
	public void delete_file(@WebParam(name="file") int file) throws FilesystemFaultException;
	public void delete_folder(@WebParam(name="folder") int folder) throws FilesystemFaultException;
	
	public FolderInfo get_folder_info(@WebParam(name="folder") int folder) throws FilesystemFaultException;
	public FileInfo get_file_info(@WebParam(name="file") int file) throws FilesystemFaultException;
	public void write_file(@WebParam(name="file") int file, @WebParam(name="offset") int offset, @WebParam(name="data") byte[] data) throws FilesystemFaultException;
	public byte[] read_file(@WebParam(name="file") int file, @WebParam(name="offset") int offset, @WebParam(name="length") int length) throws FilesystemFaultException;
}
