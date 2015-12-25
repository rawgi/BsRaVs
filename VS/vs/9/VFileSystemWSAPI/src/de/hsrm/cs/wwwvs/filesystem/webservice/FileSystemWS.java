package de.hsrm.cs.wwwvs.filesystem.webservice;


public interface FileSystemWS {

	public int get_root_folder();
	public int new_file( String name, int parent) throws FilesystemFaultException;
	public int new_folder(String name, int parent) throws FilesystemFaultException;
	public void delete_file(int file) throws FilesystemFaultException;
	public void delete_folder(int folder) throws FilesystemFaultException;
	
	public FolderInfo get_folder_info(int folder) throws FilesystemFaultException;
	public FileInfo get_file_info(int file) throws FilesystemFaultException;
	public void write_file(int file, int offset, byte[] data) throws FilesystemFaultException;
	public byte[] read_file(int file, int offset, int length) throws FilesystemFaultException;
}
