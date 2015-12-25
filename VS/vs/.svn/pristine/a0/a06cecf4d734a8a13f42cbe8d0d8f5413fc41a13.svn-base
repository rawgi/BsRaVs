package de.hsrm.cs.wwwvs.filesystem.messages;

import java.nio.ByteBuffer;

public class NewFileResponse implements Payload{
	private int handle;

	public NewFileResponse(){
		
	}
	
	public NewFileResponse(int handle){
		super();
		this.handle = handle;
	}

	public int getHandle(){
		return handle;
	}

	@Override
	public void unmarshall(ByteBuffer data) throws MarshallingException{
		this.handle = data.getInt();
	}

	@Override
	public byte[] marshall() throws MarshallingException{
		ByteBuffer result = ByteBuffer.allocate(9);
		result.putInt(5);
		result.put((byte)2);
		return result.array();
	}
}