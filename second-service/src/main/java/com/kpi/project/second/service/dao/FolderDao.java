package com.kpi.project.second.service.dao;

import com.kpi.project.second.service.entity.Folder;

import java.util.List;


public interface FolderDao extends Dao<Folder> {

    Folder findById(int id, int userId);

    List<Folder> getUserFolders(int id);

    void moveEventsToGeneral(int id);

}
