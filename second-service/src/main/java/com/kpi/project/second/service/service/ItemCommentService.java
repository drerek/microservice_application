package com.kpi.project.second.service.service;

import com.kpi.project.second.service.dao.ItemCommentDao;
import com.kpi.project.second.service.entity.ItemComment;
import com.kpi.project.second.service.entity.User;
import com.kpi.project.second.service.security.AuthenticationFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@PropertySource("classpath:strings.properties")
public class ItemCommentService {

    private static Logger log = LoggerFactory.getLogger(ItemCommentService.class);

    private final ItemCommentDao itemCommentDao;

    private final AuthenticationFacade authenticationFacade;

    @Autowired
    public ItemCommentService(ItemCommentDao itemCommentDao, AuthenticationFacade authenticationFacade) {
        this.itemCommentDao = itemCommentDao;
        this.authenticationFacade = authenticationFacade;
    }

    public ItemComment findById(String id) {

        log.debug("Trying to get comment with id '{}'", id);
        Optional<ItemComment> itemComment = itemCommentDao.findById(id);
        return itemComment.orElseGet(ItemComment::new);
    }

    public ItemComment insert(String bodyText, String itemId) {
        log.debug("Trying to get authenticated user");
        User user = authenticationFacade.getAuthentication();
        log.debug("User was successfully received");

        ItemComment itemComment = new ItemComment();
        itemComment.setPostTime(String.valueOf(System.currentTimeMillis()));
        itemComment.setAuthorLogin(user.getLogin());
        itemComment.setLogin(user.getLogin());
        itemComment.setImageFilepath(user.getImgPath());
        itemComment.setBodyText(bodyText);
        itemComment.setItemId(itemId);

        log.debug("Trying to insert comment '{}'", itemComment);

        return itemCommentDao.insert(itemComment);
    }

    public List<ItemComment> findByItemId(String itemId) {

        log.debug("Trying to get comments for item with id '{}'", itemId);
        return itemCommentDao.findByItemId(itemId);

    }



    public ItemComment deleteById(String commentId) {

        ItemComment deleteItem = findById(commentId);
        log.debug("Trying to delete comment with id '{}'", commentId);

        itemCommentDao.delete(deleteItem);
        return deleteItem;

    }
}
