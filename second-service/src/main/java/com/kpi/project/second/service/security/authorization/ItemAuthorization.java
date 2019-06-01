package com.kpi.project.second.service.security.authorization;

import com.kpi.project.second.service.entity.Item;
import com.kpi.project.second.service.security.AuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemAuthorization extends AbstractAuthorization {

    @Autowired
    public ItemAuthorization(AuthenticationFacade authenticationFacade) {
        super(authenticationFacade);
    }

    public boolean isCorrectItem(String userId, Item item) {

        if (item == null) {
            return false;
        }

        return userId.equals(item.getOwnerLogin()) && isUserCorrect(userId);
    }

    public boolean isCorrectItem(String userId, String itemId, Item item) {
        return isCorrectItem(userId, item) && itemId.equals(item.getItemId());
    }

}
