package de.hsrm.cs.wwwvs.filesystem.gateway;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import de.hsrm.cs.wwwvs.filesystem.Filesystem;
import de.hsrm.cs.wwwvs.filesystem.messages.DeleteFileRequest;
import de.hsrm.cs.wwwvs.filesystem.messages.DeleteFolderRequest;
import de.hsrm.cs.wwwvs.filesystem.messages.ErrorResponse;
import de.hsrm.cs.wwwvs.filesystem.messages.FileInfoRequest;
import de.hsrm.cs.wwwvs.filesystem.messages.FileInfoResponse;
import de.hsrm.cs.wwwvs.filesystem.messages.FileServerMessage;
import de.hsrm.cs.wwwvs.filesystem.messages.FolderInfoRequest;
import de.hsrm.cs.wwwvs.filesystem.messages.FolderInfoResponse;
import de.hsrm.cs.wwwvs.filesystem.messages.NewFileRequest;
import de.hsrm.cs.wwwvs.filesystem.messages.NewFileResponse;
import de.hsrm.cs.wwwvs.filesystem.messages.NewFolderRequest;
import de.hsrm.cs.wwwvs.filesystem.messages.NewFolderResponse;
import de.hsrm.cs.wwwvs.filesystem.messages.PayloadType;
import de.hsrm.cs.wwwvs.filesystem.messages.ReadFileRequest;
import de.hsrm.cs.wwwvs.filesystem.messages.ReadFileResponse;
import de.hsrm.cs.wwwvs.filesystem.messages.WriteFileRequest;
import de.hsrm.cs.wwwvs.filesystem.messages.marshalling.Marshaller.MarshallingException;

public class FilesystemImpl implements Filesystem{
	
	private Connection con;
	
	public FilesystemImpl(Connection con) {
		this.con = con;
	}

	@Override
	public int get_root_folder() throws IOException {
		return 0;
	}

	@Override
	public int new_file(String name, int parent) throws IOException {
		FileServerMessage request = new FileServerMessage(PayloadType.NEW_FILE_REQUEST, new NewFileRequest(parent, name));

		FileServerMessage result = new FileServerMessage();
		try {
			result = con.remoteOperation(request);
		} catch (MarshallingException e) {
			e.printStackTrace();
		}
		
		if(result.getPayloadType() == PayloadType.ERROR_RESPONSE){
			throwError((ErrorResponse)result.getPayload());
		}
			
		NewFileResponse response = (NewFileResponse)result.getPayload();
		
		return response.getHandle();
	}

	@Override
	public int new_folder(String name, int parent) throws IOException {
		FileServerMessage request = new FileServerMessage(PayloadType.NEW_FOLDER_REQUEST, new NewFolderRequest(parent, name));

		FileServerMessage result = new FileServerMessage();
		try {
			result = con.remoteOperation(request);
		} catch (MarshallingException e) {
			e.printStackTrace();
		}
		
		if(result.getPayloadType() == PayloadType.ERROR_RESPONSE){
			throwError((ErrorResponse)result.getPayload());
		}
		
		NewFolderResponse response = (NewFolderResponse)result.getPayload();
		
		return response.getHandle();
	}

	@Override
	public void delete_file(int file) throws IOException {
		FileServerMessage request = new FileServerMessage(PayloadType.DELETE_FILE_REQUEST, new DeleteFileRequest(file));

		FileServerMessage result = new FileServerMessage();
		try {
			result = con.remoteOperation(request);
		} catch (MarshallingException e) {
			e.printStackTrace();
		}
		
		if(result.getPayloadType() == PayloadType.ERROR_RESPONSE){
			throwError((ErrorResponse)result.getPayload());
		}
	}

	@Override
	public void delete_folder(int folder) throws IOException {
		FileServerMessage request = new FileServerMessage(PayloadType.DELETE_FOLDER_REQUEST, new DeleteFolderRequest(folder));

		FileServerMessage result = new FileServerMessage();
		try {
			result = con.remoteOperation(request);
		} catch (MarshallingException e) {
			e.printStackTrace();
		}
		
		if(result.getPayloadType() == PayloadType.ERROR_RESPONSE){
			throwError((ErrorResponse)result.getPayload());
		}
	}

	@Override
	public int get_file_parent(int file) throws IOException {
		FileServerMessage request = new FileServerMessage(PayloadType.FILE_INFO_REQUEST, new FileInfoRequest(file));

		FileServerMessage result = new FileServerMessage();
		try {
			result = con.remoteOperation(request);
		} catch (MarshallingException e) {
			e.printStackTrace();
		}
		
		if(result.getPayloadType() == PayloadType.ERROR_RESPONSE){
			throwError((ErrorResponse)result.getPayload());
		}
		
		FileInfoResponse response = (FileInfoResponse)result.getPayload();
		
		return response.getParent();
	}

	@Override
	public int get_file_size(int file) throws IOException {
		FileServerMessage request = new FileServerMessage(PayloadType.FILE_INFO_REQUEST, new FileInfoRequest(file));

		FileServerMessage result = new FileServerMessage();
		try {
			result = con.remoteOperation(request);
		} catch (MarshallingException e) {
			e.printStackTrace();
		}
		
		if(result.getPayloadType() == PayloadType.ERROR_RESPONSE){
			throwError((ErrorResponse)result.getPayload());
		}
		
		FileInfoResponse response = (FileInfoResponse)result.getPayload();
		
		return response.getSize();
	}

	@Override
	public String get_file_name(int file) throws IOException {
		FileServerMessage request = new FileServerMessage(PayloadType.FILE_INFO_REQUEST, new FileInfoRequest(file));

		FileServerMessage result = new FileServerMessage();
		try {
			result = con.remoteOperation(request);
		} catch (MarshallingException e) {
			e.printStackTrace();
		}
		
		if(result.getPayloadType() == PayloadType.ERROR_RESPONSE){
			throwError((ErrorResponse)result.getPayload());
		}
			
		FileInfoResponse response = (FileInfoResponse)result.getPayload();
		
		return response.getName();
	}

	@Override
	public int get_folder_parent(int folder) throws IOException {
		FileServerMessage request = new FileServerMessage(PayloadType.FOLDER_INFO_REQUEST, new FolderInfoRequest(folder));

		FileServerMessage result = new FileServerMessage();
		try {
			result = con.remoteOperation(request);
		} catch (MarshallingException e) {
			e.printStackTrace();
		}
		
		if(result.getPayloadType() == PayloadType.ERROR_RESPONSE){
			throwError((ErrorResponse)result.getPayload());
		}
		
		FolderInfoResponse response = (FolderInfoResponse)result.getPayload();
		
		return response.getParent();
	}

	@Override
	public String get_folder_name(int folder) throws IOException {
		FileServerMessage request = new FileServerMessage(PayloadType.FOLDER_INFO_REQUEST, new FolderInfoRequest(folder));

		FileServerMessage result = new FileServerMessage();
		try {
			result = con.remoteOperation(request);
		} catch (MarshallingException e) {
			e.printStackTrace();
		}
			
		if(result.getPayloadType() == PayloadType.ERROR_RESPONSE){
			throwError((ErrorResponse)result.getPayload());
		}
		
		FolderInfoResponse response = (FolderInfoResponse)result.getPayload();
		
		return response.getName();
	}

	@Override
	public int get_folder_file_count(int folder) throws IOException {
		FileServerMessage request = new FileServerMessage(PayloadType.FOLDER_INFO_REQUEST, new FolderInfoRequest(folder));

		FileServerMessage result = new FileServerMessage();
		try {
			result = con.remoteOperation(request);
		} catch (MarshallingException e) {
			e.printStackTrace();
		}
		
		if(result.getPayloadType() == PayloadType.ERROR_RESPONSE){
			throwError((ErrorResponse)result.getPayload());
		}
		
		FolderInfoResponse response = (FolderInfoResponse)result.getPayload();
		
		return response.getFiles().length;
	}

	@Override
	public int get_folder_folder_count(int folder) throws IOException {
		FileServerMessage request = new FileServerMessage(PayloadType.FOLDER_INFO_REQUEST, new FolderInfoRequest(folder));

		FileServerMessage result = new FileServerMessage();
		try {
			result = con.remoteOperation(request);
		} catch (MarshallingException e) {
			e.printStackTrace();
		}
		
		if(result.getPayloadType() == PayloadType.ERROR_RESPONSE){
			throwError((ErrorResponse)result.getPayload());
		}
		
		FolderInfoResponse response = (FolderInfoResponse)result.getPayload();
		return response.getFolders().length;
	}

	@Override
	public List<Integer> get_folder_files(int folder) throws IOException {
		FileServerMessage request = new FileServerMessage(PayloadType.FOLDER_INFO_REQUEST, new FolderInfoRequest(folder));

		FileServerMessage result = new FileServerMessage();
		try {
			result = con.remoteOperation(request);
		} catch (MarshallingException e) {
			e.printStackTrace();
		}
		
		if(result.getPayloadType() == PayloadType.ERROR_RESPONSE){
			throwError((ErrorResponse)result.getPayload());
		}
		
		FolderInfoResponse response = (FolderInfoResponse)result.getPayload();
		
		List<Integer> files = new LinkedList<Integer>();
		
		for(int i = 0; i<response.getFiles().length; i++){
			files.add(response.getFiles()[i]);
		}
		
		return files;
	}

	@Override
	public List<Integer> get_folder_folders(int folder) throws IOException {
		FileServerMessage request = new FileServerMessage(PayloadType.FOLDER_INFO_REQUEST, new FolderInfoRequest(folder));

		FileServerMessage result = new FileServerMessage();
		try {
			result = con.remoteOperation(request);
		} catch (MarshallingException e) {
			e.printStackTrace();
		}
		
		if(result.getPayloadType() == PayloadType.ERROR_RESPONSE){
			throwError((ErrorResponse)result.getPayload());
		}
		
		FolderInfoResponse response = (FolderInfoResponse)result.getPayload();
		
		List<Integer> folders = new LinkedList<Integer>();
		
		for(int i = 0; i<response.getFolders().length; i++){
			folders.add(response.getFolders()[i]);
		}
		return folders;
	}

	@Override
	public void write_file(int file, int offset, byte[] data) throws IOException {
		FileServerMessage request = new FileServerMessage(PayloadType.WRITE_FILE_REQUEST, new WriteFileRequest(file, offset, data));

		
		FileServerMessage result = new FileServerMessage();
		try {
			result = con.remoteOperation(request);
		} catch (MarshallingException e) {
			e.printStackTrace();
		}
		
		if(result.getPayloadType() == PayloadType.ERROR_RESPONSE){
			throwError((ErrorResponse)result.getPayload());
		}
	}

	@Override
	public byte[] read_file(int file, int offset, int length) throws IOException {
		FileServerMessage request = new FileServerMessage(PayloadType.READ_FILE_REQUEST, new ReadFileRequest(file, offset, length));

		FileServerMessage result = new FileServerMessage();
		try {
			result = con.remoteOperation(request);
		} catch (MarshallingException e) {
			e.printStackTrace();
		}
		
		if(result.getPayloadType() == PayloadType.ERROR_RESPONSE){
			throwError((ErrorResponse)result.getPayload());
		}
		
		ReadFileResponse response = (ReadFileResponse)result.getPayload();
		
		return response.getData();
	}
	
	private void throwError(ErrorResponse error) throws IOException{
		throw new IOException("Error-Code: "+error.getErrorCode()+"\n msg: "+error.getMsg());
	}
}