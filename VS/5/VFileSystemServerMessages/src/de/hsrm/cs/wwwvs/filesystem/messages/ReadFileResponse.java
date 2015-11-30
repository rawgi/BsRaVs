package de.hsrm.cs.wwwvs.filesystem.messages;

import java.nio.ByteBuffer;


public class ReadFileResponse implements Payload {
	private byte[] data;

	public ReadFileResponse() {
		
	}

	public ReadFileResponse(byte[] data) {
		super();
		this.data = data;
	}

	public byte[] getData() {
		return data;
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