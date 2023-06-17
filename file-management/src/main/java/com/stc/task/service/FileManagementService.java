package com.stc.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.stc.task.entity.Permission;
import com.stc.task.entity.PermissionGroup;
import com.stc.task.repo.PermissionRepository;

public class FileManagementService {

    @Autowired
    private PermissionRepository permissionRepository;

    
    protected String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return authentication.getName();
        }
        return null;
    }
    
    protected boolean hasEditAccess(PermissionGroup permissionGroup , String userEmail) {

    	Permission permission = permissionRepository.findByUserEmail(userEmail).orElse(null);

    	if( permission == null || !permission.getPermissionGroup().equals(permissionGroup))
    	{
    		return false;
    	}
    	
        return true;
    }


}
