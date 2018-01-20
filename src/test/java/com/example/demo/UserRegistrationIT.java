package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("it")
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
public class UserRegistrationIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void submitRegistrationAccountAlreadyExist() throws Exception{
        this.mockMvc.perform(
                post("/registration").
                        with(csrf())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("firstName", "Janek")
                .param("lastName", "Janek")
                .param("email", "Janek@gmail.com")
                .param("confirmEmail","Janek@gmail.com")
                .param("password","password")
                .param("confirmPassword","password")
                .param("terms","on")
        )
                .andExpect(model().hasErrors())
                .andExpect(model().attributeHasFieldErrors("user","email"))
                .andExpect(status().isOk());
    }

    @Test
    public void submitRegistrationPasswordDoesNotMatch() throws Exception{
        this.mockMvc.perform(
                post("/registration").
                        with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("firstName", "Janek")
                        .param("lastName", "Janek")
                        .param("email", "NOWYJanek@gmail.com")
                        .param("confirmEmail","NOWYJanek@gmail.com")
                        .param("password","password")
                        .param("confirmPassword","invalid")
                        .param("terms","on")
        )
                .andExpect(model().hasErrors())
                .andExpect(model().attributeHasFieldErrors("user"))
                .andExpect(status().isOk());
    }

    @Test
    public void submitRegistrationEmailDoesNotMatch() throws Exception{
        this.mockMvc.perform(
                post("/registration").
                        with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("firstName", "Janek")
                        .param("lastName", "Janek")
                        .param("email", "NOWYJanek@gmail.com")
                        .param("confirmEmail","INNYJanek@gmail.com")
                        .param("password","password")
                        .param("confirmPassword","password")
                        .param("terms","on")
        )
                .andExpect(model().hasErrors())
                .andExpect(model().attributeHasFieldErrors("user"))
                .andExpect(status().isOk());
    }

    @Test
    public void submitRegistrationSucces() throws Exception{
        this.mockMvc.perform(
                post("/registration").
                        with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("firstName", "Janek")
                        .param("lastName", "Janek")
                        .param("email", "InnyJanek@gmail.com")
                        .param("confirmEmail","InnyJanek@gmail.com")
                        .param("password","password")
                        .param("confirmPassword","password")
                        .param("terms","on")
        )
                .andExpect(redirectedUrl("/login"))
                .andExpect(status().is3xxRedirection());
    }
}
