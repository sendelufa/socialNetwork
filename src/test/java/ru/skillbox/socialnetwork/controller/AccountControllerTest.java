package ru.skillbox.socialnetwork.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import ru.skillbox.socialnetwork.api.request.RegistrationApi;
import ru.skillbox.socialnetwork.api.request.SetPasswordApi;
import ru.skillbox.socialnetwork.service.MailSender;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTest {
    private static final ObjectMapper mapper = new ObjectMapper();

    private static final String email1 = "sidorovmaxim@mail.ru";//email пользователя для авторизации в тестах (кроме смены эмаил)
    private static final String email2 = "mihailovsergei@mail.ru";//для теста смены эмаил
    private static final String newEmail = "mihailovsergei2@mail.ru";//новый эмаил для смены эмаил
    private static final String emailRegistr = "newUser@mail.ru";//эмаил для теста регистрации

    @Autowired
    private AccountController controller;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MailSender mailSender;

    @Test
    public void correctRegistrationTest() throws Exception {
        RegistrationApi registrationApi = new RegistrationApi();
        registrationApi.setEmail(emailRegistr);
        registrationApi.setFirstName("A");
        registrationApi.setLastName("B");
        registrationApi.setPasswd1("1");
        registrationApi.setPasswd2("1");
        registrationApi.setCode(1);

        String json = mapper.writeValueAsString(registrationApi);

        mvc.perform(post("/api/v1/account/register")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .with(csrf())
            .content(json))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("error")))
            .andExpect(content().string(containsString("timestamp")))
            .andExpect(content().string(containsString("data")))
            .andExpect(content().string(containsString("message")))
            .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void failRegistrationTest() throws Exception {
        RegistrationApi registrationApi = new RegistrationApi();
        registrationApi.setEmail(emailRegistr);
        registrationApi.setFirstName("A");
        registrationApi.setLastName("B");
        registrationApi.setPasswd1("1");
        registrationApi.setPasswd2("2");
        registrationApi.setCode(1);

        String json = mapper.writeValueAsString(registrationApi);

        mvc.perform(post("/api/v1/account/register")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .with(csrf())
            .content(json))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(containsString("error")))
            .andExpect(content().string(containsString("error_description")))
            .andDo(MockMvcResultHandlers.print());

        registrationApi = new RegistrationApi();
        registrationApi.setEmail("email");
        registrationApi.setFirstName("A");
        registrationApi.setLastName("B");
        registrationApi.setPasswd1("1");
        registrationApi.setPasswd2("1");
        registrationApi.setCode(1);

        json = mapper.writeValueAsString(registrationApi);

        mvc.perform(post("/api/v1/account/register")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .with(csrf())
            .content(json))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(containsString("error")))
            .andExpect(content().string(containsString("error_description")))
            .andDo(MockMvcResultHandlers.print());

    }

    @WithUserDetails(email1)
    @Test
    public void correctRecoveryPasswordTest() throws Exception {
        mvc.perform(put("/api/v1/account/password/recovery")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .with(csrf())
            .content("{\"email\": \"" + email1 + "\"}"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("error")))
            .andExpect(content().string(containsString("timestamp")))
            .andExpect(content().string(containsString("data")))
            .andExpect(content().string(containsString("message")))
            .andDo(MockMvcResultHandlers.print());

        //Email не отправляем а проверяем была ли попытка отправить
        Mockito.verify(mailSender, Mockito.times(1))
            .send(
                ArgumentMatchers.eq(email1),
                ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString()
            );
    }

    @WithUserDetails(email1)
    @Test
    public void failRecoveryPasswordTest() throws Exception {
        mvc.perform(put("/api/v1/account/password/recovery")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .with(csrf())
            .content("{\"email\": \"private\"}"))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(containsString("error")))
            .andExpect(content().string(containsString("error_description")))
            .andDo(MockMvcResultHandlers.print());
    }

    @WithUserDetails(email1)
    @Test
    public void correctSetPasswordTest() throws Exception {
        SetPasswordApi passwordApi = new SetPasswordApi();
        passwordApi.setOld("94f18938");
        passwordApi.setPassword("123");

        String json = mapper.writeValueAsString(passwordApi);

        mvc.perform(put("/api/v1/account/password/set")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .with(csrf())
            .content(json))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("error")))
            .andExpect(content().string(containsString("timestamp")))
            .andExpect(content().string(containsString("data")))
            .andExpect(content().string(containsString("message")))
            .andDo(MockMvcResultHandlers.print());
    }

    @WithUserDetails(email1)
    @Test
    public void failSetPasswordTest() throws Exception {
        SetPasswordApi passwordApi = new SetPasswordApi();
        passwordApi.setPassword("");
        String json = mapper.writeValueAsString(passwordApi);

        mvc.perform(put("/api/v1/account/password/set")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .with(csrf())
            .content(json))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(containsString("error")))
            .andExpect(content().string(containsString("error_description")))
            .andDo(MockMvcResultHandlers.print());
    }

    @WithUserDetails(email2)
    @Test
    public void correctSetEmailTest() throws Exception {
        mvc.perform(put("/api/v1/account/email")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .with(csrf())
            .content("{\"email\": \"" + newEmail + "\"}"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("error")))
            .andExpect(content().string(containsString("timestamp")))
            .andExpect(content().string(containsString("data")))
            .andExpect(content().string(containsString("message")))
            .andDo(MockMvcResultHandlers.print());
    }

    @WithUserDetails(email1)
    @Test
    public void failSetEmailTest() throws Exception {
        mvc.perform(put("/api/v1/account/email")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .with(csrf())
            .content("{\"email\": \"newEmail\"}"))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(containsString("error")))
            .andExpect(content().string(containsString("error_description")))
            .andDo(MockMvcResultHandlers.print());
    }

    @WithUserDetails(email1)
    @Test
    public void correctNotificationTest() throws Exception {
        mvc.perform(put("/api/v1/account/notifications")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .with(csrf())
            .content("{\"notification_type\": \"POST\","
                + "\"enable\": true}"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("error")))
            .andExpect(content().string(containsString("timestamp")))
            .andExpect(content().string(containsString("data")))
            .andExpect(content().string(containsString("message")))
            .andDo(MockMvcResultHandlers.print());
    }

    @WithUserDetails(email1)
    @Test
    public void failNotificationTest() throws Exception {
        mvc.perform(put("/api/v1/account/notifications")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .with(csrf())
            .content("{\"notification_type\": \"\","
                + "\"enable\": \"\"}"))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(containsString("error")))
            .andExpect(content().string(containsString("error_description")))
            .andDo(MockMvcResultHandlers.print());
    }

    @WithUserDetails(email1)
    @Test
    public void correctStatusTest() throws Exception {
        mvc.perform(put("/api/v1/account/status")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .with(csrf())
            .content("{\"status\": \"online\"}"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("error")))
            .andExpect(content().string(containsString("timestamp")))
            .andExpect(content().string(containsString("data")))
            .andExpect(content().string(containsString("message")))
            .andDo(MockMvcResultHandlers.print());
    }

    @WithUserDetails(email1)
    @Test
    public void failStatusTest() throws Exception {
        mvc.perform(put("/api/v1/account/status")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .with(csrf())
            .content("{\"status\": \"\"}"))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(containsString("error")))
            .andExpect(content().string(containsString("error_description")))
            .andDo(MockMvcResultHandlers.print());
    }
}
