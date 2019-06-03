package com.kpi.project.third.service.dao;

import com.kpi.project.third.service.entity.Event;
import com.kpi.project.third.service.entity.Role;
import com.kpi.project.third.service.entity.User;

import java.util.List;


public interface EventDao extends Dao<Event> {

    List<Event> findByUserId(String userId);

    List<Event> findByFolderId(String userId, int folderId);

    List<Event> getDrafts(String userId, int folderId);

    List<Event> findByType(String userId, String eventType, int folderId);

    List<User> getParticipants(Event event);

    Role getRole(String userId, int eventId);

    Event createEvent(Event model, String userId);

    void addParticipant(String ownerId, String participantId, int eventId);

    List<Event> getPeriodEvents(String userId, String startDate, String endDate);

    List<Event> getPeriodEventsAllUsers(String startDate, String endDate);

    List<Event> getAllPublic(String userId, String eventName);

    Event deleteParticipants(String ownerId, Event event);

    Event deleteMembers(Event event);

    void deleteParticipant(String ownerId, int eventId, String participantId);

}
