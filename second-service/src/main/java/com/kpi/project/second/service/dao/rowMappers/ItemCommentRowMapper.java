package com.kpi.project.second.service.dao.rowMappers;

import com.kpi.project.second.service.entity.ItemComment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.kpi.project.second.service.keys.Key.*;

public class ItemCommentRowMapper implements RowMapper<ItemComment> {
    @Override
    public ItemComment mapRow(ResultSet rs, int rowNum) throws SQLException {
        ItemComment itemComment = new ItemComment();
        itemComment.setCommentId(rs.getInt(ITEM_COMMENT_COMMENT_ID));
        itemComment.setPostTime(rs.getTimestamp(ITEM_COMMENT_POST_TIME));
        itemComment.setBodyText(rs.getString(ITEM_COMMENT_BODY_TEXT));
        itemComment.setItemId(rs.getInt(ITEM_COMMENT_ITEM_ID));
        itemComment.setAuthorId(rs.getInt(ITEM_COMMENT_AUTHOR_ID));
        itemComment.setLogin(rs.getString(ITEM_COMMENT_AUTHOR_LOGIN));
        itemComment.setImageFilepath(rs.getString(ITEM_COMMENT_AUTHOR_IMG));
        return itemComment;
    }
}
