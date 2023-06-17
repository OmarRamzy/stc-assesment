package com.stc.task.model;
public class CreateSpaceRequest {

    private String name;

    public CreateSpaceRequest() {
    }

    public CreateSpaceRequest(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
