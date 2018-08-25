package com.pccw.assessment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import com.pccw.assessment.model.CreateUserRequest;
import com.pccw.assessment.model.User;
import com.pccw.assessment.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

    @LocalServerPort
    private int port;

//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @Test
//    public void greetingShouldReturnDefaultMessage() {
//        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/users",
//                String.class)).contains("Hello World");
//    }

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testCreateUsingPUT() {
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setEmail("aaa@aaa.com");
        createUserRequest.setName("aaaName");
        createUserRequest.setUsername("aaaUsername");
        createUserRequest.setPassword("aaaPassword");

        User user = new User();
        BeanUtils.copyProperties(createUserRequest, user);

        User createdUser = userRepository.saveAndFlush(user);
        assertEquals(createdUser, user);
    }

//    @Test
//    public void testGetUser() {
//        ResponseEntity<String> result = service.getUser("1");
//        System.out.println("---------------------------");
//        System.out.println(result);
//        assertEquals(result.getStatusCode(), HttpStatus.OK);
//    }
//
//    @Test
//    public void testUpdateUser() {
//        UpdateUserRequest user1 = new UpdateUserRequest();
//        user1.setEmail("akarshmaidargi@yahoo.com");
//        user1.setName("akarsh maidargi");
//        user1.setUsername("akarshm");
//        user1.setPassword("newpassword");
//        ResponseEntity<String> result = service.updateUser(user1);
//        System.out.println("---------------------------");
//        System.out.println(result);
//        assertEquals(result.getStatusCode(), HttpStatus.OK);
//    }
//
//
//    @Test
//    public void testGetAllUsers() {
//        ResponseEntity<String> result = service.getAllUsers();
//        System.out.println("---------------------------");
//        System.out.println(result);
//        assertEquals(result.getStatusCode(), HttpStatus.OK);
//    }
//
//    @Test
//    public void testDeleteUsers() {
//        ResponseEntity<String> result = service.deleteUser("1");
//        System.out.println("---------------------------");
//        System.out.println(result);
//        assertEquals(result.getStatusCode(), HttpStatus.OK);
//    }
}