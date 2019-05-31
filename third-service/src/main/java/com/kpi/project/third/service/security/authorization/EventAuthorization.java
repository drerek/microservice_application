package com.kpi.project.third.service.security.authorization;

import com.kpi.project.third.service.entity.Event;
import com.kpi.project.third.service.security.AuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventAuthorization extends AbstractAuthorization {

    @Autowired
    public EventAuthorization(AuthenticationFacade authenticationFacade) {
        super(authenticationFacade);
    }

    public boolean isEventCorrect(int userId, Event event) {
        return event != null && isUserCorrect(userId) && userId == event.getOwnerId();

    }

    public boolean isEventCorrect(int userId, int eventId, Event event) {
        return isEventCorrect(userId, event) && eventId == event.getEventId();
    }

}
