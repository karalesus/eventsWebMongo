package com.rutmiit.eventsWebMongo.controller;

import com.rutmiit.eventsWebMongo.model.EventDTO;
import com.rutmiit.eventsWebMongo.model.Events;
import com.rutmiit.eventsWebMongo.service.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class EventsController {

    @Autowired
    private EventsService eventsService;

    @GetMapping("/events")
    public Page<EventDTO> getEventsPage(
            @RequestParam(defaultValue = "0") Integer offset,
            @RequestParam(defaultValue = "10") Integer limit
    ) {
        return eventsService.getEvents(PageRequest.of(offset, limit));
    }

    @GetMapping("/events/{id}")
    public EventDTO getEvents(@PathVariable String id) {
        return eventsService.getEvent(id);
    }

    @PostMapping("/events")
    public Events postEvent(@RequestBody EventDTO eventDTO) {
        return eventsService.createEvent(eventDTO);
    }

    @DeleteMapping("/events/{id}")
    public String deleteEvent(@PathVariable String id) {
        eventsService.deleteEvent(id);
        return "Мероприятие успешно удалено, id: " + id;
    }

    @PutMapping("/events/{id}")
    public Events putEvent(@RequestBody EventDTO newEvent, @PathVariable String id) {
        newEvent.setId(id);
        return eventsService.createEvent(newEvent);
    }

    @GetMapping("/events/after/{date}")
    public Page<EventDTO> findAfterDate(@PathVariable String date, @RequestParam(defaultValue = "0") Integer offset,              // Номер страницы
                                        @RequestParam(defaultValue = "10") Integer limit) {
        return eventsService.findAfterDate(date, PageRequest.of(offset, limit));
    }

}
