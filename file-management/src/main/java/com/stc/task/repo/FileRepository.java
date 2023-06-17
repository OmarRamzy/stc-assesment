package com.stc.task.repo;
import org.springframework.data.jpa.repository.JpaRepository;

import com.stc.task.entity.File;

public interface FileRepository extends JpaRepository<File, Long> {

}
