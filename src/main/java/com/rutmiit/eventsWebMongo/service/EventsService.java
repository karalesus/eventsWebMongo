package com.rutmiit.eventsWebMongo.service;

import com.rutmiit.eventsWebMongo.model.EventDTO;
import com.rutmiit.eventsWebMongo.model.Events;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EventsService  {
    long countEvents();
    Events createEvent(EventDTO eventDTO);
    EventDTO getEvent(String id);
    void deleteEvent(String id);
    void deleteAllEvents();
    void saveAllEvents(List<Events> events);
    Page<EventDTO> getEvents(Pageable pageable);
    List<Events> getAllEvents();
    Page<EventDTO> findAfterDate(String dateAfter, Pageable pageable);
    Page<Events> findEventsMatchingAll(Events probe, Pageable pageable);
}
