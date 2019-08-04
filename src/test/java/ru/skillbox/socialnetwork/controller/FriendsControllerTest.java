package ru.skillbox.socialnetwork.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import ru.skillbox.socialnetwork.dao.PersonDAO;
import ru.skillbox.socialnetwork.model.Person;
import ru.skillbox.socialnetwork.service.AccountService;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FriendsControllerTest {

  private static final String EMAIL_1 = "ivaniavanov@mail.ru";//email пользователя для авторизации в тестах

  @Autowired
  private MockMvc mvc;

  @Autowired
  private FriendsController controller;

  @Autowired
  private PersonDAO personDAO;

  @MockBean
  private AccountService service;

  @WithUserDetails(EMAIL_1)
  @Test
  public void correctGetFriendsTest() throws Exception {
    serviceCurrentUser();

    mvc.perform(get("/friends")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .with(csrf()))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("error")))
        .andExpect(content().string(containsString("timestamp")))
        .andExpect(content().string(containsString("offset")))
        .andExpect(content().string(containsString("total")))
        .andExpect(content().string(containsString("data")))
        .andExpect(content().string(containsString("id")))
        .andExpect(content().string(containsString("email")))
        .andDo(MockMvcResultHandlers.print());
  }

  @Test
  public void failGetFriendsTest() throws Exception {
    mvc.perform(get("/friends")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .with(csrf()))
        .andExpect(status().isUnauthorized())
        .andDo(MockMvcResultHandlers.print());
  }

  @WithUserDetails(EMAIL_1)
  @Test
  public void correctDeleteFriendTest() throws Exception {
    serviceCurrentUser();

    mvc.perform(delete("/friends/8")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .with(csrf()))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("error")))
        .andExpect(content().string(containsString("timestamp")))
        .andExpect(content().string(containsString("data")))
        .andExpect(content().string(containsString("message")))
        .andDo(MockMvcResultHandlers.print());
  }


  @Test
  public void failDeleteFriendTest() throws Exception {
    mvc.perform(delete("/friends/8")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .with(csrf()))
        .andExpect(status().isUnauthorized())
        .andDo(MockMvcResultHandlers.print());
  }


  @WithUserDetails(EMAIL_1)
  @Test
  public void correctAddFriendTest() throws Exception {
    serviceCurrentUser();

    mvc.perform(post("/friends/3")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .with(csrf()))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("error")))
        .andExpect(content().string(containsString("timestamp")))
        .andExpect(content().string(containsString("data")))
        .andExpect(content().string(containsString("message")))
        .andDo(MockMvcResultHandlers.print());
  }

  @Test
  public void failAddFriendTest() throws Exception {
    mvc.perform(post("/friends/3")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .with(csrf()))
        .andExpect(status().isUnauthorized())
        .andDo(MockMvcResultHandlers.print());
  }

  @WithUserDetails(EMAIL_1)
  @Test
  public void correctGetRequestFriendsTest() throws Exception {
    serviceCurrentUser();

    mvc.perform(get("/friends/request")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .with(csrf()))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("error")))
        .andExpect(content().string(containsString("timestamp")))
        .andExpect(content().string(containsString("offset")))
        .andExpect(content().string(containsString("total")))
        .andExpect(content().string(containsString("data")))
        .andDo(MockMvcResultHandlers.print());
  }

  @Test
  public void failGetRequestFriendsTest() throws Exception {
    mvc.perform(get("/friends/request")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .with(csrf()))
        .andExpect(status().isUnauthorized())
        .andDo(MockMvcResultHandlers.print());
  }


  @WithUserDetails(EMAIL_1)
  @Test
  public void correctGetFriendsRecommendationsTest() throws Exception {
    serviceCurrentUser();

    mvc.perform(get("/friends/recommendations")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .with(csrf()))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("error")))
        .andExpect(content().string(containsString("timestamp")))
        .andExpect(content().string(containsString("offset")))
        .andExpect(content().string(containsString("total")))
        .andExpect(content().string(containsString("data")))
        .andDo(MockMvcResultHandlers.print());
  }

  @Test
  public void failGetFriendsRecommendationsTest() throws Exception {
    mvc.perform(get("/friends/recommendations")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .with(csrf()))
        .andExpect(status().isUnauthorized())
        .andDo(MockMvcResultHandlers.print());
  }


  @WithUserDetails(EMAIL_1)
  @Test
  public void correctIsFriendTest() throws Exception {
    serviceCurrentUser();

    mvc.perform(post("/is/friends")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content("{"
            + "  \"user_ids\": ["
            + "    3,"
            + "    8"
            + "  ]"
            + "}")
        .with(csrf()))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("data")))
        .andExpect(content().string(containsString("user_id")))
        .andExpect(content().string(containsString("status")))
        .andDo(MockMvcResultHandlers.print());
  }

  @Test
  public void failIsFriendTest() throws Exception {
    mvc.perform(post("/is/friends")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .with(csrf()))
        .andExpect(status().isUnauthorized())
        .andDo(MockMvcResultHandlers.print());
  }

  public Person getCurrentUser() {
    String email = SecurityContextHolder.getContext().getAuthentication().getName();
    Person personByEmail = personDAO.getPersonByEmail(email);
    return personByEmail;

  }

  private void serviceCurrentUser() {
    Mockito.doReturn(getCurrentUser())
        .when(service)
        .getCurrentUser();
  }
}
