package com.kpi.project.third.service.security.authorization;

import com.kpi.project.third.service.entity.Item;
import com.kpi.project.third.service.security.AuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemAuthorization extends AbstractAuthorization {

    @Autowired
    public ItemAuthorization(AuthenticationFacade authenticationFacade) {
        super(authenticationFacade);
    }

    public boolean isCorrectItem(int userId, Item item) {

        if (item == null) {
            return false;
        }

        return userId == item.getOwnerId() && isUserCorrect(userId);
    }

    public boolean isCorrectItem(int userId, int itemId, Item item) {
        return isCorrectItem(userId, item) && itemId == item.getItemId();
    }

}
