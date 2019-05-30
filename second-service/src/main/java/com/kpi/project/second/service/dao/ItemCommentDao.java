package com.kpi.project.second.service.dao;

import com.kpi.project.second.service.entity.ItemComment;

import java.util.List;

public interface ItemCommentDao extends Dao<ItemComment> {
    List<ItemComment> getCommentsForItemId(int id);
}
