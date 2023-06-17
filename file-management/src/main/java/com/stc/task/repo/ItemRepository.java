package com.stc.task.repo;
import org.springframework.data.jpa.repository.JpaRepository;

import com.stc.task.entity.Item;

	
public interface ItemRepository extends JpaRepository<Item, Long> {
    // Add any additional custom queries if needed
}
