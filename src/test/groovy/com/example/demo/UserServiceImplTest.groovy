package com.example.demo

import com.example.demo.model.User
import com.example.demo.repository.UserRepository
import com.example.demo.service.UserServiceImpl
import com.example.demo.web.dto.UserRegistrationDto
import org.springframework.beans.factory.annotation.Autowired

class UserServiceImplTest extends MySpec {

    @Autowired
    UserRepository userRepository

    @Autowired
    UserServiceImpl userService;

    def setup() {

    }

    def "should find an user"() {
        when:
        def response = this.userService.findByEmail("TestUser@mail.com");
        then:
        assert 'TestUser@mail.com' == response.email;
    }

    def "should save and find user"(){
        when:
        def response = this.userRepository.save(User.newBuilder()
                .email("TestUser2@mail.com")
                .firstName("TestUser2")
                .lastName("TestUser2")
                .password("password")
                .build())
        then:
            this.userService.findByEmail("TestUser2@mail.com") != null

    }



}

