package com.stc.task.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateFileRequest {

    private String name;
    private byte[] data;
    private Long folderItemId;
    private Long SpaceItemId;
    private Long fileId;
	@Override
	public String toString() {
		return "CreateFileRequest [name=" + name + ", folderItemId=" + folderItemId + ", SpaceItemId=" + SpaceItemId
				+ "]";
	}

    
}
