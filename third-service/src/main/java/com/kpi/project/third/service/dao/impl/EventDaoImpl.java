package com.kpi.project.third.service.dao.impl;


import com.kpi.project.third.service.dao.AbstractDao;
import com.kpi.project.third.service.dao.EventDao;
import com.kpi.project.third.service.dao.rowMappers.EventRowMapper;
import com.kpi.project.third.service.dao.rowMappers.UserRowMapper;
import com.kpi.project.third.service.entity.Event;
import com.kpi.project.third.service.entity.Role;
import com.kpi.project.third.service.entity.User;
import com.kpi.project.third.service.exception.runtime.DatabaseWorkException;
import com.kpi.project.third.service.exception.runtime.DeleteException;
import com.kpi.project.third.service.exception.runtime.EntityNotFoundException;
import com.kpi.project.third.service.exception.runtime.UpdateException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.kpi.project.third.service.keys.Key.*;

@Repository
@PropertySource("classpath:sqlDao.properties")
@PropertySource("classpath:strings.properties")
@PropertySource("classpath:image.properties")
public class EventDaoImpl extends AbstractDao<Event> implements EventDao {


    public EventDaoImpl() {
        log = LoggerFactory.getLogger(EventDaoImpl.class);
    }

    private static final int OWNER_ID = 1;
    private static final int PARTICIPANT_ID = 2;


    @Override
    public List<Event> findByUserId(String userId) {
        List<Event> events;
        log.debug("Try to find list of events by user with id '{}'", userId);
        try {
            events = jdbcTemplate.query(env.getProperty(EVENT_FIND_BY_USER_ID),
                    new Object[]{userId}, new EventRowMapper());
        } catch (DataAccessException e) {
            log.error("Query fails by finding event by user with id '{}'", userId,e);
            throw new DatabaseWorkException(env.getProperty(EXCEPTION_DATABASE_WORK));
        }

        log.debug("Events for user with id '{}' counted '{}'", userId, events.size());

        return events;
    }

    @Override
    public Event findById(int id) {
        Event event;
        log.debug("Try to find event by id '{}'", id);
        try {
            event = jdbcTemplate.queryForObject(
                    env.getProperty(EVENT_FIND_BY_ID),
                    new Object[]{id}, new EventRowMapper()
            );
        } catch (EmptyResultDataAccessException e) {
            log.error("Event was not found by eventId '{}'", id,e);
            throw new EntityNotFoundException(String.format(env.getProperty(EXCEPTION_ENTITY_NOT_FOUND),"Event", "eventId", id));
        } catch (DataAccessException e) {
            log.error("Query fails by finding event with id '{}'", id,e);
            throw new DatabaseWorkException(env.getProperty(EXCEPTION_DATABASE_WORK));
        }

        log.debug("Event with id '{}' was found", id);
        log.debug("Try to set Participants for event with id '{}'", id);

        event.setParticipants(getParticipants(event));

        log.debug("Setting participants for event with id '{}' successful", id);

        return event;
    }

    @Override
    public Event insert(Event model) {
        int id;
        log.debug("Try to insert event with name '{}' by owner with id '{}'", model.getName(), model.getOwnerId());
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate.getDataSource())
                .withTableName(TABLE_EVENT)
                .usingGeneratedKeyColumns(EVENT_EVENT_ID);

        if (model.getImageFilepath() == null) {
            model.setImageFilepath(env.getProperty("image.default.filepath"));
        }

        Map<String, Object> parameters = new HashMap<>();
        parameters.put(EVENT_NAME, model.getName());
        parameters.put(EVENT_EVENT_DATE, model.getEventDate());
        parameters.put(EVENT_PERIODICITY_ID, model.getPeriodicityId());
        parameters.put(EVENT_DESCRIPTION, model.getDescription());
        parameters.put(EVENT_PLACE, model.getPlace());
        parameters.put(EVENT_EVENT_TYPE_ID, model.getEventTypeId());
        parameters.put(EVENT_IS_DRAFT, model.getIsDraft() ? 1 : 0);
        parameters.put(EVENT_FOLDER_ID, model.getFolderId());
        parameters.put(EVENT_IMAGE_FILEPATH, model.getImageFilepath());
        try {
            id = simpleJdbcInsert.executeAndReturnKey(parameters).intValue();
            model.setEventId(id);
        } catch (DataAccessException e) {
            log.error("Query fails by insert event with name '{}' by owner with id '{}'", model.getName(), model.getOwnerId(),e);
            throw new DatabaseWorkException(env.getProperty(EXCEPTION_DATABASE_WORK));
        }
            log.debug("Event with name '{}' by owner with id '{}' was inserted with id '{}'", model.getName(), model.getOwnerId(), id);

        return model;
    }

    /**
     * create event with this method, not insert
     *
     * @param model  event
     * @param userId owner id
     * @return created event with id
     */
    @Override
    public Event createEvent(Event model, String userId) {
        log.debug("Try to create event with name '{}' by user with id '{}'", model.getName(), userId);
        Event event;

        event = insert(model);
        insertUserEvent(userId, model.getEventId(), OWNER_ID);

        return event;
    }

    @Override
    public Role getRole(String userId, int eventId) {
        log.debug("Try to get role for user with id '{}' for event with id '{}'", userId, eventId);
        Role role;

        try {
            String roleString = jdbcTemplate.queryForObject(
                    env.getProperty(GET_ROLE), new Object[]{userId, eventId},
                    String.class);

            role = Role.valueOf(roleString);
        } catch (EmptyResultDataAccessException e) {
            log.error("Role was not found by eventId '{}'", eventId,e);
            role = Role.NULL;
        } catch (DataAccessException e) {
            log.error("Query fails by get role for user with id '{}' for event with id '{}'", userId, eventId,e);
            throw new DatabaseWorkException(env.getProperty(EXCEPTION_DATABASE_WORK));
        }

        log.debug("Role for user with id '{}' for event with id '{}' is ", userId, eventId, role.toString());
        return role;
    }

    private void insertUserEvent(String userId, int eventId, int roleId) {
        log.debug("Try to insert user event with user id '{}', event id '{}', role id '{}'", userId, eventId, roleId);
        int result;
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate.getDataSource())
                .withTableName(TABLE_USER_EVENT);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put(USER_EVENT_USER_ID, userId);
        parameters.put(USER_EVENT_EVENT_ID, eventId);
        parameters.put(USER_EVENT_ROLE_ID, roleId);

        try {
            result = simpleJdbcInsert.execute(parameters);
        } catch (DataAccessException e) {
            log.error("Query fails by insert user event with user id '{}', event id '{}', role id '{}'", userId, eventId, roleId,e);
            throw new DatabaseWorkException(env.getProperty(EXCEPTION_DATABASE_WORK));
        }
        if (result == 0) {
            log.error("Insert user event with user id '{}', event id '{}', role id '{}' not successful", userId, eventId, roleId);
            throw new UpdateException(env.getProperty(EXCEPTION_UPDATE));
        } else {
            log.debug("Insert user event with user id '{}', event id '{}', role id '{}' successful", userId, eventId, roleId);
        }
    }

    public void addParticipant(String ownerId, int participantId, int eventId) {
        log.debug("Try to insert participant to event with participant id '{}', event id '{}', owner id '{}'", participantId, eventId, ownerId);
        int result;

        try {
            result = jdbcTemplate.update(env.getProperty(EVENT_ADD_PARTICIPANT), participantId, PARTICIPANT_ID, eventId, ownerId, OWNER_ID);
        } catch (DataAccessException e) {
            log.error("Query fails by insert user event with participant id '{}', event id '{}', owner id '{}'", participantId, eventId, ownerId,e);
            throw new DatabaseWorkException(env.getProperty(EXCEPTION_DATABASE_WORK));
        }

        if (result == 0) {
            log.error("Insert user event with participant id '{}', event id '{}', owner id '{}' not successful", participantId, eventId, ownerId);
            throw new UpdateException(env.getProperty(EXCEPTION_UPDATE));
        } else {
            log.debug("Insert user event with participant id '{}', event id '{}', owner id '{}' successful", participantId, eventId, ownerId);
        }
    }

    @Override
    public Event deleteParticipants(String ownerId, Event model) {
        log.debug("Try to delete event participants with eventId '{}'", model.getEventId());
        int result;

        try {
            result = jdbcTemplate.update(env.getProperty(EVENT_DELETE_PARTICIPANTS), model.getEventId(), ownerId);
            model.setParticipants(null);
        } catch (DataAccessException e) {
            log.error("Query fails by delete participants of event with id '{}'", model.getEventId(),e);
            throw new DatabaseWorkException(env.getProperty(EXCEPTION_DATABASE_WORK));
        }

        log.debug("Participants of event with id '{}' was deleted successfully", model.getEventId());

        return model;
    }

    @Override
    public Event deleteMembers(Event event) {
        log.debug("Try to delete event members with eventId '{}'", event.getEventId());
        int result;

        try {
            result = jdbcTemplate.update(env.getProperty(EVENT_DELETE_MEMBERS), event.getEventId());
            event.setParticipants(null);
            event.setOwnerId("");
        } catch (DataAccessException e) {
            log.error("Query fails by delete participants of event with id '{}'", event.getEventId(),e);
            throw new DatabaseWorkException(env.getProperty(EXCEPTION_DATABASE_WORK));
        }

        if (result == 0) {
            throw new DeleteException(EXCEPTION_DELETE);
        }

        log.debug("Members of event with id '{}' was deleted successfully", event.getEventId());

        return event;
    }

    @Override
    public void deleteParticipant(String ownerId, int eventId, int participantId) {
        int result;

        log.debug("Try to delete event members with eventId '{}'", eventId);

        try {
            result = jdbcTemplate.update(env.getProperty(EVENT_DELETE_PARTICIPANT), participantId, eventId, ownerId);
        } catch (DataAccessException e) {
            log.error("Query fails by delete participants of event with id '{}'", eventId,e);
            throw new DatabaseWorkException(env.getProperty(EXCEPTION_DATABASE_WORK));
        }

        if (result == 0) {
            throw new DeleteException(EXCEPTION_DELETE);
        }

        log.debug("Members of event with id '{}' was deleted successfully", eventId);

    }

    @Override
    public List<Event> getPeriodEvents(String userId, String startDate, String endDate) {
        List<Event> events;
        log.debug("Try to find list of events by user between dates with id '{}' and dates '{}' '{}'",
                userId, startDate, endDate);
        try {
            events = jdbcTemplate.query(env.getProperty(EVENT_GET_IN_PERIOD),
                    new Object[]{userId, startDate, endDate}, new EventRowMapper());
        } catch (EmptyResultDataAccessException e) {
            log.error("Events was not found by userId '{}'", userId,e);
            throw new EntityNotFoundException(String.format(env.getProperty(EXCEPTION_ENTITY_NOT_FOUND),"Events", "userId", userId));
        } catch (DataAccessException e) {
            log.error("Query fails by finding event by user with id '{}'", userId,e);
            throw new DatabaseWorkException(env.getProperty(EXCEPTION_DATABASE_WORK));
        }

        log.debug("Events between dates for user with id '{}' counted '{}'", userId, events.size());
        return events;
    }

    @Override
    public List<Event> getPeriodEventsAllUsers(String startDate, String endDate) {
        List<Event> events;

        log.debug("Try to find list of events between '{}' and '{}'",
                startDate, endDate);

        try {
            events = jdbcTemplate.query(env.getProperty(EVENT_GET_IN_PERIOD_ALL_USERS),
                    new Object[]{startDate, endDate}, new EventRowMapper());
        } catch (EmptyResultDataAccessException e) {
            log.error("Events was not found",e);
            throw new EntityNotFoundException(String.format(env.getProperty(EXCEPTION_ENTITY_NOT_FOUND), "Events", "period", "one day"));
        } catch (DataAccessException e) {
            log.error("Query fails by finding event",e);
            throw new DatabaseWorkException(env.getProperty(EXCEPTION_DATABASE_WORK));
        }

        log.debug("Events between dates counted '{}'", events.size());

        return events;
    }

    @Override
    public List<Event> getAllPublic(String userId, String eventName) {
        List<Event> events;

        log.debug("Try to find list of public events by user with id '{}' and query '{}'", userId, eventName);

        try {
            String qString = '%' + eventName + '%';
            events = jdbcTemplate.query(env.getProperty(EVENT_GET_ALL_PUBLIC),
                    new Object[]{userId, qString}, new EventRowMapper());
        } catch (EmptyResultDataAccessException e) {
            log.error("Events were not found by userId '{}'", userId,e);
            throw new EntityNotFoundException(String.format(env.getProperty(EXCEPTION_ENTITY_NOT_FOUND),"Events", "userId", userId));
        } catch (DataAccessException e) {
            log.error("Query fails by finding public events by user with id '{}'", userId,e);
            throw new DatabaseWorkException(env.getProperty(EXCEPTION_DATABASE_WORK));
        }

        log.debug("Public events for user with id '{}' counted '{}'", userId, events.size());

        return events;
    }

    @Override
    public Event update(Event model) {
        log.debug("Try to update event with id '{}'", model.getEventId());

        int result;

        try {
            result = jdbcTemplate.update(env.getProperty(EVENT_UPDATE),
                    model.getName(), model.getEventDate(), model.getDescription(), model.getPeriodicityId(),
                    model.getPlace(), model.getEventTypeId(), model.getIsDraft() ? 1 : 0, model.getFolderId(), model.getImageFilepath(), model.getEventId());
        } catch (DataAccessException e) {
            log.error("Query fails by update event with id '{}'", model.getEventId(),e);
            throw new DatabaseWorkException(env.getProperty(EXCEPTION_DATABASE_WORK));
        }

        if (result == 0) {
            log.debug("Update event with id '{}' not successful", model.getEventId());
            throw new UpdateException(env.getProperty(EXCEPTION_UPDATE));
        } else {
            log.debug("Update event with id '{}' successful", model.getEventId());
        }

        return model;
    }

    @Override
    public Event delete(Event model) {
        log.debug("Try to delete event with id '{}'", model.getEventId());
        int result;
        try {
            result = jdbcTemplate.update(env.getProperty(EVENT_DELETE), model.getEventId());
        } catch (DataAccessException e) {
            log.error("Query fails by delete event with id '{}'", model.getEventId(),e);
            throw new DatabaseWorkException(env.getProperty(EXCEPTION_DATABASE_WORK));
        }

        if (result == 0) {
            log.debug("Event with id '{}' was not deleted successful", model.getEventId());
            throw new DeleteException(EXCEPTION_DELETE);
        } else {
            log.debug("Event with id '{}' was deleted successful", model.getEventId());
        }

        return model;
    }

    @Override
    public List<Event> findByFolderId(String userId, int folderId) {
        List<Event> events;
        log.debug("Try to find events with folder id '{}' and owner id '{}'", folderId);
        try {
            events = jdbcTemplate.query(env.getProperty(EVENT_FIND_BY_FOLDER_ID),
                    new Object[]{folderId, userId}, new EventRowMapper());
        } catch (EmptyResultDataAccessException e) {
            log.error("Events was not found by  folderId '{}'", folderId,e);
            throw new EntityNotFoundException(String.format(env.getProperty(EXCEPTION_ENTITY_NOT_FOUND), "Events", "folderId", folderId));
        } catch (DataAccessException e) {
            log.error("Query fails by find event by folder id '{}'", folderId, e);
            throw new DatabaseWorkException(env.getProperty(EXCEPTION_DATABASE_WORK));
        }

        log.debug("Events was found with folder id '{}' and counted '{}' pcs", folderId, events.size());

        return events;
    }

    @Override
    public List<Event> getDrafts(String userId, int folderId) {
        log.debug("Try to get drafts with folder id '{}'", folderId);
        List<Event> events;

        try {
            events = jdbcTemplate.query(env.getProperty(EVENT_GET_DRAFTS),
                    new Object[]{folderId, userId}, new EventRowMapper());
        } catch (DataAccessException e) {
            log.error("Query fails by getting drafts with folder id '{}'", folderId, e);
            throw new DatabaseWorkException(env.getProperty(EXCEPTION_DATABASE_WORK));
        }

        log.debug("Drafts with folder id '{}' were founded counted '{}' pcs", folderId, events.size());
        return events;
    }

    @Override
    public List<Event> findByType(String userId, String eventType, int folderId) {
        List<Event> events;

        log.debug("Try to find events with type '{}' with folderId '{}'", eventType, folderId);

        try {
            events = jdbcTemplate.query(env.getProperty(EVENT_FIND_BY_TYPE_IN_FOLDER),
                    new Object[]{eventType, folderId, userId}, new EventRowMapper());

        } catch (DataAccessException e) {
            log.error("Query fails by finding events with type '{}' with folderId '{}'", eventType, folderId, e);
            throw new DatabaseWorkException(env.getProperty(EXCEPTION_DATABASE_WORK));
        }

        log.debug("Events were found with type '{}' with folderId '{}' counted '{}' pcs", eventType, folderId, events.size());

        return events;
    }

    @Override
    public List<User> getParticipants(Event event) {
        log.debug("Try to get participants for event with id '{}'", event.getEventId());
        List<String> participants;

        try {
            participants = jdbcTemplate.queryForList(env.getProperty(EVENT_GET_PARTICIPANTS),
                    new Object[]{event.getEventId()}, String.class);
        } catch (DataAccessException e) {
            log.error("Query fails by getting participants for event with id '{}'", event.getEventId(), e);
            throw new DatabaseWorkException(env.getProperty(EXCEPTION_DATABASE_WORK));
        }
        List<User> users= new ArrayList<User>();
        for (String participant :participants){
            User tempUser = new User();
            tempUser.setLogin(participant);
            users.add(tempUser);
        }
        log.debug("Participants for event with id '{}' found and counted '{}'", event.getEventId(), participants.size());

        return users;

    }
}
