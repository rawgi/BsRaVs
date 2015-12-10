package de.hsrm.cs.wwwvs.filesystem.messages;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class FileInfoResponse implements Payload {
	private int parent;
	private int size;
	private String name;

	public FileInfoResponse() {
		
	}
	
	public FileInfoResponse(int parent, int size, String name) {
		super();
		this.parent = parent;
		this.size = size;
		this.name = name;
	}

	public int getParent() {
		return parent;
	}

	public int getSize() {
		return size;
	}

	public String getName() {
		return name;
	}

	@Override
	public void unmarshall(ByteBuffer data) throws MarshallingException {
		parent = data.getInt();
		size = data.getInt();
		byte name_length = data.get();
		byte[] name = new byte[name_length];
		data.get(name, 0, name_length);
		this.name = new String(name, Charset.forName("US-ASCII"));
		
	}

	@Override
	public byte[] marshall() throws MarshallingException {
		ByteBuffer result = ByteBuffer.allocate(9+name.length());
		result.putInt(5+name.length());
		result.put((byte)8);
		result.putInt(parent);
		result.putInt(size);
		result.put((byte)name.length());
		byte[] string = name.getBytes(Charset.forName("UTF-8"));
		result.put(string);
		return result.array();
	}
}