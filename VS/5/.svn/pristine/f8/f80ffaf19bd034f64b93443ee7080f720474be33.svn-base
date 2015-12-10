package de.hsrm.cs.wwwvs.filesystem.messages;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class ErrorResponse implements Payload{
	private byte errorCode;
	private String msg;

	public ErrorResponse(){
		
	}
	
	public ErrorResponse(byte errorCode, String msg){
		super();
		this.errorCode = errorCode;
		this.msg = msg;
	}

	public byte getErrorCode(){
		return errorCode;
	}

	public String getMsg(){
		return msg;
	}

	@Override
	public void unmarshall(ByteBuffer data) throws MarshallingException{
		errorCode = data.get();
		int msg_length = data.getInt();
		byte[] msg = new byte[msg_length];
		data.get(msg, 0, msg_length);
		this.msg = new String(msg, Charset.forName("US-ASCII"));
	}

	@Override
	public byte[] marshall() throws MarshallingException{
		ByteBuffer result = ByteBuffer.allocate(9+msg.length());
		result.putInt(5+msg.length());
		result.put((byte)17);
		result.put(errorCode);
		result.putInt(msg.length());
		byte[] string = msg.getBytes(Charset.forName("UTF-8"));
		result.put(string);
		return result.array();
	}
}