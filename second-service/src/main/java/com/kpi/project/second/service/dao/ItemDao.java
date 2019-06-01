package com.kpi.project.second.service.dao;

import com.kpi.project.second.service.entity.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ItemDao extends MongoRepository<Item, String> {
    List<Item> findItemsByOwnerLogin(String ownerLogin);
    Item findItemByOwnerLoginAndItemId(String ownerLogin, String itemId);
    List<Item> findFirst10ByOrderByNameDesc();

    Item deleteItemByOwnerLoginAndItemId(String userId, String itemId);

    List<Item> findItemsByTags(String[] tagName);

}
