package com.kpi.project.first.service.dao;

import com.kpi.project.first.service.entity.ItemComment;

import java.util.List;

public interface ItemCommentDao extends Dao<ItemComment> {
    List<ItemComment> getCommentsForItemId(int id);
}
