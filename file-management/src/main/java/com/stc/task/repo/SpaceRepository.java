package com.stc.task.repo;
import org.springframework.data.jpa.repository.JpaRepository;

import com.stc.task.entity.Space;

public interface SpaceRepository extends JpaRepository<Space, Long> {
    // Add any additional custom queries if needed
}
