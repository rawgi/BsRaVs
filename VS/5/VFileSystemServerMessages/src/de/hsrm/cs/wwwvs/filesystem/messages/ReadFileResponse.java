package de.hsrm.cs.wwwvs.filesystem.messages;

import java.nio.ByteBuffer;

public class ReadFileResponse implements Payload{
	private byte[] data;

	public ReadFileResponse(){
		
	}

	public ReadFileResponse(byte[] data){
		super();
		this.data = data;
	}

	public byte[] getData(){
		return data;
	}
	
	@Override
	public void unmarshall(ByteBuffer data) throws MarshallingException{
		int size = data.getInt();
		this.data = new byte[size];
		data.get(this.data, 0, size);
	}

	@Override
	public byte[] marshall() throws MarshallingException{
		ByteBuffer result = ByteBuffer.allocate(8+data.length);
		result.putInt(4+data.length);
		result.put((byte)13);
		result.putInt(data.length);
		result.put(data);
		return result.array();
	}
}