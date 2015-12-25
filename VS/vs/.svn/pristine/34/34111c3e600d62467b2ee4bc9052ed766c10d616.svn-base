#include <stdio.h>
#include <vfilesystem_api.h>
#include <string.h>

int main(int argc, char **args) {
	int returnValue = 0;
	FolderID* folderIDs;
	FileID* fileIDs;
	/*
	* Im Dateisystem ein paar Ordner und Dateien anlegen:
	* root[ rootFile ]
	*  |-root_eins
	*  |-root_zwei[ root_zweiFile1, root_zweiFile2 ]
	*  |-root_drei
	*  |      |-root_drei_eins[ root_drei_einsFile ]
	*  |      |-root_drei_zwei
	*  |      |-root_drei_drei
	*  |                  |-root_drei_drei_fileOrdner[ root_drei_drei_fileOrdnerFile ]
	*  |-root_vier
	*  |-root_fuenf[ root_fuenfFile ]
	*/
	FolderID root = fs_init();
	FolderID root_eins = fs_new_folder("root_eins", root);
	FolderID root_zwei = fs_new_folder("root_zwei", root);
	FolderID root_drei = fs_new_folder("root_drei", root);
	FolderID root_drei_eins = fs_new_folder("root_drei_eins", root_drei);
	FolderID root_drei_zwei = fs_new_folder("root_drei_zwei", root_drei);
	FolderID root_drei_drei = fs_new_folder("root_drei_drei", root_drei);
	FolderID root_drei_drei_fileOrdner = fs_new_folder("root_drei_drei_fileOrdner", root_drei_drei);
	FolderID root_vier = fs_new_folder("root_vier", root);
	FolderID root_fuenf = fs_new_folder("root_fuenf", root);

	FileID rootFile = fs_new_file("rootFile", root);
	FileID root_zweiFile1 = fs_new_file("root_zweiFile1", root_zwei);
	FileID root_zweiFile2 = fs_new_file("root_zweiFile2", root_zwei);
	FileID root_drei_einsFile = fs_new_file("root_drei_einsFile", root_drei_eins);
	FileID root_drei_drei_fileOrdnerFile = fs_new_file("root_drei_drei_fileOrdnerFile", root_drei_drei_fileOrdner);
	FileID root_fuenfFile = fs_new_file("root_fuenfFile", root_fuenf);
	char* string = "blablablablubbidubbiduuuu alkdfjaldfkj il jkaskjdfas";
	char* test;

	printf("\n\n----------Testoutput:----------\n\n");
	/*inhalt jedes Ordners zählen*/
	printf("%d (5) root subfolders\n", fs_get_folder_folder_count(root));
	printf("%d (1) root files\n", fs_get_folder_file_count(root));
	printf("%d (0) root_eins subfolders\n", fs_get_folder_folder_count(root_eins));
	printf("%d (0) root_eins files\n", fs_get_folder_file_count(root_eins));
	printf("%d (0) root_zwei subfolders\n", fs_get_folder_folder_count(root_zwei));
	printf("%d (2) root_zwei files\n", fs_get_folder_file_count(root_zwei));
	printf("%d (3) root_drei subfolders\n", fs_get_folder_folder_count(root_drei));
	printf("%d (0) root_drei files\n", fs_get_folder_file_count(root_drei));
	printf("%d (0) root_drei_eins subfolders\n", fs_get_folder_folder_count(root_drei_eins));
	printf("%d (1) root_drei_eins files\n", fs_get_folder_file_count(root_drei_eins));
	printf("%d (0) root_drei_zwei subfolders\n", fs_get_folder_folder_count(root_drei_zwei));
	printf("%d (0) root_drei_zwei files\n", fs_get_folder_file_count(root_drei_zwei));
	printf("%d (1) root_drei_drei subfolders\n", fs_get_folder_folder_count(root_drei_drei));
	printf("%d (0) root_drei_drei files\n", fs_get_folder_file_count(root_drei_drei));
	printf("%d (0) root_drei_drei_fileOrdner subfolders\n", fs_get_folder_folder_count(root_drei_drei_fileOrdner));
	printf("%d (1) root_drei_drei_fileOrdner files\n", fs_get_folder_file_count(root_drei_drei_fileOrdner));
	printf("%d (0) root_vier subfolders\n", fs_get_folder_folder_count(root_vier));
	printf("%d (0) root_vier files\n", fs_get_folder_file_count(root_vier));
	printf("%d (0) root_fuenf subfolders\n", fs_get_folder_folder_count(root_fuenf));
	printf("%d (1) root_fuenf files\n\n", fs_get_folder_file_count(root_fuenf));

	returnValue = fs_delete_folder(root_fuenf);
	if(returnValue != INVALID_HANDLE) printf("deleting root_fuenf sould be INVALID_HANDLE\n");
	returnValue = fs_delete_file(root_fuenfFile);
	if(returnValue != 0) printf("error deleting root_fuenfFile\n");
	returnValue = fs_delete_folder(root_fuenf);
	if(returnValue != 0) printf("error deleting root_fuenf\n");

	/*file tests:
	parent
	name
	name_length*/
	returnValue = fs_get_file_parent(root_drei_drei_fileOrdnerFile);
	if(returnValue < 0){
		printf("error getting parent Folder of root_drei_drei_fileOrdnerFile");
	}else{
		if(returnValue != root_drei_drei_fileOrdner){
			printf("returned wrong parent of roor_drei_drei_fileOrdnerFile. Should be %d, but was %d", root_drei_drei_fileOrdner, returnValue);
		}
	}

	test = malloc(strlen("root_drei_einsFile"));
	returnValue = fs_get_file_name(root_drei_einsFile, test, strlen("root_drei_einsFile"));
	if(returnValue < 0){
		printf("error getting file name of root_drei_einsFile");
	}

	returnValue = fs_get_file_name_length(root_drei_einsFile);
	if(strlen("root_drei_einsFile") != returnValue){
		printf("error getting name length of root_drei_einsFile");
	}
	free(test);

	/*folder tests:
	parent
	name
	name_length*/
	returnValue = fs_get_folder_parent(root_eins);
	if(returnValue < 0){
		printf("error getting parent Folder of root_eins\n");
	}else{
		if(returnValue != root){
			printf("returned wrong parent of root_eins. Should be %d, but was %d\n", root, returnValue);
		}
	}

	test = malloc(strlen("root_eins"));
	returnValue = fs_get_folder_name(root_eins, test, strlen("root_eins"));
	if(returnValue < 0){
		printf("error getting folder name of root_eins\n");
	}

	returnValue = fs_get_folder_name_length(root_eins);
	if(strlen("root_eins") != returnValue){
		printf("error getting name length of root_eins\n");
	}
	free(test);

	/*subfolders und files geben lassen*/
	fileIDs = malloc(2*sizeof(FileID));
	returnValue = fs_get_folder_files(root_zwei, fileIDs, 2);
	if(returnValue < 0){
		printf("error getting files of root_zwei\n");
	}else{
		if((fileIDs[0] != root_zweiFile1 && fileIDs[1] != root_zweiFile1) || (fileIDs[0] != root_zweiFile2 && fileIDs[1] != root_zweiFile2)){
			printf("error returning fileIDs of root_zwei\n");
		}
	}
	free(fileIDs);

	folderIDs = malloc(3*sizeof(FolderID));
	returnValue = fs_get_folder_folders(root_drei, folderIDs, 3);
	if(returnValue < 0){
		printf("error getting folders of root_drei\n");
	}else{
		if((folderIDs[0] != root_drei_eins && folderIDs[1] != root_drei_eins && folderIDs[2] != root_drei_eins)
		|| (folderIDs[0] != root_drei_zwei && folderIDs[1] != root_drei_zwei && folderIDs[2] != root_drei_zwei)
		|| (folderIDs[0] != root_drei_drei && folderIDs[1] != root_drei_drei && folderIDs[2] != root_drei_drei)){
			printf("error returning folderIDs of root_drei\n");
		}
	}
	free(folderIDs);

	/*Datei schreiben und lesen*/
	test = malloc(1*strlen(string));
	returnValue = fs_write_file(root_drei_drei_fileOrdnerFile, 0, strlen(string), (uint8_t*)string);
	if(returnValue != 0) return -1;

	returnValue = fs_read_file(root_drei_drei_fileOrdnerFile, 0, strlen(string), (uint8_t*)test);
	if(returnValue != 0) return -1;

	printf("\n \n hier die testausgabe der gelesenen Datei: \n eingabe: %s \n ausgabe: %s \n \n", string, test);
	
	fs_destroy();
	free(test);
	printf("\n\n--------------END--------------\n");
	return 0;
}
