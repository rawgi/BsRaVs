package de.hsrm.cs.wwwvs.filesystem.messages;

import java.nio.ByteBuffer;

public class WriteFileRequest implements Payload {
	private int handle;
	private int offset;
	private byte[] data;

	public WriteFileRequest(){
		
	}
	
	public WriteFileRequest(int handle, int offset, byte[] data) {
		super();
		this.handle = handle;
		this.offset = offset;
		this.data = data;
	}

	public int getHandle() {
		return handle;
	}

	public int getOffset() {
		return offset;
	}

	public byte[] getData() {
		return data;
	}

	@Override
	public void unmarshall(ByteBuffer data) throws MarshallingException {
		handle = data.getInt();
		offset = data.getInt();
		this.data = new byte[data.getInt()];
		data.get(this.data);
	}

	@Override
	public byte[] marshall() throws MarshallingException {
		ByteBuffer result = ByteBuffer.allocate(17+this.data.length);
		result.putInt(13+this.data.length);
		result.put((byte)16);
		result.putInt(handle);
		result.putInt(offset);
		result.putInt(this.data.length);
		result.put(this.data);
		return result.array();
	}

}
