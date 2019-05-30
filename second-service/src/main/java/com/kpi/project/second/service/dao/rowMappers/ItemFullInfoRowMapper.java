package com.kpi.project.second.service.dao.rowMappers;

import com.kpi.project.second.service.entity.Item;
import com.kpi.project.second.service.entity.ItemPriority;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import static com.kpi.project.second.service.keys.Key.*;

public class ItemFullInfoRowMapper implements RowMapper<Item> {
    

    @Override
    public Item mapRow(ResultSet resultSet, int i) throws SQLException {
        Item item = new Item();
        item.setItemId(resultSet.getInt(ITEM_ITEM_ID));
        item.setName(resultSet.getString(ITEM_NAME));
        item.setDescription(resultSet.getString(ITEM_DESCRIPTION));
        item.setImageFilepath(resultSet.getString(ITEM_IMAGE_FILEPATH));
        item.setLink(resultSet.getString(ITEM_LINK));
        Timestamp date = resultSet.getTimestamp(ITEM_DUE_DATE);
        item.setDueDate(date == null ? null : date.toString());
        item.setOwnerId(resultSet.getInt(USER_ITEM_USER_ID));
        item.setBookerId(resultSet.getInt(USER_ITEM_BOOKER_ID));
        item.setPriority(ItemPriority.values()[resultSet.getInt(USER_ITEM_PRIORITY_ID) - 1]);
        item.setLikes(resultSet.getInt(ITEM_LIKES));
        return item;
    }
}
