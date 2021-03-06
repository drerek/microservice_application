package com.kpi.project.third.service.security.authorization;

import com.kpi.project.third.service.entity.Folder;
import com.kpi.project.third.service.security.AuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FolderAuthorization extends AbstractAuthorization{

    @Autowired
    public FolderAuthorization(AuthenticationFacade authenticationFacade) {
        super(authenticationFacade);
    }

    public boolean isFolderCorrect(String userId, Folder folder) {
        return isUserCorrect(userId) && userId.equals(folder.getUserId());
    }

    public boolean isFolderCorrect(String userId, int folderId, Folder folder) {
        return isUserCorrect(userId) && folderId == folder.getFolderId();
    }
}
