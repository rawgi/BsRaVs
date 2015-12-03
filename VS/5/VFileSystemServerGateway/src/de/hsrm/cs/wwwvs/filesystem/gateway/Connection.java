package de.hsrm.cs.wwwvs.filesystem.gateway;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;

import de.hsrm.cs.wwwvs.filesystem.messages.ErrorResponse;
import de.hsrm.cs.wwwvs.filesystem.messages.FileInfoResponse;
import de.hsrm.cs.wwwvs.filesystem.messages.FileServerMessage;
import de.hsrm.cs.wwwvs.filesystem.messages.FolderInfoResponse;
import de.hsrm.cs.wwwvs.filesystem.messages.NewFileRequest;
import de.hsrm.cs.wwwvs.filesystem.messages.NewFileResponse;
import de.hsrm.cs.wwwvs.filesystem.messages.NewFolderResponse;
import de.hsrm.cs.wwwvs.filesystem.messages.Payload;
import de.hsrm.cs.wwwvs.filesystem.messages.PayloadType;
import de.hsrm.cs.wwwvs.filesystem.messages.ReadFileResponse;
import de.hsrm.cs.wwwvs.filesystem.messages.marshalling.Marshaller.MarshallingException;

public class Connection {
	
	private final Socket socket;

	public Connection(Socket socket) {
		this.socket = socket;
	}

	/**
	 * Serialisiert den Request über das Marshaller Interface, sendet die Nachricht über den Socket
	 * an den Server, empfängt die Antwort, deserialisiert diese und gibt die Response zurück.
	 * @param request der Request, der an den Server geschickt werden soll
	 * @return die Response des Servers
	 * @throws MarshallingException
	 * @throws IOException
	 */
	
	public FileServerMessage remoteOperation(FileServerMessage request) throws MarshallingException, IOException {
			PayloadType payloadType = request.getPayloadType();
			InputStream in = socket.getInputStream();
			OutputStream out = socket.getOutputStream();
			byte[] req;
			int res_length;
			byte[] res_len = new byte[4];
			req = request.marshall();
			out.write(req);
			
			in.read(res_len, 0, 4);
			res_length = ByteBuffer.wrap(res_len).getInt();
			byte[] res = new byte[res_length];
			in.read(res);
			ByteBuffer resBB = ByteBuffer.wrap(res);
			PayloadType resPLT = PayloadType.values()[resBB.get()];
			Payload resPL = makePayloadFromType(resPLT.getId());
			if(resPL != null){
				resPL.unmarshall(resBB);
			}
		return new FileServerMessage(resPLT, resPL);
	}
	
	private Payload makePayloadFromType(byte id){
		switch(id){
		case 2: return new NewFileResponse();
		case 4: return new NewFolderResponse();
		case 8: return new FileInfoResponse();
		case 10: return new FolderInfoResponse();
		case 13: return new ReadFileResponse();
		case 17: return new ErrorResponse();
		default: return null;
		}
	}
}
