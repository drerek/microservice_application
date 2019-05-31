package com.kpi.project.third.service.dao.rowMappers;

import com.kpi.project.third.service.entity.Item;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import static com.kpi.project.third.service.keys.Key.*;

public class ItemRowMapper implements RowMapper<Item> {
    @Override
    public Item mapRow(ResultSet resultSet, int i) throws SQLException {
        Item item = new Item();
        item.setItemId(resultSet.getInt(ITEM_ITEM_ID));
        item.setName(resultSet.getString(ITEM_NAME));
        item.setDescription(resultSet.getString(ITEM_DESCRIPTION));
        item.setImageFilepath(resultSet.getString(ITEM_IMAGE_FILEPATH));
        item.setLink(resultSet.getString(ITEM_LINK));
        item.setLikes(resultSet.getInt(ITEM_LIKES));
        return item;
    }
}
