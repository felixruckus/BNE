package com.pccw.assessment;

import static org.assertj.core.api.Assertions.assertThat;

import com.pccw.assessment.controller.UserController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AssessmentApplicationTests {

    @Autowired
    private UserController userController;

    @Test
    public void contextLoads() {
        assertThat(userController).isNotNull();
    }

}
