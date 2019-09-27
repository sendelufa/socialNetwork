package ru.skillbox.socialnetwork.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TagControllerTest {

  private final String PATH_USER = "/tags";
  private static final String EMAIL_1 = "sidorovmaxim@mail.ru";//email пользователя для авторизации в тестах (кроме смены эмаил)

  @Autowired
    private MockMvc mvc;

  @Autowired
  private TagController controller;

    @WithUserDetails(EMAIL_1)
  @Test
  public void correctAddTagTest() throws Exception {
    mvc.perform(post(PATH_USER + "/")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content("{\n"
            + "  \"tag\": \"test5 tag\"\n"
            + "}")
        .with(csrf()))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("error")))
        .andExpect(content().string(containsString("timestamp")))
        .andExpect(content().string(containsString("data")))
        .andExpect(content().string(containsString("id")))
        .andExpect(content().string(containsString("tag")))
        .andDo(MockMvcResultHandlers.print());
  }

  @Test
  public void failAddTagTest() throws Exception {
    mvc.perform(post(PATH_USER + "/")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content("{\n"
            + "  \"tag\": \"test5 tag\"\n"
            + "}")
        .with(csrf()))
        .andExpect(status().isUnauthorized())
        .andDo(MockMvcResultHandlers.print());
  }

  @WithUserDetails(EMAIL_1)
  @Test
  public void correctDeleteTagTest() throws Exception {
    mvc.perform(delete(PATH_USER + "/")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .param("id", "2")
        .with(csrf()))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("error")))
        .andExpect(content().string(containsString("timestamp")))
        .andExpect(content().string(containsString("data")))
        .andExpect(content().string(containsString("message")))
        .andDo(MockMvcResultHandlers.print());
  }

  @WithUserDetails(EMAIL_1)
  @Test
  public void failDeleteTagTest() throws Exception {
    mvc.perform(delete(PATH_USER + "/")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .param("id", "999999")
        .with(csrf()))
        .andExpect(status().isBadRequest())
        .andExpect(content().string(containsString("error")))
        .andExpect(content().string(containsString("error_description")))
        .andDo(MockMvcResultHandlers.print());
  }

  @WithUserDetails(EMAIL_1)
  @Test
  public void correctGetTagsTest() throws Exception {
    mvc.perform(get(PATH_USER + "/")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .param("tag", "По")
        .with(csrf()))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("error")))
        .andExpect(content().string(containsString("timestamp")))
        .andExpect(content().string(containsString("data")))
        .andExpect(content().string(containsString("id")))
        .andExpect(content().string(containsString("tag")))
        .andDo(MockMvcResultHandlers.print());
  }

  @Test
  public void failGetTagsTest() throws Exception {
    mvc.perform(get(PATH_USER + "/")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .param("tag", "Еда")
        .with(csrf()))
        .andExpect(status().isUnauthorized())
        .andDo(MockMvcResultHandlers.print());
  }
}
