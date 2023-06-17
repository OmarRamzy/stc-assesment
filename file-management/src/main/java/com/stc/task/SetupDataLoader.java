package com.stc.task;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.stc.task.entity.Permission;
import com.stc.task.entity.PermissionGroup;
import com.stc.task.model.PermissionLevel;
import com.stc.task.repo.PermissionGroupRepository;
import com.stc.task.repo.PermissionRepository;

@Component
public class SetupDataLoader implements
  ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

 
    @Autowired
    private PermissionGroupRepository roleRepository;
 
    @Autowired
    private PermissionRepository privilegeRepository;
 
 
    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
 
        if (alreadySetup)
            return;
        Permission readPrivilege
          = createPrivilegeIfNotFound(PermissionLevel.VIEW);
        Permission writePrivilege
          = createPrivilegeIfNotFound(PermissionLevel.EDIT);
        readPrivilege.setUserEmail("mohamed@gmail.com");
        writePrivilege.setUserEmail("omar@gmail.com");
        
        List<Permission> adminPrivileges = Arrays.asList(
          readPrivilege, writePrivilege);
        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege));

        alreadySetup = true;
    }

    @Transactional
    Permission createPrivilegeIfNotFound(PermissionLevel permissionLevel) {
 
    	Permission privilege = privilegeRepository.findByPermissionLevel(permissionLevel);
        if (privilege == null) {
            privilege = new Permission();
            privilege.setPermissionLevel(permissionLevel);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }

    @Transactional
    PermissionGroup createRoleIfNotFound(
      String name, Collection<Permission> privileges) {
 
    	PermissionGroup role = roleRepository.findByGroupName(name);
        if (role == null) {
            role = new PermissionGroup();
            role.setGroupName(name);
            for (Permission permission : privileges)
            	permission.setPermissionGroup(role);
            
            roleRepository.save(role);
        }
        return role;
    }
}