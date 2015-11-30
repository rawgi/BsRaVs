#include <vfilesystem_api.h>
#include <string.h>
#include <stdio.h>

int currId = 0;

/*Grundlegender Aufbau:
* Es gibt 2 einfach verkettete Listen. (Dateien und Ordner)
* Jedes Element einer Liste besitzt ein Feld für das nächste Element.
*/

typedef struct Folder{
	FolderID id;
	FolderID parent;
	char* name;
	struct Folder* next;
}Folder;

typedef struct File{
	FileID id;
	FolderID parent;
	char* name;
	char* content;
	struct File* next;
}File;

Folder* folderStart;
File* fileStart;

int folderExists(FolderID id){
	if(id < 0) return 0;
	if(id == 0 && currId == 1) return 1;
	Folder* list = folderStart;
	while(list != NULL){
		if(list->id == id){
			return 1;
		}
		list = list->next;
	}
	return 0;
}

int fileExists(FileID id){
	File* list = fileStart;
	if(id < 0) return 0;
	while(list != NULL){
		if(list->id == id){
			return 1;
		}
		list = list->next;
	}
	return 0;
}

FolderID fs_init() {
	if(currId == 0){
		folderStart = calloc(1,sizeof(Folder));
		folderStart->id = currId;
		folderStart->name = "root";
		folderStart->next = NULL;
		folderStart->parent = -1;
		currId++;
	}
	return 0;
}

void fs_destroy() {
	Folder* tmpFo;
	File* tmpFi;
	while(folderStart != NULL){
		tmpFo = folderStart;
		folderStart = folderStart->next;
		free(tmpFo); 
	}
	while(fileStart != NULL){
		tmpFi = fileStart;
		fileStart = fileStart->next;
		free(tmpFi); 
	}
}

/*neue Datei erstellen und am Ende der Liste einfügen*/
FileID fs_new_file(char *name, FolderID parent){
	File* list = fileStart;
	File* new = calloc(1,sizeof(File));
	if(!folderExists(parent)) return INVALID_HANDLE;
	new->id = currId;
	currId++;
	new->parent = parent;
	new->name = name;
	new->next = NULL;
	if(list == NULL){
		fileStart = new;
		return new->id;
	}
	while(list->next != NULL){
		list = list->next;
	}
	list->next = new;
	return new->id;
}

/*neuen Ordner erstellen und am Ende der Liste einfügen*/
FolderID fs_new_folder(char *name, FolderID parent){
	Folder* list = folderStart;
	Folder* new = calloc(1,sizeof(Folder));
	if(!folderExists(parent)) return INVALID_HANDLE;
	new->id = currId;
	currId++;
	new->parent = parent;
	new->name = name;
	new->next = NULL;
	while(list->next != NULL){
		list = list->next;
	}
	list->next = new;
	return new->id;
}

/*zu löschendes Element in der Liste suchen und immer das vorherige Element in 'tmp' merken.
wenn Element gefunden, tmp->next auf das next meines elements setzen und element freigeben*/
int32_t fs_delete_file(FileID file) {	
	File* list = fileStart;
	File* tmp;
	if(!fileExists(file)) return INVALID_HANDLE;
	if(list->id == file){
		fileStart = list->next;
		free(list);
		return 0;
	}
	tmp = list;
	list = list->next;
	while(list != NULL){
		if(list->id == file){
			tmp->next = list->next;
			free(list);
			return 0;
		}
		tmp = list;
		list = list->next;
	}
	return -1;
}

/*selbe Vorgehensweise wie bei Dateien.
Nur, dass hier noch geprüft wird, ob der Ordner auch leer ist.*/
int32_t fs_delete_folder(FolderID folder) {
	Folder* list = folderStart;
	File* listFile = fileStart;
	Folder* tmp;
	if(!folderExists(folder)) return INVALID_HANDLE;
	while(list != NULL){
		if(list->parent == folder) return INVALID_HANDLE;
		list = list->next;
	}
	while(listFile != NULL){
		if(listFile->parent == folder) return INVALID_HANDLE;
		listFile = listFile->next;
	}
	list = folderStart;
	if(list->id == folder){
		folderStart = list->next;
		free(list);
		return 0;
	}
	tmp = list;
	list = list->next;
	while(list != NULL){
		if(list->id == folder){
			tmp->next = list->next;
			free(list);
			return 0;
		}
		tmp = list;
		list = list->next;
	}
	return -1;
}

/*liste bis element durchlaufen und parent zurück geben*/
FolderID fs_get_file_parent(FileID file) {
	File* list = fileStart;
	if(!fileExists(file)) return INVALID_HANDLE;
	while(list != NULL){
		if(list->id == file) return list->parent;
		list = list->next;
	}
	return -1;
}

/*liste bis element durchlaufen und size zurück geben*/
int32_t fs_get_file_size(FileID file) {
	File* list = fileStart;
	if(!fileExists(file)) return INVALID_HANDLE;
	while(list->id != file){
		list = list->next;
	}
	return sizeof(list);
}

/*liste bis element durchlaufen und länge des Namens zurück geben*/
int32_t fs_get_file_name_length(FileID file) {
	File* list = fileStart;
	if(!fileExists(file)) return INVALID_HANDLE;
	while(list != NULL){
		if(list->id == file) return strlen(list->name);
		list = list->next;
	}
	return -1;
}

/*liste bis element durchlaufen und namen in file_name speichern*/
int32_t fs_get_file_name(FileID file, char* file_name, uint8_t max_length) {
	File* list = fileStart;
	if(!fileExists(file)) return INVALID_HANDLE;
	while(list != NULL){
		if(list->id == file) break;
		list = list->next;
	}
	if(list != NULL){
		if(fs_get_file_name_length(file) <= max_length){
			file_name = list->name;
			return 0;
		}
	}
	return -1;
}

/*liste bis element durchlaufen und parent zurück geben*/
FolderID fs_get_folder_parent(FolderID folder) {
	Folder* list = folderStart;
	if(!folderExists(folder)) return INVALID_HANDLE;
	while(list != NULL){
		if(list->id == folder) return list->parent;
		list = list->next;
	}
	return -1;
}

/*liste bis element durchlaufen und länge des Namens zurück geben*/
int32_t fs_get_folder_name_length(FolderID folder) {
	Folder* list = folderStart;
	if(!folderExists(folder)) return INVALID_HANDLE;
	while(list != NULL){
		if(list->id == folder) return strlen(list->name);
		list = list->next;
	}
	return -1;
}

/*liste bis element durchlaufen und namen in file_name speichern*/
int32_t fs_get_folder_name(FolderID folder, char* folder_name, uint32_t max_length) {
	int nameLength = fs_get_folder_name_length(folder);
	Folder* list = folderStart;
	if(nameLength <= max_length){
		while(list != NULL){
			if(list->id == folder){
				folder_name = list->name;
				return 0;
			}
			list = list->next;
		}
	}
	return -1;
}

/*Liste aller Dateien durchlaufen und counter bei jedem element hochsetzen, das 'folder' als parent hat*/
int32_t fs_get_folder_file_count(FolderID folder) {
	int count = 0;
	File* list = fileStart;
	while(list != NULL){
		if(list->parent == folder) count++;
		list = list->next;
	}
	return count;
}

/*Liste aller Ordner durchlaufen und counter bei jedem element hochsetzen, das 'folder' als parent hat*/
int32_t fs_get_folder_folder_count(FolderID folder) {
	int count = 0;
	Folder* list = folderStart;
	while(list != NULL){
		if(list->parent == folder) count++;
		list = list->next;
	}
	return count;
}

/*prüfen, ob der übergebene puffer ausreicht.
Liste durchlaufen und jedes gefundene Element in 'files' abspeichern*/
int32_t fs_get_folder_files(FolderID folder, FileID* files, uint32_t max_num_files) {
	int i, fileCount;
	File* list = fileStart;
	fileCount = fs_get_folder_file_count(folder);
	if(fileCount > max_num_files) return INVALID_HANDLE;
	i = 0;
	while(i<fileCount && list != NULL){
		if(list->parent == folder){
			files[i] = list->id;
			i++;
		}
		list = list->next;
	}
	if(i != fileCount) return -1;
	return 0;
}

/*selbe Vorgehensweise wie bei 'fs_get_folder_files'*/
int32_t fs_get_folder_folders(FolderID folder, FolderID* folders, uint32_t max_num_folders) {
	int i, folderCount;
	Folder* list = folderStart;
	folderCount = fs_get_folder_folder_count(folder);
	if(folderCount > max_num_folders) return INVALID_HANDLE;
	i = 0;
	while(i<folderCount && list != NULL){
		if(list->parent == folder){
			folders[i] = list->id;
			i++;
		}
		list = list->next;
	}
	if(i != folderCount) return -1;
	return 0;
}

/*Liste bis zu gesuchter Datei durchlaufen.
Alten Inhalt löschen.
Neuen Inhalt ab offset schreiben.*/
int32_t fs_write_file(FileID file, uint32_t offset, uint32_t length, uint8_t *data){
	File* list = fileStart;
	int read, write = 0;
	if(!fileExists(file)) return INVALID_HANDLE;
	while(list->id != file){
		list = list->next;
	}
	free(list->content);
	list->content = malloc(length-offset);
	for(read = offset; read<length; read++){
		list->content[write] = data[read];
		write++;
	}
	return 0;
}

/*Liste bis zu gesuchter Datei durchlaufen.
prüfen, ob der inhalt der datei noch in 'data' rein passt.
Inhalt in 'data' ab offset schreiben*/
int32_t fs_read_file(FileID file, uint32_t offset, uint32_t length, uint8_t *data){
	File* list = fileStart;
	int read = 0, write, contentLength;
	if(!fileExists(file)) return INVALID_HANDLE;
	while(list->id != file){
		list = list->next;
	}
	contentLength = strlen(list->content);
	if(length-offset < contentLength) return INVALID_HANDLE;
	for(write = offset; write < length && read < contentLength; write++){
		data[write] = list->content[read];
		read++;
	}
	return 0;
}
