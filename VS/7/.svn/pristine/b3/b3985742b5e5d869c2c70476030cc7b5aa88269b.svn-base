package de.hsrm.cs.wwwvs.filesystem.emulator;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import de.hsrm.cs.wwwvs.filesystem.Filesystem;

public class Emulator implements Filesystem {
	
	private static int idFolderCount = 1;
	private static int idFileCount = 1;
	
	private Folder root = null;
	
	private class Folder {
		private int id;
		private int parent;
		private String name;
		
		List<Folder> subfolders;
		List<File> files;
		
		
		public Folder(String name, int parent, int id) {
			this.name = name;
			this.parent = parent;
			this.id = id;
			subfolders = new LinkedList<Folder>();
			files = new LinkedList<File>();
		}
		
		public int getId() {
			return this.id;
		}

		public Folder findFolder(int id) {
			// check self
			if (this.id == id) {
				return this;
			}
			for (Folder f : this.subfolders) {
				if (f.getId() == id) {
					return f;
				}
				Folder s  = f.findFolder(id);
				if (s != null) {
					return s;
				}
			}
			return null;
		}
		public File findFile(int id) {
			for (Folder f : this.subfolders) {
				for (File fi : f.files) {
					if (fi.id == id) {
						return fi;
					}
				}
				File fi = f.findFile(id);
				if (fi != null) {
					return fi;
				}
			}
			return null;
		}
	}
	private class File {

		private int id;
		private String name;
		private int parent;
		
		byte[] data = null;
		
		public File(String name, int parent, int id) {
			this.name = name;
			this.parent = parent;
			this.id = id;
			
		}
	}

	public Emulator() {
		
		if (this.root == null) {
			root = new Folder("root", 0, 0);
		}
		
		
	}
	
	@Override
	public int get_root_folder() throws IOException {
		
		return root.getId();
	}

	@Override
	public int new_file(String name, int parent) throws IOException {
		
		if (name == null) {
			throw new NullPointerException();
		}
		
		Folder p = root.findFolder(parent);
		
		if (p == null) {
			throw new IOException("Parent does not exist");
		}
		
		File f = new File(name, parent, idFileCount++);
		
		p.files.add(f);
		
		return f.id;
	}

	@Override
	public int new_folder(String name, int parent) throws IOException {
		if (name == null) {
			throw new NullPointerException();
		}
		
		Folder p = root.findFolder(parent);
		if (p == null) {
			throw new IOException("Parent does not exist");
		}
		
		Folder nF = new Folder(name, parent, idFolderCount++);
		
		p.subfolders.add(nF);
		
		return nF.id;
	}

	@Override
	public void delete_file(int file) throws IOException {
		
		File toDelete = root.findFile(file);
		if (toDelete == null) {
			throw new IOException("File does not exist");
		}
		
		
		Folder parent = root.findFolder(toDelete.parent);
		if (parent == null) {
			// should not happen
			throw new IOException();
		}
		parent.files.remove(toDelete);
		
	}

	@Override
	public void delete_folder(int folder) throws IOException {
		
		Folder toDelete = root.findFolder(folder);
		if (toDelete == null) {
			throw new IOException("Folder does not exist");
		}
		
		Folder parent = root.findFolder(toDelete.parent);
		if (parent == null) {
			// should not happen
			throw new IOException();
		}
		
		parent.subfolders.remove(toDelete);
	}

	@Override
	public int get_file_parent(int file) throws IOException {
		File f = root.findFile(file);
		if (f == null) {
			throw new IOException("File does not exist");
		}
		
		return f.parent;
	}

	@Override
	public int get_file_size(int file) throws IOException {
		File f = root.findFile(file);
		if (f == null) {
			throw new IOException("File does not exist");
		}
		
		if (f.data == null) {
			return 0;
		}
		
		return f.data.length;
	}

	@Override
	public String get_file_name(int file) throws IOException {
		File f = root.findFile(file);
		if (f == null) {
			throw new IOException("File does not exist");
		}
		
		return f.name;
	}

	@Override
	public int get_folder_parent(int folder) throws IOException {
		Folder f = root.findFolder(folder);
		if (f == null) {
			throw new IOException("Folder does not exist");
		}
		
		return f.parent;
	}

	@Override
	public String get_folder_name(int folder) throws IOException {
		Folder f = root.findFolder(folder);
		if (f == null) {
			throw new IOException("Folder does not exist");
		}
		
		return f.name;
	}

	@Override
	public int get_folder_file_count(int folder) throws IOException {
		Folder f = root.findFolder(folder);
		if (f == null) {
			throw new IOException("Folder does not exist");
		}
		return f.files.size();
	}

	@Override
	public int get_folder_folder_count(int folder) throws IOException {
		Folder f = root.findFolder(folder);
		if (f == null) {
			throw new IOException("Folder does not exist");
		}
		return f.subfolders.size();
	}

	@Override
	public List<Integer> get_folder_files(int folder) throws IOException {
		Folder f = root.findFolder(folder);
		if (f == null) {
			throw new IOException("Folder does not exist");
		}
		
		List<Integer> retArray = new LinkedList<Integer>();
		
		for (File file : f.files) {
			retArray.add(new Integer(file.id));
		}
		return retArray;
	}

	@Override
	public List<Integer> get_folder_folders(int folder) throws IOException {
		Folder f = root.findFolder(folder);
		if (f == null) {
			throw new IOException("Folder does not exist");
		}
		
		List<Integer> retArray = new LinkedList<Integer>();
		
		for (Folder fo : f.subfolders) {
			retArray.add(new Integer(fo.id));
		}
		
		return retArray;
	}

	@Override
	public void write_file(int file, int offset, byte[] data)
			throws IOException {
		
		File f = root.findFile(file);
		if (f == null) {
			throw new IOException("File does not exist");
		}
		
		// case 1 : no data yet
		if (f.data == null) {
			f.data = data.clone();
			return ;
		}
		
		// case 2 : to big
		
		if (data.length + offset > f.data.length) {
			f.data = Arrays.copyOf(f.data, data.length + offset);
		}
		// all other cases
		System.arraycopy(data, 0, f.data, offset, data.length);
	}

	@Override
	public byte[] read_file(int file, int offset, int length)
			throws IOException {
		
		File f = root.findFile(file);
		if (f == null) {
			throw new IOException("File does not exist");
		}
		
		// no data 
		if (f.data == null) {
			return new byte[0];
		}
		
		// check if read is to large
		if (offset + length > f.data.length) {
			// error 
			return new byte[0];
		}
		
		byte[] retArray = new byte[length];
		
		System.arraycopy(f.data, offset, retArray, 0, length);
		
		
		return retArray;
	}

}
