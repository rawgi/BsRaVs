package de.hsrm.cs.wwwvs.filesystem.thriftclient;

import java.nio.ByteBuffer;
import java.util.List;

import org.apache.thrift.TException;

import de.hsrm.cs.wwwvs.filesystem.thriftclient.VFileServer.Client;
import de.hsrm.cs.wwwvs.filesystem.thriftclient.VFileServer.Iface;

public class ThriftClientImpl implements Iface{
	
	Client client;
	
	public ThriftClientImpl(Client client) {
		this.client = client;
	}
	
	@Override
	public int get_root_folder() throws TException {
		return client.get_root_folder();
	}

	@Override
	public int new_file(String name, int parent) throws TException {
		return client.new_file(name, parent);
	}

	@Override
	public int new_folder(String name, int parent) throws TException {
		return client.new_folder(name, parent);
	}

	@Override
	public void delete_file(int file) throws TException {
		client.delete_file(file);
	}

	@Override
	public void delete_folder(int folder) throws TException {
		client.delete_folder(folder);
	}

	@Override
	public int get_file_parent(int file) throws TException {
		return client.get_file_parent(file);
	}

	@Override
	public int get_file_size(int file) throws TException {
		return client.get_file_size(file);
	}

	@Override
	public String get_file_name(int file) throws TException {
		return client.get_file_name(file);
	}

	@Override
	public int get_folder_parent(int folder) throws TException {
		return client.get_folder_parent(folder);
	}

	@Override
	public String get_folder_name(int folder) throws TException {
		return client.get_folder_name(folder);
	}

	@Override
	public int get_folder_file_count(int folder) throws TException {
		return client.get_folder_file_count(folder);
	}

	@Override
	public int get_folder_folder_count(int folder) throws TException {
		return client.get_folder_folder_count(folder);
	}

	@Override
	public List<Integer> get_folder_files(int folder) throws TException {
		return client.get_folder_files(folder);
	}

	@Override
	public List<Integer> get_folder_folders(int folder) throws TException {
		return client.get_folder_folders(folder);
	}

	@Override
	public void write_file(int file, int offset, ByteBuffer data) throws TException {
		client.write_file(file, offset, data);
	}

	@Override
	public ByteBuffer read_file(int file, int offset, int length) throws TException {
		return client.read_file(file, offset, length);
	}
}
