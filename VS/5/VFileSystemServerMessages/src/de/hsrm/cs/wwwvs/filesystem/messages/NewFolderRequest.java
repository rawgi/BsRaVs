package de.hsrm.cs.wwwvs.filesystem.messages;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class NewFolderRequest implements Payload{
	private int parent;
	private String name;

	public NewFolderRequest(){
		
	}
	
	public NewFolderRequest(int parent, String name){
		super();
		this.parent = parent;
		this.name = name;
	}

	public int getParent(){
		return parent;
	}

	public String getName(){
		return name;
	}

	@Override
	public void unmarshall(ByteBuffer data) throws MarshallingException{
		parent = data.getInt();
		byte length = data.get();
		byte[] name = new byte[length];
		data.get(name, 0, length);
		this.name = new String(name, Charset.forName("US-ASCII"));
	}

	@Override
	public byte[] marshall() throws MarshallingException{
		ByteBuffer result = ByteBuffer.allocate(10+name.length());
		result.putInt(6+name.length());
		result.put((byte)3);
		result.putInt(parent);
		result.put((byte)name.length());
		byte[] string = name.getBytes(Charset.forName("UTF-8"));
		result.put(string);
		return result.array();
	}
}