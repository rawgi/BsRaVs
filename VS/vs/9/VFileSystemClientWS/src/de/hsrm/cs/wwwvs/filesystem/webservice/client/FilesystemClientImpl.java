package de.hsrm.cs.wwwvs.filesystem.webservice.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.hsrm.cs.wwwvs.filesystem.Filesystem;
import de.hsrm.cs.wwwvs.filesystem.webservice.FileSystemWS;

public class FilesystemClientImpl implements Filesystem{

	FileSystemWS fs;
	
	public FilesystemClientImpl(FileSystemWS fsci) {
		fs = fsci;
	}

	@Override
	public int get_root_folder() throws IOException {
		return fs.get_root_folder();
	}

	@Override
	public int new_file(String name, int parent) throws IOException {
		return fs.new_file(name, parent);
	}

	@Override
	public int new_folder(String name, int parent) throws IOException {
		return fs.new_folder(name, parent);
	}

	@Override
	public void delete_file(int file) throws IOException {
		fs.delete_file(file);
	}

	@Override
	public void delete_folder(int folder) throws IOException {
		fs.delete_folder(folder);
	}

	@Override
	public int get_file_parent(int file) throws IOException {
		return fs.get_file_info(file).parent;
	}

	@Override
	public int get_file_size(int file) throws IOException {
		return fs.get_file_info(file).size;
	}

	@Override
	public String get_file_name(int file) throws IOException {
		return fs.get_file_info(file).name;
	}

	@Override
	public int get_folder_parent(int folder) throws IOException {
		return fs.get_folder_info(folder).parent;
	}

	@Override
	public String get_folder_name(int folder) throws IOException {
		return fs.get_folder_info(folder).name;
	}

	@Override
	public int get_folder_file_count(int folder) throws IOException {
		return fs.get_folder_info(folder).fileCount;
	}

	@Override
	public int get_folder_folder_count(int folder) throws IOException {
		return fs.get_folder_info(folder).folderCount;
	}

	@Override
	public List<Integer> get_folder_files(int folder) throws IOException {
		int[] files = fs.get_folder_info(folder).files;
		List<Integer> fileList = new ArrayList<Integer>();
		if(files != null){
			for(int i = 0; i<files.length; i++){
				fileList.add(files[i]);
			}
		}
		return fileList;
	}

	@Override
	public List<Integer> get_folder_folders(int folder) throws IOException {
		int[] folders = fs.get_folder_info(folder).folders;
		List<Integer> folderList = new ArrayList<Integer>();
		if(folders != null){
			for(int i = 0; i<folders.length; i++){
				folderList.add(folders[i]);
			}
		}
		return folderList;
	}

	@Override
	public void write_file(int file, int offset, byte[] data) throws IOException {
		fs.write_file(file, offset, data);
	}

	@Override
	public byte[] read_file(int file, int offset, int length) throws IOException {
		return fs.read_file(file, offset, length);
	}
}