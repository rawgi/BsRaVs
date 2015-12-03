package de.hsrm.cs.wwwvs.filesystem.messages;

import java.nio.ByteBuffer;

import de.hsrm.cs.wwwvs.filesystem.messages.marshalling.Marshaller.MarshallingException;


public class FolderInfoRequest implements Payload {
	private int handle;
	
	public FolderInfoRequest(){
		
	}
	
	public FolderInfoRequest(int handle) {
		super();
		this.handle = handle;
	}

	public int getHandle() {
		return handle;
	}

	@Override
	public void unmarshall(ByteBuffer data) throws MarshallingException {
		this.handle = data.getInt();
	}

	@Override
	public byte[] marshall() throws MarshallingException {
		ByteBuffer result = ByteBuffer.allocate(9);
		result.putInt(5);
		result.put((byte)9);
		return result.array();
	}
}
