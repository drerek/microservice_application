package com.kpi.project.second.service.service;

import com.kpi.project.second.service.dao.ItemDao;
import com.kpi.project.second.service.entity.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@PropertySource("classpath:strings.properties")
public class WishListService {

    private static Logger log = LoggerFactory.getLogger(WishListService.class);

    private final ItemDao itemDao;

    @Autowired
    public WishListService(ItemDao itemDao) {
        this.itemDao = itemDao;
    }


    public List<Item> getWishList(String ownerLogin) {
        log.debug("Trying to get all WishList for user '{}'", ownerLogin);

        return itemDao.findItemsByOwnerLogin(ownerLogin);
    }

    public List<Item> getWishesByUser(String login) {
        log.debug("Trying to get wishes from dao by user login '{}'", login);

        return itemDao.findItemsByOwnerLogin(login);
    }

    public List<Item> getRecommendations(String[] tagArray) {
        log.debug("Trying to get all recommendations");

        if (tagArray.length == 0) {
            return itemDao.findFirst10ByOrderByNameDesc();
        }

        return itemDao.findItemsByTags(tagArray);
    }

}
