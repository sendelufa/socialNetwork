package ru.skillbox.socialnetwork.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
public class PostControllerTest {

   private static final String EMAIL = "sidorovmaxim@mail.ru";//email пользователя для
   private final String PATH_POST = "/post";
   @Autowired
   private PostController controller;
   @Autowired
   private MockMvc mvc;
   // авторизации в тестах (кроме смены эмаил)

   @WithUserDetails(EMAIL)
   @Test
   public void get_by_id_success_response() throws Exception {
      mvc.perform(get(PATH_POST + "/{id}", "1")
          .contentType(MediaType.APPLICATION_JSON_UTF8)
          .with(csrf()))
          .andExpect(status().isOk())
          .andExpect(content().string(containsString("error")))
          .andExpect(content().string(containsString("timestamp")))
          .andExpect(content().string(containsString("data")))
          .andDo(MockMvcResultHandlers.print());
   }

   @WithUserDetails(EMAIL)
   @Test
   public void get_by_id_bad_request() throws Exception {
      mvc.perform(get(PATH_POST + "/{id}", "99999999")
          .contentType(MediaType.APPLICATION_JSON_UTF8)
          .with(csrf()))
          .andExpect(status().isBadRequest())
          .andExpect(content().string(containsString("error_description")))
          .andExpect(content().string(containsString("error")))
          .andDo(MockMvcResultHandlers.print());
   }

   @Test
   public void get_by_id_wo_authorization() throws Exception {
      mvc.perform(get(PATH_POST + "/{id}", "1")
          .contentType(MediaType.APPLICATION_JSON_UTF8)
          .with(csrf()))
          .andExpect(status().isUnauthorized())
          .andDo(MockMvcResultHandlers.print());
   }

   @WithUserDetails(EMAIL)
   @Test
   public void edit_by_id_success_response() throws Exception {
      mvc.perform(put(PATH_POST + "/{id}", "1")
          .param("publish_date", "1559751301818")
          .contentType(MediaType.APPLICATION_JSON_UTF8)
          .with(csrf())
          .content("{\n"
              + "  \"title\": \"string\",\n"
              + "  \"post_text\": \"string\"\n"
              + "}"))
          .andExpect(status().isOk())
          .andExpect(content().string(containsString("error")))
          .andExpect(content().string(containsString("timestamp")))
          .andExpect(content().string(containsString("data")))
          .andDo(MockMvcResultHandlers.print());
   }

}
