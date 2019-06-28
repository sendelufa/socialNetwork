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
        assertEquals(post.getId(), postApi.getData().getId());
        assertEquals(post.getPostText(), postApi.getData().getPostText());
        assertEquals(post.getTime().getTime(), postApi.getData().getTime());
        assertEquals(post.getTitle(), postApi.getData().getTitle());
        assertEquals(post.isBlocked(), postApi.getData().isBlocked());
    }

    @Test
    public void testApiToEntity()
    {
        //заполняем поля
        PostApi postApi = new PostApi();
        postApi.getData().setId(7);
        postApi.getData().setTime(234238);
        postApi.getData().setAuthorId(6);
        postApi.getData().setTitle("Head");
        postApi.getData().setPostText("Grand");
        postApi.getData().setBlocked(false);

        //мапим и сравниваем
        Post post = mapper.map(postApi, Post.class);
        assertEquals(postApi.getData().getId(), post.getId());
        assertEquals(postApi.getData().getTime(), post.getTime().getTime());
        assertEquals(postApi.getData().getAuthorId(), post.getAuthor().getId());
        assertEquals(postApi.getData().getTitle(), post.getTitle());
        assertEquals(postApi.getData().getPostText(), post.getPostText());
        assertEquals(postApi.getData().isBlocked(), post.isBlocked());
    }
}
