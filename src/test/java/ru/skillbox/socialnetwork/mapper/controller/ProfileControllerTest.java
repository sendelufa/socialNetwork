package ru.skillbox.socialnetwork.mapper.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import ru.skillbox.socialnetwork.controller.ProfileController;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(ProfileController.class)
public class ProfileControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @WithMockUser
    public void testGetMe() throws Exception {
        mvc.perform(get("/api/v1/users/me")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .with(csrf()))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

        mvc.perform(get("/api/v1/users/me")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .with(csrf()))
                .andExpect(status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    @WithMockUser
    public void testEditMe() throws Exception {
        mvc.perform(put("/api/v1/users/me")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .with(csrf()))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

        mvc.perform(put("/api/v1/users/me")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .with(csrf()))
                .andExpect(status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @WithMockUser
    public void testDeleteMe() throws Exception {
        mvc.perform(delete("/api/v1/users/me")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .with(csrf()))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

        mvc.perform(delete("/api/v1/users/me")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .with(csrf()))
                .andExpect(status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @WithMockUser
    public void testGet() throws Exception {
        mvc.perform(get("/api/v1/users/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("id", "1")
                .with(csrf()))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

        mvc.perform(get("/api/v1/users/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .with(csrf())
                .param("id", "f"))
                .andExpect(status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @WithMockUser
    public void testGetWall() throws Exception {
        mvc.perform(get("/api/v1/users/1/wall")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("queue", "false")
                .param("id", "1")
                .param("offset", "1")
                .param("itemPerPage", "20")
                .with(csrf()))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

        mvc.perform(get("/api/v1/users/1/wall")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("queue", "")
                .param("id", "f")
                .param("offset", "")
                .param("itemPerPage", "")
                .with(csrf()))
                .andExpect(status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    @WithMockUser
    public void testPostWall() throws Exception {
        mvc.perform(post("/api/v1/users/1/wall")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .with(csrf())
                .param("id", "1")
                .param("publishDate",""))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

        mvc.perform(post("/api/v1/users/1/wall")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .with(csrf())
                .param("id", "f")
                .param("publishDate",""))
                .andExpect(status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @WithMockUser
    public void testSearch() throws Exception {
        mvc.perform(get("/api/v1/users/search")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .with(csrf())
                .param("first_name","a")
                .param("last_name","b")
                .param("age_from","1")
                .param("age_to","2")
                .param("country_id","1")
                .param("city_id","2")
                .param("offset", "2")
                .param("itemPerPage", "2"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

        mvc.perform(get("/api/v1/users/search")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .with(csrf())
                .param("first_name","")
                .param("last_name","")
                .param("age_from","")
                .param("age_to","")
                .param("country_id","")
                .param("city_id","")
                .param("offset", "")
                .param("itemPerPage", ""))
                .andExpect(status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @WithMockUser
    public void testBlock() throws Exception {
        mvc.perform(put("/api/v1/users/block/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .with(csrf())
                .param("id", "1"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

        mvc.perform(put("/api/v1/users/block/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .with(csrf()))
                .andExpect(status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @WithMockUser
    public void testUnlock() throws Exception {
        mvc.perform(delete("/api/v1/users/block/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .with(csrf())
                .param("id", "1"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

        mvc.perform(delete("/api/v1/users/block/ ")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .with(csrf()))
                .andExpect(status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }
}
