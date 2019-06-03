package com.kpi.project.second.service.rest.controller;


import com.kpi.project.second.service.entity.Item;
import com.kpi.project.second.service.service.ItemService;
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
@RequestMapping(path = "/second/users/{userId}/items")
public class ItemController {

    private static Logger log = LoggerFactory.getLogger(ItemController.class);

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/{itemId}/login/{login}")
    public ResponseEntity<Item> getItemByUserLoginAndItemId(@PathVariable String userId, @PathVariable String itemId, @PathVariable String login) {
        log.debug("Try to get item with id '{}' for user with with login '{}'", itemId, login);

        Item item = itemService.findByUserIdItemId(itemId, login);

        log.debug("Send response body item '{}' and status OK", item);

        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("@itemAuthorization.isCorrectItem(#userId, #item)")
    public ResponseEntity<Item> addItem(@PathVariable String userId, @Valid @RequestBody Item item) {
        log.debug("Trying to save item {}", item);

        Item addedItem = itemService.addItem(item);

        log.debug("Send response body saved item '{}' and status CREATED", addedItem);

        return new ResponseEntity<>(addedItem, HttpStatus.CREATED);
    }

    @PostMapping("/{itemId}")
    @PreAuthorize("@itemAuthorization.isCorrectItem(#userId, #itemId, #item)")
    public ResponseEntity<Item> addItemToUserWishList(@PathVariable String userId, @PathVariable String itemId, @Valid @RequestBody Item item){
        log.debug("Trying to add item with id '{}' to user wish list", item.getItemId());

        Item addedItem = itemService.addItemToUserWishList(userId, item);

        log.info("Added item with id '{}' to user wish list", addedItem.getItemId());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{itemId}")
    @PreAuthorize("@itemAuthorization.isCorrectItem(#userId, #itemId, #newItem)")
    public ResponseEntity<Item> updateItem(@PathVariable String userId, @PathVariable String itemId, @Valid @RequestBody Item newItem) {
        log.debug("Trying to update item '{}'", newItem);

        Item updatedItem = itemService.updateItem(newItem);

        log.debug("Send response body updated '{}' and status OK", updatedItem);

        return new ResponseEntity<>(updatedItem, HttpStatus.OK);
    }

    @DeleteMapping("/{itemId}")
    @PreAuthorize("@itemAuthorization.isUserCorrect(#userId)")
    public ResponseEntity<Item> deleteItem(@PathVariable String userId, @PathVariable String itemId) {
        log.debug("Trying to delete item with id '{}' to user wish list", itemId);

        Item deletedItem = itemService.deleteItemFromUserWishList(userId, itemId);

        log.debug("Send response status OK");

        return new ResponseEntity<>(deletedItem, HttpStatus.OK);
    }

    @DeleteMapping
    @PreAuthorize("@itemAuthorization.isCorrectItem(#userId, #item)")
    public ResponseEntity deleteItem(@PathVariable String userId, @Valid @RequestBody Item item) {
        log.debug("Trying to delete item '{}'", item);

        Item deletedItem = itemService.deleteItem(item);

        log.debug("Send response status OK");

        return new ResponseEntity(HttpStatus.OK);
    }

}
