package com.rutmiit.eventsWebMongo.service;

import com.rutmiit.eventsWebMongo.model.EventDTO;
import com.rutmiit.eventsWebMongo.model.EventMapper;
import com.rutmiit.eventsWebMongo.model.Events;
import com.rutmiit.eventsWebMongo.repo.EventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventsServiceImpl implements EventsService {

    private final EventsRepository eventsRepository;
    private final EventMapper eventMapper = new EventMapper();

    @Autowired
    public EventsServiceImpl(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public long countEvents() {
        return eventsRepository.count();
    }

    @Override
    public Events createEvent(EventDTO eventDTO) {
        Events event = eventMapper.convertToEvent(eventDTO);
        eventsRepository.save(event);
        return event;
    }

    @Override
    public EventDTO getEvent(String id) {
        EventDTO eventDTO = eventMapper.convertToEventDTO(eventsRepository.findById(id).orElseThrow(() -> new RuntimeException("Мероприятие с id " + id + "  не найдено")));
        return eventDTO;
    }

    @Override
    public void deleteEvent(String id) {
        eventsRepository.deleteById(id);
    }

    @Override
    public void deleteAllEvents() {
        eventsRepository.deleteAll();
    }

    @Override
    public void saveAllEvents(List<Events> events) {
        eventsRepository.saveAll(events);
    }

    @Override
    public Page<EventDTO> getEvents(Pageable pageable) {
        Page<Events> eventPage = eventsRepository.findAll(pageable);
        List<EventDTO> eventDTOs = eventMapper.convertToEventDTO(eventPage.getContent());
        return new PageImpl<>(eventDTOs, pageable, eventPage.getTotalElements());
    }

    @Override
    public List<Events> getAllEvents() {
        return eventsRepository.findAll();
    }

    @Override
    public Page<EventDTO> findAfterDate(String afterDate, Pageable pageable) {
        LocalDateTime dateTime = LocalDateTime.parse(afterDate);
        Page<Events> events = eventsRepository.findByDateAfter(dateTime, pageable);
        List<EventDTO> eventDTOs = eventMapper.convertToEventDTO(events.getContent());
        return new PageImpl<>(eventDTOs, pageable, events.getTotalElements());
    }

    @Override
    public Page<Events> findEventsMatchingAll(Events probe, Pageable pageable) {
        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAll();
        Page<Events> events = eventsRepository.findAll(Example.of(probe, exampleMatcher), pageable);
        return events;
    }

}
