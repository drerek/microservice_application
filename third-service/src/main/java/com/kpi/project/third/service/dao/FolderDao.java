package com.kpi.project.third.service.dao;

import com.kpi.project.third.service.entity.Folder;

import java.util.List;


public interface FolderDao extends Dao<Folder> {

    Folder findById(int id, String userId);

    List<Folder> getUserFolders(String id);

    void moveEventsToGeneral(int id);

}
