package de.hsrm.cs.wwwvs.filesystem.messages;

import java.nio.ByteBuffer;

import de.hsrm.cs.wwwvs.filesystem.messages.marshalling.Marshaller;

public class FileServerMessage implements Marshaller {
	private PayloadType payloadType;
	private Payload payload;

	
	public FileServerMessage() {
		super();
	}
	
	public FileServerMessage(PayloadType payloadType, Payload payload) {
		super();
		this.payloadType = payloadType;
		this.payload = payload;
	}

	public PayloadType getPayloadType() {
		return payloadType;
	}

	public Payload getPayload() {
		return payload;
	}
	
	@Override
	public void unmarshall(ByteBuffer data) throws MarshallingException{
		byte plType = data.get();
		switch(plType){
			case 1: payloadType = PayloadType.NEW_FILE_REQUEST;
					payload = new NewFileRequest();
					break;
			case 2: payloadType = PayloadType.NEW_FILE_RESPONSE;
					payload = new NewFileResponse();
					break;
			case 3: payloadType = PayloadType.NEW_FOLDER_REQUEST;
					payload = new NewFolderRequest();
					break;
			case 4: payloadType = PayloadType.NEW_FOLDER_RESPONSE;
					payload = new NewFolderResponse();
					break;
			case 5: payloadType = PayloadType.DELETE_FILE_REQUEST;
					payload = new DeleteFileRequest();
					break;
			case 6: payloadType = PayloadType.DELETE_FOLDER_REQUEST;
					payload = new DeleteFolderRequest();
					break;
			case 7: payloadType = PayloadType.FILE_INFO_REQUEST;
					payload = new FileInfoRequest();
					break;
			case 8: payloadType = PayloadType.FILE_INFO_RESPONSE;
					payload = new FileInfoResponse();
					break;
			case 9: payloadType = PayloadType.FOLDER_INFO_REQUEST;
					payload = new FolderInfoRequest();
					break;
			case 10: payloadType = PayloadType.FOLDER_INFO_RESPONSE;
					payload = new FolderInfoResponse();
					break;
			case 11: payloadType = PayloadType.WRITE_FILE_REQUEST;
					payload = new WriteFileRequest();
					break;
			case 12: payloadType = PayloadType.READ_FILE_REQUEST;
					payload = new ReadFileRequest();
					break;
			case 13: payloadType = PayloadType.READ_FILE_RESPONSE;
					payload = new ReadFileResponse();
					break;
			case 14: payloadType = PayloadType.DELETE_FILE_RESPONSE; break;
			case 15: payloadType = PayloadType.DELETE_FOLDER_RESPONSE; break;
			case 16: payloadType = PayloadType.WRITE_FILE_RESPONSE; break;
			case 17: payloadType = PayloadType.ERROR_RESPONSE;
					payload = new ErrorResponse();
					break;
			default: throw new MarshallingException("No valid Payload Type.");
		}
		if(payload != null){
			payload.unmarshall(data);
		}
	}

	@Override
	public byte[] marshall() throws MarshallingException {
		if(payload != null){
			return payload.marshall();
		}
		return null;
	}
}
