package com.epam.spring.advanced.homework.soap;

import com.epam.spring.advanced.homework.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

/**
 * @author Denes_Toth
 */
@Endpoint
public class SomeService {

    private static final String NAMESPACE_URI = "";

    @Autowired
    private UserRepository userRepository;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getSomeRequest")
    public SomeResponse getSome(@RequestPayload GetSomeRequest request) {
        SomeResponse response = new SomeResponse();
        response.setSomething(userRepository.getAll());
        return response;
    }
}
