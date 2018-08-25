package com.pccw.assessment.controller;

import com.pccw.assessment.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.mockito.BDDMockito.given;


@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserController userController;

    @Test
    public void testListUsingGET_1() throws Exception {
//        User aaaUser = new User();
//        aaaUser.setName("aaaName");
//        aaaUser.setEmail("aaaEmail@aaa.aaa");
//        aaaUser.setPassword("aaaPassword");
//        aaaUser.setUsername("aaaUsername");
//
//        List<User> allUsers = singletonList(aaaUser);
//
//        given(userController.listUsingGET_1(Matchers.any(Pageable.class))).willReturn(allUsers);
//
//        mvc.perform(get(VERSION + ARRIVAL + "all")
//                .with(user("blaze").password("Q1w2e3r4"))
//                .contentType(APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[0].city", is(arrival.getCity())));
    }
}
