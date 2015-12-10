package de.hsrm.cs.wwwvs.filesystem.messages;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class FolderInfoResponse implements Payload{
	private int parent;
	private String name;
	private int[] files;
	private int[] folders;

	public FolderInfoResponse(){
		
	}
	
	public FolderInfoResponse(int parent, String name, int[] files, int[] folders){
		super();
		this.parent = parent;
		this.name = name;
		this.files = files;
		this.folders = folders;
	}

	public int getParent(){
		return parent;
	}

	public String getName(){
		return name;
	}

	public int[] getFiles(){
		return files;
	}

	public int[] getFolders(){
		return folders;
	}

	@Override
	public void unmarshall(ByteBuffer data) throws MarshallingException{
		parent = data.getInt();
		byte name_length = data.get();
		byte[] name = new byte[name_length];
		data.get(name, 0, name_length);
		this.name = new String(name, Charset.forName("US-ASCII"));
		
		int file_count = data.getInt();
		this.files = new int[file_count];
		for(int i = 0; i<file_count; i++){
			this.files[i] = data.getInt();
		}

		int folder_count = data.getInt();
		this.folders = new int[folder_count];
		for(int i = 0; i<folder_count; i++){
			this.folders[i] = data.getInt();
		}		
	}

	@Override
	public byte[] marshall() throws MarshallingException{
		ByteBuffer result = ByteBuffer.allocate(15+name.length()+(files.length+folders.length)*4);
		result.putInt(11+name.length()+(files.length+folders.length)*4);
		result.put((byte)10);
		result.putInt(parent);
		result.put((byte)name.length());
		
		byte[] string = name.getBytes(Charset.forName("UTF-8"));
		result.put(string);
		
		result.putInt(files.length);
		for(int i = 0; i<files.length; i++){
			result.putInt(files[i]);
		}
		
		result.putInt(folders.length);
		for(int i = 0; i<folders.length; i++){
			result.putInt(folders[i]);
		}
		
		return result.array();
	}
}