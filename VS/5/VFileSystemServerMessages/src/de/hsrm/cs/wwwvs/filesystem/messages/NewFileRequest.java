package de.hsrm.cs.wwwvs.filesystem.messages;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

import de.hsrm.cs.wwwvs.filesystem.messages.marshalling.Marshaller.MarshallingException;


public class NewFileRequest implements Payload {
	private int parent;
	private String name;

	public NewFileRequest(){
		
	}
	
	public NewFileRequest(int parent, String name) {
		super();
		this.parent = parent;
		this.name = name;
	}

	public int getParent() {
		return parent;
	}

	public String getName() {
		return name;
	}

	@Override
	public void unmarshall(ByteBuffer data) throws MarshallingException {
		parent = data.getInt();
		byte length = data.get();
		byte[] nameAsByte = new byte[length];
		data.get(nameAsByte, 0, length);
		name = new String(nameAsByte, Charset.forName("US_ASCII"));
	}

	@Override
	public byte[] marshall() throws MarshallingException {
		ByteBuffer result = ByteBuffer.allocate(10+name.length());
		result.putInt(6+name.length());
		result.put((byte)1);
		result.putInt(parent);
		result.put((byte)name.length());
		byte[] string = name.getBytes(Charset.forName("UTF-8"));
		result.put(string);
		return result.array();
	}
}