package com.kpi.project.second.service.service;


import com.kpi.project.second.service.dao.ItemDao;
import com.kpi.project.second.service.entity.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemService {

    private static Logger log = LoggerFactory.getLogger(ItemService.class);

    private final ItemDao itemDao;

    @Autowired
    public ItemService(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    public Item findByUserIdItemId(String itemId, String ownerLogin) {
        log.debug("Trying to find item by user and item id");

        if(ownerLogin.equals("no")) {
            Optional<Item> item = itemDao.findById(String.valueOf(itemId));
            if (item.isPresent()) {
                return item.get();
            }
        }

        return itemDao.findItemByOwnerLoginAndItemId(ownerLogin, itemId);
    }

    public Item addItem(Item item) {
        log.debug("Trying to insert item to database");

        return itemDao.insert(item);
    }

    public Item updateItem(Item item) {
        log.debug("Trying to update item '{}' in database", item);

        return itemDao.save(item);
    }

    public Item deleteItem(Item item) {
        log.debug("Trying to delete item '{}' from database", item);

        itemDao.delete(item);
        return item;
    }

    public Item addItemToUserWishList(String userId, Item item) {
        log.debug("Trying to add item with id '{}' in user '{}' wish list", item.getItemId(), userId);

        return itemDao.save(item);
    }

    public Item deleteItemFromUserWishList(String userId, String itemId) {
        log.debug("Trying to delete item with id '{}' from user '{}' wish list", itemId, userId);

        return itemDao.deleteItemByOwnerLoginAndItemId(userId, itemId);
    }
}
