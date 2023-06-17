package com.stc.task.repo;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stc.task.entity.Folder;

public interface FolderRepository extends JpaRepository<Folder, Long> {

    Optional<Folder> findByIdAndSpaceId(Long folderId, Long spaceId);

}
