package com.epam.spring.advanced.homework.soap;

import com.epam.spring.advanced.homework.domain.User;
import com.epam.spring.advanced.homework.repository.UserRepository;
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

    private static final String NAMESPACE_URI = "http://localhost:8080/denes-toth/";

    @Autowired
    private UserRepository userRepository;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserRequest")
    public GetUserResponse getUserByEmail(@RequestPayload GetUserRequest request) {
        String email = request.getEmail();
        List<User> users = userRepository.getAll().stream().filter(user -> user.getEmail().equals(email)).collect(Collectors.toList());
        User user = users.isEmpty() ? null : users.get(0);

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
