package com.stc.task.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stc.task.entity.PermissionGroup;
import com.stc.task.entity.Space;
import com.stc.task.model.CreateSpaceRequest;
import com.stc.task.model.ItemType;
import com.stc.task.repo.PermissionGroupRepository;
import com.stc.task.repo.PermissionRepository;
import com.stc.task.repo.SpaceRepository;

@Service
public class SpaceService {
    
    @Autowired
    private SpaceRepository spaceRepository;

    @Autowired
    private PermissionGroupRepository permissionGroupRepository;
    
    @Autowired
    PermissionRepository permissionRepository;

    @Transactional
    public Space createSpace(CreateSpaceRequest createSpaceRequest) {

        Space spaceItem = new Space();
        spaceItem.setType(ItemType.SPACE);
        spaceItem.setName(createSpaceRequest.getName());
        spaceItem.setPermissionGroup(getAdminPermissionGroup());

        return  spaceRepository.save(spaceItem);

    }

	private PermissionGroup getAdminPermissionGroup() {
		
		PermissionGroup adminGroup = permissionGroupRepository.findByGroupName("ROLE_ADMIN");
		return adminGroup;
	}
	
}
