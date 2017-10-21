package com.epam.spring.advanced.homework.soap;

import com.epam.spring.advanced.homework.domain.Event;
import com.epam.spring.advanced.homework.repository.EventRepository;
import com.epam.spring.advanced.homework.repository.UserRepository;
import generated.GetEventRequest;
import generated.GetEventResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Denes_Toth
 */
@Endpoint
public class EventEndpoint {

    private static final String NAMESPACE_URI = "http://localhost:8080/denes-toth/";

    @Autowired
    private EventRepository eventRepository;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getEventRequest")
    public GetEventResponse getEventByName(@RequestPayload GetEventRequest request) {
        String name = request.getName();
        List<Event> events = eventRepository.getAll().stream().filter(event -> event.getName().equals(request.getName())).collect(Collectors.toList());
        Event event = events.isEmpty() ? null : events.get(0);

        generated.Event responseEvent = new generated.Event();

        if (event != null) {
            responseEvent.setId(event.getId());
            responseEvent.setBasePrice(event.getBasePrice());
            responseEvent.setName(event.getName());
            responseEvent.setTicketPrice(event.getTicketPrice());
        }

        GetEventResponse response = new GetEventResponse();
        response.setEvent(responseEvent);
        return response;
    }
}
