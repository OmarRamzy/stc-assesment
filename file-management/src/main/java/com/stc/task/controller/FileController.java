package com.stc.task.controller;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.stc.task.entity.File;
import com.stc.task.model.CreateFileRequest;
import com.stc.task.service.FileService;

@RestController
@RequestMapping("/api/v2")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/spaces/{spaceId}/folders/{folderId}/files")
    public ResponseEntity<File> createFile(@PathVariable Long spaceId ,@PathVariable Long folderId ,@RequestParam("file") MultipartFile file) 
    																						throws IOException {
    	try{
    		CreateFileRequest request = CreateFileRequest.builder().name(file.getOriginalFilename())
	    								.data(file.getBytes())
	    								.folderItemId(folderId)
	    								.SpaceItemId(spaceId)
	    								.build();
	        File savedFile = fileService.createFile(request);
	        return ResponseEntity.ok(savedFile);
    	}catch (Exception e) {
    		throw e;
    		//return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }
    
    @GetMapping(value= "/files/{fileId}" , produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> getFileById(@PathVariable Long fileId) {
            File file = fileService.downloadFile(fileId);
                return ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_OCTET_STREAM)
                        .header("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"")
                        .body(file.getBinaries());
        
    
}

}
