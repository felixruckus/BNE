package com.pccw.assessment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import com.pccw.assessment.model.CreateUserRequest;
import com.pccw.assessment.model.UpdateUserRequest;
import com.pccw.assessment.model.User;
import com.pccw.assessment.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AssessmentApplicationTests {

    @Autowired
    private WebApplicationContext context;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private UserRepository repository;
    @LocalServerPort
    private int port;
    private MockMvc mockMvc;

    @Before
    public void setupMockMvc() {
        mockMvc = webAppContextSetup(context).build();
    }

    @Test
    public void contextLoads() {
        assertEquals(1, repository.count());
    }

    @Test
    @Sql({ "delete_test-data.sql", "insert_test-data.sql"})
    public void testListUsingGET_1() {
        ResponseEntity<List<User>> response = restTemplate.exchange(
                "http://localhost:" + port + "/users",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<User>>() {
                });
        List<User> users = response.getBody();
        assertEquals(10, users.size());
    }

    @Test
    @Sql({ "delete_test-data.sql", "insert_test-data.sql"})
    public void testListUsingGET_1_pageSize() {
        ResponseEntity<List<User>> response = restTemplate.exchange(
                "http://localhost:" + port + "/users?page=3&size=3",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<User>>() {
                });
        List<User> users = response.getBody();
        assertEquals(1, users.size());
        assertEquals("960c3778-8ad6-4a90-a23e-b56338e1dc32", users.get(0).getId());
        assertEquals("999@pccw.com", users.get(0).getEmail());
        assertEquals("999Name", users.get(0).getName());
        assertEquals("password@999", users.get(0).getPassword());
        assertEquals("999Username", users.get(0).getUsername());

    }

    @Test
    @Sql({ "delete_test-data.sql" })
    public void testCreateUsingPUT() {
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setEmail("ddd@ddd.com");
        createUserRequest.setName("dddName");
        createUserRequest.setPassword("password@ddd");
        createUserRequest.setUsername("dddUsername");
        HttpEntity<CreateUserRequest> entity = new HttpEntity<>(createUserRequest);
        ResponseEntity<User> response = restTemplate.exchange("http://localhost:" + port + "/users", HttpMethod.PUT, entity, User.class);

        User user = response.getBody();
        assertEquals(createUserRequest.getEmail(), user.getEmail());
        assertEquals(createUserRequest.getName(), user.getName());
        assertEquals(createUserRequest.getPassword(), user.getPassword());
        assertEquals(createUserRequest.getUsername(), user.getUsername());
    }

    @Test
    @Sql({ "delete_test-data.sql", "insert_test-single-data.sql" })
    public void testGetUsingGET() {
        User user =
                restTemplate.getForObject("http://localhost:" +
                        port + "/users/4116aa7e-ca4d-42ac-a7e0-b56bcfe1f191", User.class);
        assertNotNull(user);
        assertEquals("4116aa7e-ca4d-42ac-a7e0-b56bcfe1f191", user.getId());
        assertEquals("fff@pccw.com", user.getEmail());
        assertEquals("fffName", user.getName());
        assertEquals("password@fff", user.getPassword());
        assertEquals("fffUsername", user.getUsername());
    }

    @Test
    @Sql({ "delete_test-data.sql", "insert_test-single-data.sql" })
    public void testUpdateUsingPOST_1() {
        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        updateUserRequest.setEmail("ggg@pccw.com");
        updateUserRequest.setName("gggName");
        updateUserRequest.setPassword("password@ggg");
        updateUserRequest.setUsername("gggUsername");
        HttpEntity<UpdateUserRequest> entity = new HttpEntity<>(updateUserRequest);

        ResponseEntity<User> response = restTemplate.exchange("http://localhost:" + port + "/users/4116aa7e-ca4d-42ac-a7e0-b56bcfe1f191", HttpMethod.POST, entity, User.class);

        User user = response.getBody();
        assertEquals("4116aa7e-ca4d-42ac-a7e0-b56bcfe1f191", user.getId());
        assertEquals(updateUserRequest.getEmail(), user.getEmail());
        assertEquals(updateUserRequest.getName(), user.getName());
        assertEquals(updateUserRequest.getPassword(), user.getPassword());
        assertEquals(updateUserRequest.getUsername(), user.getUsername());

    }

    @Test
    @Sql({ "delete_test-data.sql", "insert_test-single-data.sql" })
    public void testDeleteUsingDELETE() {
        ResponseEntity<User> response = restTemplate.exchange("http://localhost:" + port + "/users/4116aa7e-ca4d-42ac-a7e0-b56bcfe1f191", HttpMethod.DELETE, null, User.class);
        User user = response.getBody();
        int size = repository.findAll().size();

        assertEquals(0, size);
        assertEquals("4116aa7e-ca4d-42ac-a7e0-b56bcfe1f191", user.getId());
        assertEquals("fff@pccw.com", user.getEmail());
        assertEquals("fffName", user.getName());
        assertEquals("password@fff", user.getPassword());
        assertEquals("fffUsername", user.getUsername());


    }
}
