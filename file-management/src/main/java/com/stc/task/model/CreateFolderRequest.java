package com.stc.task.model;
public class CreateFolderRequest {

    private String name;
    private Long spaceId;

    public CreateFolderRequest() {
    }

    public CreateFolderRequest(String name, Long spaceId) {
        this.name = name;
        this.spaceId = spaceId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(Long spaceId) {
        this.spaceId = spaceId;
    }
}
