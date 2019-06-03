package com.kpi.project.second.service.dao;

import com.kpi.project.second.service.entity.ItemComment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ItemCommentDao extends MongoRepository<ItemComment, String> {
    List<ItemComment> findByItemId(String id);

}
