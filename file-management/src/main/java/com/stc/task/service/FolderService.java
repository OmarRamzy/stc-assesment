package com.stc.task.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stc.task.entity.Folder;
import com.stc.task.entity.PermissionGroup;
import com.stc.task.entity.Space;
import com.stc.task.model.CreateFolderRequest;
import com.stc.task.model.ItemType;
import com.stc.task.repo.FolderRepository;
import com.stc.task.repo.SpaceRepository;

@Service
public class FolderService extends FileManagementService {

    @Autowired
    private SpaceRepository spaceRepository;
    
    @Autowired
    private FolderRepository folderRepository;

    @Transactional
    public Folder createFolder(CreateFolderRequest request) {

        Space spaceItem = spaceRepository.findById(request.getSpaceId()).orElse(null);
        
        if (spaceItem == null || spaceItem.getType() != ItemType.SPACE) {
            throw new IllegalArgumentException("Invalid space item ID");
        }

        PermissionGroup permissionGroup = spaceItem.getPermissionGroup();
        if (!hasEditAccess(permissionGroup , getCurrentUsername())) {
            throw new SecurityException("User does not have edit access to the space");
        }
        
        
        Folder folder = buildFolderItem(request, permissionGroup , spaceItem);
        
        folderRepository.save(folder);

       return folder;
    }


	private Folder buildFolderItem(CreateFolderRequest request, PermissionGroup permissionGroup, Space spaceItem) {
		Folder folder = new Folder();
        folder.setName(request.getName());
        folder.setPermissionGroup(permissionGroup);
        folder.setType(ItemType.FOLDER);
        folder.setSpace(spaceItem);
		return folder;
	}

    

}
