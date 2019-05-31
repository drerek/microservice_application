package com.kpi.project.third.service.dao;

import com.kpi.project.third.service.entity.ItemComment;

import java.util.List;

public interface ItemCommentDao extends Dao<ItemComment> {
    List<ItemComment> getCommentsForItemId(int id);
}
