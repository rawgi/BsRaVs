package de.hsrm.cs.wwwvs.filesystem.ws.server;

import java.io.IOException;
import java.util.List;

import de.hsrm.cs.wwwvs.filesystem.Filesystem;
import de.hsrm.cs.wwwvs.filesystem.webservice.FileInfo;
import de.hsrm.cs.wwwvs.filesystem.webservice.FileSystemWS;
import de.hsrm.cs.wwwvs.filesystem.webservice.FilesystemFaultException;
import de.hsrm.cs.wwwvs.filesystem.webservice.FolderInfo;

public class FileSystemWSImpl implements FileSystemWS{

	Filesystem fs;
	
	public FileSystemWSImpl(Filesystem fs){
		this.fs = fs;
	}
	
	
	@Override
	public int get_root_folder() {
		try {
			return fs.get_root_folder();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int new_file(String name, int parent) throws FilesystemFaultException {
		try {
			return fs.new_file(name, parent);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int new_folder(String name, int parent) throws FilesystemFaultException {
		try {
			return fs.new_folder(name, parent);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public void delete_file(int file) throws FilesystemFaultException {
		try {
			fs.delete_file(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete_folder(int folder) throws FilesystemFaultException {
		try {
			fs.delete_folder(folder);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public FolderInfo get_folder_info(int folder) throws FilesystemFaultException {
		FolderInfo fi = new FolderInfo();
		List<Integer> folders;
		List<Integer> files;
		try {
			fi.parent = fs.get_folder_parent(folder);
			fi.name = fs.get_folder_name(folder);
			fi.folderCount = fs.get_folder_folder_count(folder);
			fi.fileCount = fs.get_folder_file_count(folder);
		
			folders = fs.get_folder_folders(folder);
			files = fs.get_folder_files(folder);
			fi.folders = new int[folders.size()];
			for(int i = 0; i<folders.size(); i++){
				fi.folders[i] = folders.get(i);
			}
			fi.files = new int[files.size()];
			for(int i = 0; i<files.size(); i++){
				fi.files[i] = files.get(i);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return fi;
	}

	@Override
	public FileInfo get_file_info(int file) throws FilesystemFaultException {
		FileInfo fi = new FileInfo();
		try {
			fi.parent = fs.get_file_parent(file);
			fi.name = fs.get_file_name(file);
			fi.size = fs.get_file_size(file);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return fi;
	}

	@Override
	public void write_file(int file, int offset, byte[] data) throws FilesystemFaultException {
		try {
			fs.write_file(file, offset, data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public byte[] read_file(int file, int offset, int length) throws FilesystemFaultException {
		try {
			return fs.read_file(file, offset, length);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}