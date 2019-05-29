package com.kpi.project.first.service.security.authorization;

import com.kpi.project.first.service.entity.Folder;
import com.kpi.project.first.service.security.AuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FolderAuthorization extends AbstractAuthorization{

    @Autowired
    public FolderAuthorization(AuthenticationFacade authenticationFacade) {
        super(authenticationFacade);
    }

    public boolean isFolderCorrect(int userId, Folder folder) {
        return isUserCorrect(userId) && userId == folder.getUserId();
    }

    public boolean isFolderCorrect(int userId, int folderId, Folder folder) {
        return isUserCorrect(userId) && folderId == folder.getFolderId();
    }
}
