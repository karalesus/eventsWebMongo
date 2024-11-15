package com.rutmiit.eventsWebMongo.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EventMapper {

    public EventDTO convertToEventDTO(Events event) {
        List<String> hostNames = event.getHost().stream()
                .map(Events.Host::getName)
                .collect(Collectors.toList());
        List<String> hostEmails = event.getHost().stream()
                .map(Events.Host::getEmail)
                .collect(Collectors.toList());

        return new EventDTO(
                event.getId(),
                event.getTitle(),
                event.getCategory(),
                String.valueOf(event.getDate()),
                event.getLocation(),
                String.join(", ", hostNames),
                String.join(", ", hostEmails),
                event.getPrice()
        );
    }

    public List<EventDTO> convertToEventDTO(List<Events> events) {
        return events.stream()
                .map(event -> new EventDTO(
                        event.getId(),
                        event.getTitle(),
                        event.getCategory(),
                        String.valueOf(event.getDate()),
                        event.getLocation(),
                        event.getHost().stream().map(Events.Host::getName).collect(Collectors.joining(", ")),
                        event.getHost().stream().map(Events.Host::getEmail).collect(Collectors.joining(", ")),
                        event.getPrice()
                ))
                .collect(Collectors.toList());
    }

    public Events convertToEvent(EventDTO eventDTO) {
        List<String> hostNames = Arrays.asList(eventDTO.getHostNames().split(", "));
        List<String> hostEmails = Arrays.asList(eventDTO.getHostEmails().split(", "));

        if (hostNames.size() != hostEmails.size()) {
            throw new IllegalArgumentException("Количество имен и email хостов не совпадает");
        }

        List<Events.Host> hosts = new ArrayList<>();
        for (int i = 0; i < hostNames.size(); i++) {
            hosts.add(new Events.Host(hostNames.get(i), hostEmails.get(i)));
        }
        return new Events(eventDTO.getId(), eventDTO.getTitle(), eventDTO.getCategory(), LocalDateTime.parse(eventDTO.getDate()), eventDTO.getLocation(), hosts, eventDTO.getPrice());
    }
}
