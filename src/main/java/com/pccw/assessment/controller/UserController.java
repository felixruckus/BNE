package com.pccw.assessment.controller;

import com.pccw.assessment.model.CreateUserRequest;
import com.pccw.assessment.model.UpdateUserRequest;
import com.pccw.assessment.model.User;
import com.pccw.assessment.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;


@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;
    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "users", method = RequestMethod.GET)
    public List<User> listUsingGET_1(Pageable pageable) {
        return this.userRepository.findAll(pageable).getContent();
    }

    @RequestMapping(value = "users", method = RequestMethod.PUT)
    public User createUsingPUT(@Valid @RequestBody CreateUserRequest createUserRequest) {
        User user = new User();
        BeanUtils.copyProperties(createUserRequest, user);
        return this.userRepository.saveAndFlush(user);
    }

    @RequestMapping(value = "users/{id}", method = RequestMethod.GET)
    public User getUsingGET(@PathVariable String id) {
        return this.userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @RequestMapping(value="users/{id}", method = RequestMethod.POST)
    public User updateUsingPOST_1(@PathVariable String id, @Valid @RequestBody UpdateUserRequest updateUserRequest)
    {
        User existingUser = this.userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        BeanUtils.copyProperties(updateUserRequest, existingUser);
        return this.userRepository.saveAndFlush(existingUser);

    }

    @RequestMapping(value = "users/{id}", method = RequestMethod.DELETE)
    public User deleteUsingDELETE(@PathVariable String id) {
        User user = this.userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        this.userRepository.delete(user);
        return user;
    }
}


