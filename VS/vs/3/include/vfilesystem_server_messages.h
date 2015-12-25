
#ifndef FILESERVERMESSAGES_H_
#define FILESERVERMESSAGES_H_

#include <stdint.h>

#define NEW_FILE_REQUEST 1
#define NEW_FILE_RESPONSE 2
#define NEW_FOLDER_REQUEST 3
#define NEW_FOLDER_RESPONSE 4
#define DELETE_FILE_REQUEST 5
#define DELETE_FOLDER_REQUEST 6
#define FILE_INFO_REQUEST 7
#define FILE_INFO_RESPONSE 8
#define FOLDER_INFO_REQUEST 9
#define FOLDER_INFO_RESPONSE 10
#define WRITE_FILE_REQUEST 11
#define READ_FILE_REQUEST 12
#define READ_FILE_RESPONSE 13
#define DELETE_FILE_RESPONSE 14
#define DELETE_FOLDER_RESPONSE 15
#define WRITE_FILE_RESPONSE 16
#define ERROR_RESPONSE 17

/*				FileServerMessage
 0                   1                   2                   3  
 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
+---------------------------------------------------------------+
|     type      |                    payload                    ~
+---------------------------------------------------------------+

payload ::= NewFileRequest | NewFileResponse | DeleteFolderResponse |
            NewFolderRequest | NewFolderResponse | DeleteFileRequest |
            DeleteFolderRequest | FileInfoRequest | FileInfoResponse |
            FolderInfoRequest | FolderInfoResponse | WriteFileRequest |
            ReadFileRequest | ReadFileResponse | DeleteFolderResponse |
            WriteFileResponse | ErrorResponse 
*/


typedef struct {
	uint8_t payload_type;
	uint8_t *payload;
} FileServerMessage;

/* 
	DeleteFileResponse, DeleteFolderResponse, WriteFileResponse bestehen nur 
	aus der Typ-ID und haben keinen weiteren Payload!
*/

/*
 Request -> Response
 
 NewFileRequest         -> NewFileResponse      | ErrorResponse
 NewFolderRequest       -> NewFolderResponse    | ErrorResponse
 DeleteFileRequest      -> DeleteFileResponse   | ErrorResponse
 DeleteFolderRequest    -> DeleteFolderResponse | ErrorResponse
 FileInfoRequest        -> FileInfoResponse     | ErrorResponse
 FolderInfoRequest      -> FolderInfoResponse   | ErrorResponse
 WriteFileRequest       -> WriteFileResponse    | ErrorResponse
 ReadFileRequest        -> ReadFileResponse     | ErrorResponse
*/
	
/* 				NewFileRequest
 0                   1                   2                   3  
 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
+---------------------------------------------------------------+
|                            parent                             |
+---------------------------------------------------------------+
|   name_size   |                     name                      ~
+---------------------------------------------------------------+
~                                                               ~
+---------------------------------------------------------------+
*/

typedef struct {
	uint32_t parent;
	uint8_t name_size;
	uint8_t *name;
} NewFileRequest;

/*				NewFileResponse
 0                   1                   2                   3  
 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
+---------------------------------------------------------------+
|                           handle                              |
+---------------------------------------------------------------+
*/

typedef struct {
	uint32_t handle;
} NewFileResponse;


/* 				NewFolderRequest
 0                   1                   2                   3  
 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
+---------------------------------------------------------------+
|                            parent                             |
+---------------------------------------------------------------+
|   name_size   |                     name                      ~
+---------------------------------------------------------------+
~                                                               ~
+---------------------------------------------------------------+
*/

typedef struct {
	uint32_t parent;
	uint8_t name_size;
	uint8_t *name;
} NewFolderRequest;

/*				NewFolderResponse
 0                   1                   2                   3  
 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
+---------------------------------------------------------------+
|                           handle                              |
+---------------------------------------------------------------+
*/

typedef struct {
	uint32_t handle;
} NewFolderResponse;

/*				DeleteFileRequest
 0                   1                   2                   3  
 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
+---------------------------------------------------------------+
|                           handle                              |
+---------------------------------------------------------------+
*/

typedef struct {
	uint32_t handle;
} DeleteFileRequest;

/*				DeleteFolderRequest
 0                   1                   2                   3  
 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
+---------------------------------------------------------------+
|                           handle                              |
+---------------------------------------------------------------+
*/

typedef struct {
	uint32_t handle;
} DeleteFolderRequest;

/*				FileInfoRequest
 0                   1                   2                   3  
 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
+---------------------------------------------------------------+
|                           handle                              |
+---------------------------------------------------------------+
*/
typedef struct {
	uint32_t handle;
} FileInfoRequest;


/*				FileInfoResponse
 0                   1                   2                   3  
 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
+---------------------------------------------------------------+
|                            parent                             |
+---------------------------------------------------------------+
|                             size                              |
+---------------------------------------------------------------+
|  name_length  |                     name                      ~
+---------------------------------------------------------------+
~                                                               ~
+---------------------------------------------------------------+
*/
typedef struct {
	uint32_t parent;
	uint32_t size;
	uint8_t name_length;
	uint8_t *name;
} FileInfoResponse;

/*				FolderInfoRequest
 0                   1                   2                   3  
 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
+---------------------------------------------------------------+
|                           handle                              |
+---------------------------------------------------------------+
*/
typedef struct {
	uint32_t handle;
} FolderInfoRequest;

/*				FolderInfoResponse
 0                   1                   2                   3  
 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
+---------------------------------------------------------------+
|                            parent                             |
+---------------------------------------------------------------+
|  name_length  |                     name                      ~
+---------------------------------------------------------------+
~                                                               |
+---------------------------------------------------------------+
|                          file_count                           |
+---------------------------------------------------------------+
~                             files                             ~
+---------------------------------------------------------------+
~                                                               |
+---------------------------------------------------------------+
|                          folder_count                         |
+---------------------------------------------------------------+
~                            folders                            ~
+---------------------------------------------------------------+
~                                                               ~
+---------------------------------------------------------------+

*/
typedef struct {
	uint32_t parent;
	uint8_t name_length;
	uint8_t *name;
	uint32_t file_count;
	uint32_t *files;
	uint32_t folder_count;
	uint32_t *folders;

} FolderInfoResponse;

/*				WriteFileRequest
 0                   1                   2                   3  
 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
+---------------------------------------------------------------+
|                            handle                             |
+---------------------------------------------------------------+
|                            offset                             |
+---------------------------------------------------------------+
|                            length                             |
+---------------------------------------------------------------+
~                             data                              ~
+---------------------------------------------------------------+
~                                                               ~
+---------------------------------------------------------------+

*/
typedef struct {
	uint32_t handle;
	uint32_t offset;
	uint32_t length;
	uint8_t *data;
} WriteFileRequest;

/*				ReadFileRequest
 0                   1                   2                   3  
 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
+---------------------------------------------------------------+
|                            handle                             |
+---------------------------------------------------------------+
|                            offset                             |
+---------------------------------------------------------------+
|                            length                             |
+---------------------------------------------------------------+
*/
typedef struct {
	uint32_t handle;
	uint32_t offset;
	uint32_t length;
} ReadFileRequest;

/*				ReadFileResponse
 0                   1                   2                   3  
 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
+---------------------------------------------------------------+
|                             size                              |
+---------------------------------------------------------------+
~                             data                              ~
+---------------------------------------------------------------+
~                                                               ~
+---------------------------------------------------------------+
*/
typedef struct {
	uint32_t size;
	uint8_t *data;
} ReadFileResponse;

/*				ErrorResponse
 0                   1                   2                   3  
 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
+---------------------------------------------------------------+
|  error_code   |                  msg_length                   |
+---------------------------------------------------------------+
|  msg_length   |                      msg                      ~
+---------------------------------------------------------------+
~                                                               ~
+---------------------------------------------------------------+

*/
typedef struct {
	int8_t error_code;
	uint32_t msg_length;
	uint8_t *msg;
} ErrorResponse;

#endif /* FILESERVERMESSAGES_H_ */
