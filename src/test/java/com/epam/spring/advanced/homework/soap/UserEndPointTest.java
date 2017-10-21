package com.epam.spring.advanced.homework.soap;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.xml.transform.StringSource;

import javax.xml.transform.Source;

import static org.springframework.ws.test.server.RequestCreators.withPayload;

/**
 * @author Denes Toth
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:*/WEB-INF/spring-ws-servlet.xml")
public class UserEndPointTest {

    @Autowired
    private WebApplicationContext context;

    private MockWebServiceClient client;

    @Before
    public void createClient() {
        client = MockWebServiceClient.createClient(context);
    }

    @Test
    public void testUserEndPoint() {
        Source requestPayload = new StringSource("");

        Object result = client.sendRequest(withPayload(requestPayload));
    }

}
