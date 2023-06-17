package com.stc.task.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stc.task.entity.Folder;
import com.stc.task.model.CreateFolderRequest;
import com.stc.task.service.FolderService;

@RestController
@RequestMapping("/api/v2")
public class FolderController {

    @Autowired
    private FolderService folderService;

    @PostMapping("/spaces/{spaceId}/folders")
    public ResponseEntity<Folder> createFolder(@PathVariable Long spaceId, @RequestBody CreateFolderRequest request) {
    	request.setSpaceId(spaceId);
        Folder folder = folderService.createFolder(request);
        return ResponseEntity.ok(folder);
    }
}
