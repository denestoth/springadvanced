package com.epam.spring.advanced.homework.soap;

import com.epam.spring.advanced.homework.domain.User;
import com.epam.spring.advanced.homework.repository.UserRepository;
import com.epam.spring.advanced.homework.service.UserService;
import generated.GetUserRequest;
import generated.GetUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Denes Toth
 */
@Endpoint
public class UserEndPoint {

    public static final String NAMESPACE_URI = "http://something.com/ws/";

    @Autowired
    private UserService userService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserRequest")
    public GetUserResponse getUserByEmail(@RequestPayload GetUserRequest request) {
        User user = userService.getUserByEmail(request.getEmail());

        generated.User responseUser = new generated.User();

        if (user != null) {
            responseUser.setId(user.getId());
            responseUser.setEmail(user.getEmail());
            responseUser.setFirstName(user.getFirstName());
            responseUser.setLastName(user.getLastName());
        }

        GetUserResponse response = new GetUserResponse();
        response.setUser(responseUser);
        return response;
    }
}
