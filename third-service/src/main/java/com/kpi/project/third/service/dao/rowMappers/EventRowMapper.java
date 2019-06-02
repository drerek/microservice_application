package com.kpi.project.third.service.dao.rowMappers;

import com.kpi.project.third.service.entity.Event;
import com.kpi.project.third.service.entity.EventPeriodicity;
import com.kpi.project.third.service.entity.EventType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import static com.kpi.project.third.service.keys.Key.*;

public class EventRowMapper implements RowMapper<Event> {
    @Override
    public Event mapRow(ResultSet resultSet, int i) throws SQLException {
        Event event = new Event();
        Timestamp date;

        event.setEventId(resultSet.getInt(EVENT_EVENT_ID));
        event.setName(resultSet.getString(EVENT_NAME));
        date = resultSet.getTimestamp(EVENT_EVENT_DATE);
        event.setEventDate(date == null ? null : date.toString());
        event.setDescription(resultSet.getString(EVENT_DESCRIPTION));
        event.setPeriodicityId(resultSet.getInt(EVENT_PERIODICITY_ID));
        event.setPeriodicity(EventPeriodicity.valueOf(resultSet.getString("PERIODICITY_NAME")));
        event.setPlace(resultSet.getString(EVENT_PLACE));
        event.setEventTypeId(resultSet.getInt(EVENT_EVENT_TYPE_ID));
        event.setEventType(EventType.valueOf(resultSet.getString("TYPE")));
        event.setIsDraft(resultSet.getInt(EVENT_IS_DRAFT) == 1);
        event.setFolderId(resultSet.getInt(EVENT_FOLDER_ID));
        event.setImageFilepath(resultSet.getString(EVENT_IMAGE_FILEPATH));
        event.setOwnerId(resultSet.getString("OWNER_ID"));

        return event;
    }
}
