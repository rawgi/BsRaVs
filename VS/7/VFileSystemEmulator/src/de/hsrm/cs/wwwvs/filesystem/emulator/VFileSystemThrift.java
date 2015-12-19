package de.hsrm.cs.wwwvs.filesystem.emulator;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

import org.apache.thrift.TException;

import de.hsrm.cs.wwwvs.filesystem.Filesystem;
import de.hsrm.cs.wwwvs.filesystem.emulator.VFileServer.Iface;

public class VFileSystemThrift implements Iface{

	Filesystem fs;
	
	public VFileSystemThrift(Filesystem fs) {
		this.fs = fs;
	}

	@Override
	public int get_root_folder() throws TException {
		try {
			return fs.get_root_folder();
		} catch (IOException e) {
			throw new TException(e);
		}
	}

	@Override
	public int new_file(String name, int parent) throws TException {
		try {
			return fs.new_file(name, parent);
		} catch (IOException e) {
			throw new TException(e);
		}
	}

	@Override
	public int new_folder(String name, int parent) throws TException {
		try {
			return fs.new_folder(name, parent);
		} catch (IOException e) {
			throw new TException(e);
		}
	}

	@Override
	public void delete_file(int file) throws TException {
		try {
			fs.delete_file(file);
		} catch (IOException e) {
			throw new TException(e);
		}
	}

	@Override
	public void delete_folder(int folder) throws TException {
		try {
			fs.delete_folder(folder);
		} catch (IOException e) {
			throw new TException(e);
		}
	}

	@Override
	public int get_file_parent(int file) throws TException {
		try {
			return fs.get_file_parent(file);
		} catch (IOException e) {
			throw new TException(e);
		}
	}

	@Override
	public int get_file_size(int file) throws TException {
		try {
			return fs.get_file_size(file);
		} catch (IOException e) {
			throw new TException(e);
		}
	}

	@Override
	public String get_file_name(int file) throws TException {
		try {
			return fs.get_file_name(file);
		} catch (IOException e) {
			throw new TException(e);
		}
	}

	@Override
	public int get_folder_parent(int folder) throws TException {
		try {
			return fs.get_folder_parent(folder);
		} catch (IOException e) {
			throw new TException(e);
		}
	}

	@Override
	public String get_folder_name(int folder) throws TException {
		try {
			return fs.get_folder_name(folder);
		} catch (IOException e) {
			throw new TException(e);
		}
	}

	@Override
	public int get_folder_file_count(int folder) throws TException {
		try {
			return fs.get_folder_file_count(folder);
		} catch (IOException e) {
			throw new TException(e);
		}
	}

	@Override
	public int get_folder_folder_count(int folder) throws TException {
		try {
			return fs.get_folder_folder_count(folder);
		} catch (IOException e) {
			throw new TException(e);
		}
	}

	@Override
	public List<Integer> get_folder_files(int folder) throws TException {
		try {
			return fs.get_folder_files(folder);
		} catch (IOException e) {
			throw new TException(e);
		}
	}

	@Override
	public List<Integer> get_folder_folders(int folder) throws TException {
		try {
			return fs.get_folder_folders(folder);
		} catch (IOException e) {
			throw new TException(e);
		}
	}

	@Override
	public void write_file(int file, int offset, ByteBuffer data) throws TException {
		try {
			fs.write_file(file, offset, data.array());
		} catch (IOException e) {
			throw new TException(e);
		}
	}

	@Override
	public ByteBuffer read_file(int file, int offset, int length) throws TException {
		try {
			return ByteBuffer.wrap(fs.read_file(file, offset, length));
		} catch (IOException e) {
			throw new TException(e);
		}
	}
}