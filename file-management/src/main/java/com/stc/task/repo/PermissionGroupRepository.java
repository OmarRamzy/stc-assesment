package com.stc.task.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stc.task.entity.PermissionGroup;

@Repository
public interface PermissionGroupRepository extends JpaRepository<PermissionGroup, Long> {

	PermissionGroup findByGroupName(String name);
    // Add any additional custom queries if needed
}
