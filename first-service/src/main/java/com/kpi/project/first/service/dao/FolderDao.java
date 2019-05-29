package com.kpi.project.first.service.dao;

import com.kpi.project.first.service.entity.Folder;

import java.util.List;


public interface FolderDao extends Dao<Folder> {

    Folder findById(int id, int userId);

    List<Folder> getUserFolders(int id);

    void moveEventsToGeneral(int id);

}
