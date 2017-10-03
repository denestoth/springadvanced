package com.epam.spring.advanced.homework.controllers;

import com.epam.spring.advanced.homework.domain.User;
import com.epam.spring.advanced.homework.service.UserService;
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
public class UserUploadController {

    @Autowired
    UserService userService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @RequestMapping(value = "/api/user/upload", method = RequestMethod.GET)
    public String uploadUsers(Model model) {
        return "userUpload";
    }

    @RequestMapping(value = "/api/user/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String doUpload(@RequestParam("file") MultipartFile file,
                           Model model) throws IOException {

        List<User> users = objectMapper.readValue(file.getBytes(), new TypeReference<List<User>>() {
        });

        for (User user : users) {
            userService.addUser(user);
        }

        return "index";
    }

}
