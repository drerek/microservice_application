package com.kpi.project.second.service.rest.controller;

import com.kpi.project.second.service.entity.ItemComment;
import com.kpi.project.second.service.service.ItemCommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/second/comment")
public class ItemCommentController {
    private static Logger log = LoggerFactory.getLogger(ItemCommentController.class);

    private final ItemCommentService itemCommentService;

    @Autowired
    public ItemCommentController(ItemCommentService itemCommentService) {
        this.itemCommentService = itemCommentService;
    }

    @GetMapping("{id}")
    public ResponseEntity<ItemComment> getItemCommentById(@PathVariable String id){
        log.debug("Try to get comment item with id '{}'", id);
        ItemComment itemComment = itemCommentService.findById(id);

        log.debug("Send response body item comment '{}' and status OK", itemComment);
        return new ResponseEntity<>(itemComment, HttpStatus.OK);
    }

    @PreAuthorize("@itemCommentPermissionChecker.canDeleteItemComment(#id)")
    @DeleteMapping("{id}")
    public ResponseEntity<ItemComment> deleteById(@PathVariable String id){
        log.debug("Try to delete comment item with id '{}'", id);
        ItemComment deletedItem = itemCommentService.deleteById(id);

        log.debug("Send response body item comment '{}' and status OK", deletedItem);
        return new ResponseEntity<>(deletedItem,HttpStatus.OK);
    }

    @PostMapping("{itemId}")
    public ResponseEntity<ItemComment> insert(@Valid @RequestBody ItemComment itemComment, @PathVariable String itemId){
        log.debug("Try to insert comment '{}'", itemComment.getBodyText());
        ItemComment addedComment = itemCommentService.insert(itemComment.getBodyText(), itemId);

        log.debug("Send response body saved item comment '{}' and status CREATED", addedComment);
        return new ResponseEntity<>(addedComment,HttpStatus.CREATED);
    }

    @GetMapping("{itemId}/comments")
    public ResponseEntity<List<ItemComment>> findByItemId(@PathVariable String itemId){
        log.debug("Try to get comments for item with id '{}'", itemId);
        List<ItemComment> comments = itemCommentService.findByItemId(itemId);

        log.debug("Send response body comments '{}' and status CREATED", comments);
        return new ResponseEntity<>(comments,HttpStatus.OK);
    }
}
