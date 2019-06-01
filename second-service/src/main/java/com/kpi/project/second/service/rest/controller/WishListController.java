package com.kpi.project.second.service.rest.controller;

import com.kpi.project.second.service.entity.Item;
import com.kpi.project.second.service.service.WishListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/second/users/{userId}/wishes")
public class WishListController {

    private static Logger log = LoggerFactory.getLogger(WishListController.class);

    @Autowired
    private WishListService wishListService;

    @GetMapping
    @PreAuthorize("@wishListAuthorization.isUserCorrect(#ownerLogin)")
    public ResponseEntity<List<Item>> getWishList(@PathVariable String ownerLogin){
        log.debug("Trying to get wish list");

        List<Item> items = wishListService.getWishList(ownerLogin);

        log.debug("Send response body items '{}' and status OK", items.toString());

        return new ResponseEntity<>(items, HttpStatus.OK);
    }


    @GetMapping("/{login}")
    @PreAuthorize("@wishListAuthorization.isUserCorrect(#userId)")
    public ResponseEntity<List<Item>> getWishesByUser(@PathVariable String userId, @PathVariable String login) {
        log.debug("Trying to get wishes by login '{}'", login);

        List<Item> userWishes = wishListService.getWishesByUser(login);

        log.debug("Send response body wishes '{}' and status OK", userWishes.toString());

        return new ResponseEntity<>(userWishes, HttpStatus.OK);
    }

    @PostMapping("/recommendations")
    @PreAuthorize("@wishListAuthorization.isUserCorrect(#userId)")
    public ResponseEntity<List<Item>> getRecommendations(@PathVariable String userId, @RequestBody String[] tagArray) {
        log.debug("Trying to get  recommend wishes");

        List<Item> items = wishListService.getRecommendations(tagArray);

        log.debug("Send response body items '{}' and status OK", items.toString());

        return new ResponseEntity<>(items, HttpStatus.OK);
    }

}