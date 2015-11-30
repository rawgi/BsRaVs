package de.hsrm.cs.wwwvs.filesystem.messages;

import java.nio.ByteBuffer;


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
		data.getInt();											//nameLength überspringen, um an den eigentlichen namen zu kommen
		name = data.asCharBuffer().toString();
	}

	@Override
	public byte[] marshall() throws MarshallingException {
		// TODO Auto-generated method stub
		return null;
	}

}
