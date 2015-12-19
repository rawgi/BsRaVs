typedef binary myBytes
typedef i32 int
service VFileServer{

	int get_root_folder(),

	int new_file(1:string name, 2:int parent),

	int new_folder(1:string name, 2:int parent),
	
	delete_file(1:int file),

	void delete_folder(1:int folder),

	int get_file_parent(1:int file),

	int get_file_size(1:int file),

	string get_file_name(1:int file),

	int get_folder_parent(1:int folder),

	string get_folder_name(1:int folder),

	int get_folder_file_count(1:int folder),

	int get_folder_folder_count(1:int folder)
    
	list<int> get_folder_files(1:int folder),

	list<int> get_folder_folders(1:int folder),

	void write_file(1:int file, 2:int offset, 3:myBytes data),

	myBytes read_file(1:int file, 2:int offset, 3:int length),
}





thrift --gen java server.thrift

ergebnis java datei in emulator ordner (evtl eigenes package)




















}