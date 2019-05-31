package com.kpi.project.third.service.service;

import com.kpi.project.third.service.dao.ItemDao;
import com.kpi.project.third.service.dao.UserDao;
import com.kpi.project.third.service.entity.Item;
import com.kpi.project.third.service.entity.User;
import com.kpi.project.third.service.exception.runtime.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.kpi.project.third.service.keys.Key.EXCEPTION_ENTITY_NOT_FOUND;

@Service
@PropertySource("classpath:strings.properties")
public class WishListService {

    private static Logger log = LoggerFactory.getLogger(WishListService.class);

    private final ItemDao itemDao;
    private final UserDao userDao;
    private final Environment env;

    @Autowired
    public WishListService(ItemDao itemDao, UserDao userDao, Environment env) {
        this.itemDao = itemDao;
        this.userDao = userDao;
        this.env = env;
    }


    public List<Item> getWishList(int userId) {
        log.debug("Trying to get all WishList for user '{}'", userId);

        return itemDao.getWishListByUserId(userId);
    }

    public List<Item> getWishesByUser(String login) {
        User user = userDao.findByLogin(login);

        if (user == null) {
            throw new EntityNotFoundException(String.format(env.getProperty(EXCEPTION_ENTITY_NOT_FOUND), "User", "login", login));
        }

        log.debug("Trying to get wishes from dao by user login '{}'", login);

        return itemDao.getWishListByUserId(user.getId());
    }

    public List<Item> getRecommendations(String[] tagArray) {
        log.debug("Trying to get all recommendations");

        if (tagArray.length == 0) {
            return itemDao.getPopularItems();
        }

        return itemDao.findItemsByTagName(tagArray);
    }

    public List<Item> getBookingByUser(int userId) {
        log.debug("Trying to get booking wishes from dao for user '{}'", userId);

        return itemDao.findBookedItemsByUserId(userId);
    }

    public List<String> getSearchTags(String tagPart) {
        log.debug("Trying to get tags from dao by tag part '{}'", tagPart);

        return itemDao.searchTag(tagPart);
    }
}
