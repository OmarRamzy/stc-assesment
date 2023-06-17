package com.stc.task.repo;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stc.task.entity.Permission;
import com.stc.task.model.PermissionLevel;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

	Permission findByPermissionLevel(PermissionLevel permissionLevel);
	Optional<Permission> findByUserEmail(String email);

}
