package com.epam.spring.advanced.homework.controllers;

import com.epam.spring.advanced.homework.domain.Event;
import com.epam.spring.advanced.homework.service.EventService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author Denes_Toth
 */
@Controller
public class EventUploadController {

    @Autowired
    EventService eventService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @RequestMapping(value = "/api/event/upload", method = RequestMethod.GET)
    public String uploadEvents(Model model) {
        return "eventUpload";
    }

    @RequestMapping(value = "/api/event/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String doUpload(@RequestParam("file") MultipartFile file,
                           Model model) throws IOException {

        List<Event> events = objectMapper.readValue(file.getBytes(), new TypeReference<List<Event>>() {
        });

        for (Event event : events) {
            eventService.addEvent(event);
        }

        return "index";
    }

}
