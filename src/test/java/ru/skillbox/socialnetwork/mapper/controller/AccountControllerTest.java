package ru.skillbox.socialnetwork.mapper.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import ru.skillbox.socialnetwork.api.request.RegistrationApi;
import ru.skillbox.socialnetwork.api.request.SetPasswordApi;
import ru.skillbox.socialnetwork.controller.AccountController;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(AccountController.class)
public class AccountControllerTest {
    private static final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mvc;

    @Test
    @WithMockUser
    public void testRegistration() throws Exception {
        RegistrationApi registrationApi = new RegistrationApi();
        registrationApi.setEmail("email@test.com");
        registrationApi.setFirstName("A");
        registrationApi.setLastName("B");
        registrationApi.setPasswd1("1");
        registrationApi.setPasswd2("1");
        registrationApi.setCoda(1);

        String json = mapper.writeValueAsString(registrationApi);

        mvc.perform(post("/api/v1/account/registration")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .with(csrf())
                .content(json))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());


        registrationApi = new RegistrationApi();
        registrationApi.setEmail("email@test.com");
        registrationApi.setFirstName("A");
        registrationApi.setLastName("B");
        registrationApi.setPasswd1("1");
        registrationApi.setPasswd2("2");
        registrationApi.setCoda(1);

        json = mapper.writeValueAsString(registrationApi);

        mvc.perform(post("/api/v1/account/registration")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .with(csrf())
                .content(json))
                .andExpect(status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());


        registrationApi = new RegistrationApi();
        registrationApi.setEmail("test");
        registrationApi.setFirstName("A");
        registrationApi.setLastName("B");
        registrationApi.setPasswd1("1");
        registrationApi.setPasswd2("1");
        registrationApi.setCoda(1);

        json = mapper.writeValueAsString(registrationApi);

        mvc.perform(post("/api/v1/account/registration")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .with(csrf())
                .content(json))
                .andExpect(status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());

        registrationApi = new RegistrationApi();
        json = mapper.writeValueAsString(registrationApi);

        mvc.perform(post("/api/v1/account/registration")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .with(csrf())
                .content(json))
                .andExpect(status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    @WithMockUser
    public void testRecoveryPassword() throws Exception {
        mvc.perform(put("/api/v1/account/password/recovery")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .with(csrf())
                .param("email", "email@test.com"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

        mvc.perform(put("/api/v1/account/password/recovery")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .with(csrf())
                .param("email", "test"))
                .andExpect(status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @WithMockUser
    public void testSetPassword() throws Exception {
        SetPasswordApi passwordApi = new SetPasswordApi();
        passwordApi.setOld("1");
        passwordApi.setPasswdord("2");

        String json = mapper.writeValueAsString(passwordApi);

        mvc.perform(put("/api/v1/account/password/set")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .with(csrf())
                .content(json))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());


        passwordApi = new SetPasswordApi();
        passwordApi.setToken("1");
        passwordApi.setPasswdord("2");

        json = mapper.writeValueAsString(passwordApi);

        mvc.perform(put("/api/v1/account/password/set")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .with(csrf())
                .content(json))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());


        passwordApi = new SetPasswordApi();
        passwordApi.setPasswdord("1");
        json = mapper.writeValueAsString(passwordApi);

        mvc.perform(post("/api/v1/account/password/set")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .with(csrf())
                .content(json))
                .andExpect(status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @WithMockUser
    public void testSetEmail() throws Exception {
        mvc.perform(put("/api/v1/account/email")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .with(csrf())
                .param("email", "email@test.com"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

        mvc.perform(post("/api/v1/account/email")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .with(csrf())
                .param("email", "test"))
                .andExpect(status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @WithMockUser
    public void testNotification() throws Exception {
        mvc.perform(put("/api/v1/account/notification")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .with(csrf())
                .param("notification_type", "type")
                .param("enable", "true"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

        mvc.perform(post("/api/v1/account/notification")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .with(csrf())
                .param("notification_type", "")
                .param("enable", "true"))
                .andExpect(status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @WithMockUser
    public void testStatus() throws Exception {
        mvc.perform(put("/api/v1/account/status")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .with(csrf())
                .param("status", "online"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

        mvc.perform(put("/api/v1/account/status")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .with(csrf())
                .param("status", ""))
                .andExpect(status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }

}
