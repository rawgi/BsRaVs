package de.hsrm.cs.wwwvs.filesystem.messages;

import java.nio.ByteBuffer;

public class DeleteFileRequest implements Payload{
	private int handle;

	public DeleteFileRequest(){
		
	}
	
	public DeleteFileRequest(int handle){
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
		result.put((byte)5);
		result.putInt(handle);
		return result.array();
	}
}