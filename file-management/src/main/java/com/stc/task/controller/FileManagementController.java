package com.stc.task.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.stc.task.entity.File;
import com.stc.task.entity.Folder;
import com.stc.task.entity.Space;
import com.stc.task.model.CreateFileRequest;
import com.stc.task.model.CreateFolderRequest;
import com.stc.task.model.CreateSpaceRequest;
import com.stc.task.service.FileService;
import com.stc.task.service.FolderService;
import com.stc.task.service.SpaceService;

@RestController
@RequestMapping("/api/v1")
@Deprecated
public class FileManagementController {

    @Autowired
    private SpaceService spaceService;

    @Autowired
    private FolderService folderService;

    @Autowired
    private FileService fileService;
    
    @PostMapping("/spaces")
    public ResponseEntity<Space> createSpace(@RequestBody CreateSpaceRequest createSpaceRequest) {
        // spaceService.createSpace();
         return  ResponseEntity.ok(spaceService.createSpace(createSpaceRequest));

    }
    
    @PostMapping("spaces/{spaceId}/folders")
    public ResponseEntity<Folder> createFolder(@PathVariable Long spaceId,  @RequestBody CreateFolderRequest request) {
    	request.setSpaceId(spaceId);
        Folder folder = folderService.createFolder(request);
        return ResponseEntity.ok(folder);
    }
    
    @PostMapping("spaces/{spaceId}/folders/{folderId}/files")
    public ResponseEntity<File> createFile(@PathVariable Long spaceId ,@PathVariable Long folderId ,@RequestParam("file") MultipartFile file) 
    																						throws IOException {
    	try{
    		CreateFileRequest request = CreateFileRequest.builder().name(file.getOriginalFilename())
	    								.data(file.getBytes())
	    								.folderItemId(folderId)
	    								.build();
	        File savedFile = fileService.createFile(request);
	        return ResponseEntity.ok(savedFile);
    	}catch (Exception e) {
    		throw e;
    		//return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }




}
