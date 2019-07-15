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

    private final String PATH_ACC = "/api/v1/account";
    private static final String EMAIL_1 = "sidorovmaxim@mail.ru";//email пользователя для авторизации в тестах (кроме смены эмаил)
    private static final String EMAIL_2 = "mihailovsergei@mail.ru";//для теста смены эмаил
    private static final String NEW_EMAIL = "mihailovsergei2@mail.ru";//новый эмаил для смены эмаил
    private static final String EMAIL_REGISTR = "newUser@mail.ru";//эмаил для теста регистрации

    @Autowired
    private AccountController controller;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MailSender mailSender;

  @WithUserDetails(EMAIL_1)//Удалить когда регистрация будет доступна для неавторизованых
  @Test
    public void correctRegistrationTest() throws Exception {
        RegistrationApi registrationApi = new RegistrationApi();
        registrationApi.setEmail(EMAIL_REGISTR);
        registrationApi.setFirstName("A");
        registrationApi.setLastName("B");
        registrationApi.setPasswd1("1");
        registrationApi.setPasswd2("1");
        registrationApi.setCode(1);

        String json = mapper.writeValueAsString(registrationApi);

        mvc.perform(post(PATH_ACC + "/register")
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

  @WithUserDetails(EMAIL_1)//Удалить когда регистрация будет доступна для неавторизованых
  @Test
    public void failRegistrationTest() throws Exception {
        RegistrationApi registrationApi = new RegistrationApi();
        registrationApi.setEmail(EMAIL_REGISTR);
        registrationApi.setFirstName("A");
        registrationApi.setLastName("B");
        registrationApi.setPasswd1("1");
        registrationApi.setPasswd2("2");
        registrationApi.setCode(1);

        String json = mapper.writeValueAsString(registrationApi);

        mvc.perform(post(PATH_ACC + "/register")
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

        mvc.perform(post(PATH_ACC + "/register")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .with(csrf())
            .content(json))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(containsString("error")))
            .andExpect(content().string(containsString("error_description")))
            .andDo(MockMvcResultHandlers.print());

    }

    @WithUserDetails(EMAIL_1)//Удалить когда восстановление пароля будет доступно для неавторизованых
    @Test
    public void correctRecoveryPasswordTest() throws Exception {
        mvc.perform(put(PATH_ACC + "/password/recovery")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .with(csrf())
            .content("{\"email\": \"" + EMAIL_1 + "\"}"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("error")))
            .andExpect(content().string(containsString("timestamp")))
            .andExpect(content().string(containsString("data")))
            .andExpect(content().string(containsString("message")))
            .andDo(MockMvcResultHandlers.print());

        //Email не отправляем а проверяем была ли попытка отправить
        Mockito.verify(mailSender, Mockito.times(1))
            .send(
                ArgumentMatchers.eq(EMAIL_1),
                ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString()
            );
    }

    @WithUserDetails(EMAIL_1)//Удалить когда восстановление пароля будет доступно для неавторизованых
    @Test
    public void failRecoveryPasswordTest() throws Exception {
        mvc.perform(put(PATH_ACC + "/password/recovery")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .with(csrf())
            .content("{\"email\": \"private\"}"))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(containsString("error")))
            .andExpect(content().string(containsString("error_description")))
            .andDo(MockMvcResultHandlers.print());
    }

    @WithUserDetails(EMAIL_1)
    @Test
    public void correctSetPasswordTest() throws Exception {
        SetPasswordApi passwordApi = new SetPasswordApi();
        passwordApi.setOld("94f18938");
        passwordApi.setPassword("123");

        String json = mapper.writeValueAsString(passwordApi);

        mvc.perform(put(PATH_ACC + "/password/set")
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

    @WithUserDetails(EMAIL_1)
    @Test
    public void failSetPasswordTest() throws Exception {
        SetPasswordApi passwordApi = new SetPasswordApi();
        passwordApi.setPassword("");
        String json = mapper.writeValueAsString(passwordApi);

        mvc.perform(put(PATH_ACC + "/password/set")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .with(csrf())
            .content(json))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(containsString("error")))
            .andExpect(content().string(containsString("error_description")))
            .andDo(MockMvcResultHandlers.print());
    }

    @WithUserDetails(EMAIL_2)
    @Test
    public void correctSetEmailTest() throws Exception {
        mvc.perform(put(PATH_ACC + "/email")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .with(csrf())
            .content("{\"email\": \"" + NEW_EMAIL + "\"}"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("error")))
            .andExpect(content().string(containsString("timestamp")))
            .andExpect(content().string(containsString("data")))
            .andExpect(content().string(containsString("message")))
            .andDo(MockMvcResultHandlers.print());
    }

    @WithUserDetails(EMAIL_1)
    @Test
    public void failSetEmailTest() throws Exception {
        mvc.perform(put(PATH_ACC + "/email")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .with(csrf())
            .content("{\"email\": \"NEW_EMAIL\"}"))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(containsString("error")))
            .andExpect(content().string(containsString("error_description")))
            .andDo(MockMvcResultHandlers.print());
    }

    @WithUserDetails(EMAIL_1)
    @Test
    public void correctNotificationTest() throws Exception {
        mvc.perform(put(PATH_ACC + "/notifications")
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

    @WithUserDetails(EMAIL_1)
    @Test
    public void failNotificationTest() throws Exception {
        mvc.perform(put(PATH_ACC + "/notifications")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .with(csrf())
            .content("{\"notification_type\": \"\","
                + "\"enable\": \"\"}"))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(containsString("error")))
            .andExpect(content().string(containsString("error_description")))
            .andDo(MockMvcResultHandlers.print());
    }

    @WithUserDetails(EMAIL_1)
    @Test
    public void correctStatusTest() throws Exception {
        mvc.perform(put(PATH_ACC + "/status")
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

    @WithUserDetails(EMAIL_1)
    @Test
    public void failStatusTest() throws Exception {
        mvc.perform(put(PATH_ACC + "/status")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .with(csrf())
            .content("{\"status\": \"\"}"))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(containsString("error")))
            .andExpect(content().string(containsString("error_description")))
            .andDo(MockMvcResultHandlers.print());
    }
}
