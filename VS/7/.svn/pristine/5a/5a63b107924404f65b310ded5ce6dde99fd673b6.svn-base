package de.hsrm.cs.wwwvs.filesystem.thriftclient;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.rmi.RemoteException;
import java.util.List;

import org.apache.thrift.TException;

import de.hsrm.cs.wwwvs.filesystem.Filesystem;
import de.hsrm.cs.wwwvs.filesystem.thriftclient.VFileServer.Client;

public class ThriftClientImpl implements Filesystem{
	
	Client client;
	
	public ThriftClientImpl(Client client) {
		this.client = client;
	}

	@Override
	public int get_root_folder() throws IOException, RemoteException {
			try {
				return client.get_root_folder();
			} catch (TException e) {
				e.printStackTrace();
			}
			return 0;
	}

	@Override
	public int new_file(String name, int parent) throws IOException {
		try {
			return client.new_file(name, parent);
		} catch (TException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int new_folder(String name, int parent) throws IOException {
		try {
			return client.new_folder(name, parent);
		} catch (TException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public void delete_file(int file) throws IOException {
		try {
			client.delete_file(file);
		} catch (TException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete_folder(int folder) throws IOException {
		try {
			client.delete_folder(folder);
		} catch (TException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int get_file_parent(int file) throws IOException {
		try {
			return client.get_file_parent(file);
		} catch (TException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int get_file_size(int file) throws IOException {
		try {
			return client.get_file_size(file);
		} catch (TException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public String get_file_name(int file) throws IOException {
		try {
			return client.get_file_name(file);
		} catch (TException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int get_folder_parent(int folder) throws IOException {
		try {
			return client.get_folder_parent(folder);
		} catch (TException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public String get_folder_name(int folder) throws IOException {
		try {
			return client.get_folder_name(folder);
		} catch (TException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int get_folder_file_count(int folder) throws IOException {
		try {
			return client.get_folder_file_count(folder);
		} catch (TException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int get_folder_folder_count(int folder) throws IOException {
		try {
			return client.get_folder_folder_count(folder);
		} catch (TException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Integer> get_folder_files(int folder) throws IOException {
		try {
			return client.recv_get_folder_files();
		} catch (TException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Integer> get_folder_folders(int folder) throws IOException {
		try {
			return client.get_folder_folders(folder);
		} catch (TException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void write_file(int file, int offset, byte[] data) throws IOException {
		try {
			client.write_file(file, offset, ByteBuffer.wrap(data));
		} catch (TException e) {
			e.printStackTrace();
		}
	}

	@Override
	public byte[] read_file(int file, int offset, int length) throws IOException {
		try {
			return client.read_file(file, offset, length).array();
		} catch (TException e) {
			e.printStackTrace();
		}
		return null;
	}
	

}
