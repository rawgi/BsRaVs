package de.hsrm.cs.wwwvs.filesystem.messages;

import java.nio.ByteBuffer;

public class NewFolderResponse implements Payload {
	private int handle;

	public NewFolderResponse() {
		
	}
	
	public NewFolderResponse(int handle) {
		super();
		this.handle = handle;
	}

	public int getHandle() {
		return handle;
	}

	@Override
	public void unmarshall(ByteBuffer data) throws MarshallingException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public byte[] marshall() throws MarshallingException {
		// TODO Auto-generated method stub
		return null;
	}
}