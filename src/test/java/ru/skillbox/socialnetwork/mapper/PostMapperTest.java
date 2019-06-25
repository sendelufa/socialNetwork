package ru.skillbox.socialnetwork.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.skillbox.socialnetwork.api.response.PostApi;
import ru.skillbox.socialnetwork.config.AppConfig;
import ru.skillbox.socialnetwork.model.Post;

import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= AppConfig.class)
public class PostMapperTest {

    @Autowired
    private ModelMapper mapper;

    @Test
    public void testEntityToApi()
    {
        //заполняем поля
        Post post = new Post();
        post.setId(4);
        post.setBlocked(true);
        post.setPostText("Hello, world!");

        GregorianCalendar calendar = new GregorianCalendar(2019, 4, 5);
        Date time = calendar.getTime();
        post.setTime(time);

        post.setTitle("Java");

        //мапим и сравниваем
        PostApi postApi = mapper.map(post, PostApi.class);
        assertEquals(post.getId(), postApi.getId());
        assertEquals(post.getPostText(), postApi.getPost_text());
        assertEquals(post.getTime().getTime(), postApi.getTime());
        assertEquals(post.getTitle(), postApi.getTitle());
        assertEquals(post.isBlocked(), postApi.isIs_blocked());
    }

    @Test
    public void testApiToEntity()
    {
        //заполняем поля
        PostApi postApi = new PostApi();
        postApi.setId(7);
        postApi.setAuthor_id(6);
        postApi.setIs_blocked(false);
        postApi.setPost_text("Grand");
        postApi.setTime(234238);
        postApi.setTitle("Head");

        //мапим и сравниваем
        Post post = mapper.map(postApi, Post.class);
        assertEquals(postApi.getId(), post.getId());
        assertEquals(postApi.getPost_text(), post.getPostText());
        assertEquals(postApi.getTime(), post.getTime().getTime());
        assertEquals(postApi.getTitle(), post.getTitle());
        assertEquals(postApi.isIs_blocked(), post.isBlocked());
    }
}
