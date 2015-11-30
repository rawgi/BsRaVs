package de.hsrm.cs.wwwvs.filesystem.gateway;

import java.io.IOException;
import java.net.Socket;

import de.hsrm.cs.wwwvs.filesystem.messages.FileServerMessage;
import de.hsrm.cs.wwwvs.filesystem.messages.PayloadType;
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
			
		return null;
	}
}
