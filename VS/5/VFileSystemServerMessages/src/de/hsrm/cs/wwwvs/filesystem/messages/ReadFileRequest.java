package de.hsrm.cs.wwwvs.filesystem.messages;

import java.nio.ByteBuffer;

public class ReadFileRequest implements Payload{
	private int handle;
	private int offset;
	private int length;

	public ReadFileRequest(){
		
	}
	
	public ReadFileRequest(int handle, int offset, int length){
		super();
		this.handle = handle;
		this.offset = offset;
		this.length = length;
	}

	public int getHandle(){
		return handle;
	}

	public int getOffset(){
		return offset;
	}

	public int getLength(){
		return length;
	}

	@Override
	public void unmarshall(ByteBuffer data) throws MarshallingException{
		data.getInt(handle);
		data.getInt(offset);
		data.getInt(length);		
	}

	@Override
	public byte[] marshall() throws MarshallingException{
		ByteBuffer result = ByteBuffer.allocate(17);
		result.putInt(13);
		result.put((byte)12);
		result.putInt(handle);
		result.putInt(offset);
		result.putInt(length);
		
		return result.array();
	}
}