package com.stc.task.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stc.task.entity.Space;
import com.stc.task.model.CreateSpaceRequest;
import com.stc.task.service.SpaceService;

@RestController
@RequestMapping("/api/v2")
public class SpaceController {

    @Autowired
    private SpaceService spaceService;

    @PostMapping("/spaces")
    public ResponseEntity<Space> createSpace(@RequestBody CreateSpaceRequest createSpaceRequest) {
        // spaceService.createSpace();
         return  ResponseEntity.ok(spaceService.createSpace(createSpaceRequest));

    }
}
