package com.stc.task.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stc.task.entity.File;
import com.stc.task.entity.Folder;
import com.stc.task.entity.PermissionGroup;
import com.stc.task.model.CreateFileRequest;
import com.stc.task.model.ItemType;
import com.stc.task.repo.FileRepository;
import com.stc.task.repo.FolderRepository;

@Service
public class FileService extends FileManagementService {

    @Autowired
    private FolderRepository folderRepository;
    
    @Autowired
    private FileRepository fileRepository;
    

    @Transactional
    public File createFile(CreateFileRequest request) {

        Folder folderItem = folderRepository.findByIdAndSpaceId(request.getFolderItemId() , request.getSpaceItemId()).orElse(null);

        if (folderItem == null || folderItem.getType() != ItemType.FOLDER) {
            throw new IllegalArgumentException("Invalid folder item ID");
        }

        PermissionGroup permissionGroup = folderItem.getPermissionGroup();
       
        if (!hasEditAccess(permissionGroup , getCurrentUsername())) {
            throw new SecurityException("User does not have edit access to the folder");
        }
        
        File file = buildFileItem(request, folderItem);

        fileRepository.save(file);

        return file;
    }


    @Transactional
    // another way for managing roles and permission ( preferred ) 
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public File downloadFile(Long fileId) {
    	
    	File fileItem = fileRepository.findById(fileId).orElse(null);
    	
    	if (fileItem == null || fileItem.getType() != ItemType.FILE) {
            throw new IllegalArgumentException("Invalid file item ID");
        }
    	
    	return fileItem;
    	
    }
    
	private File buildFileItem(CreateFileRequest request, Folder folderItem) {
		File file = new File();
        file.setName(request.getName());
        file.setType(ItemType.FILE);
        file.setBinaries(request.getData());
        file.setFolder(folderItem);
        file.setPermissionGroup(folderItem.getPermissionGroup());
		return file;
	}

    

}
