package de.hsrm.cs.wwwvs.filesystem.messages;

import java.nio.ByteBuffer;

public class DeleteFolderRequest implements Payload{
	private int handle;

	public DeleteFolderRequest(){
		
	}
	
	public DeleteFolderRequest(int handle){
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
		result.put((byte)6);
		result.putInt(handle);
		return result.array();
	}
}