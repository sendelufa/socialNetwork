package ru.skillbox.socialnetwork.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.skillbox.socialnetwork.api.response.LikeApi;
import ru.skillbox.socialnetwork.config.AppConfig;
import ru.skillbox.socialnetwork.model.Person;
import ru.skillbox.socialnetwork.model.Post;
import ru.skillbox.socialnetwork.model.PostLike;

import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, PostLikeMapper.class})
public class PostLikeMapperTest {

    @Autowired
    private PostLikeMapper postLikeMapper;

    @Test
    public void testEntityToApi() {
        //заполняем поля
        PostLike postLike = new PostLike();
        postLike.setId(232);
        postLike.setPost(new Post());
        postLike.getPost().setId(1);
        postLike.setPerson(new Person());
        postLike.getPerson().setId(1);

        GregorianCalendar calendar = new GregorianCalendar(1982, 11, 23);
        Date date = calendar.getTime();
        postLike.setTime(date);

        //мапим и сравниваем
        LikeApi likeApi = postLikeMapper.toApi(postLike);
        assertEquals(postLike.getId(), likeApi.getId());
        assertEquals(postLike.getTime().getTime(), likeApi.getTime());
        assertEquals(postLike.getPerson().getId(), likeApi.getPerson_id().intValue());
        assertEquals(postLike.getPost().getId(), likeApi.getPost_id().intValue());
    }

    @Test
    public void testApiToEntity() {
        //заполняем поля
        LikeApi likeApi = new LikeApi();
        likeApi.setId(232423);
        likeApi.setPerson_id(1);
        likeApi.setPost_id(1);
        likeApi.setTime(23113242);

        //мапим и сравниваем
        PostLike postLike = postLikeMapper.toEntity(likeApi);
        assertEquals(likeApi.getId(), postLike.getId());
        assertEquals(likeApi.getTime(), postLike.getTime().getTime());
        assertEquals(likeApi.getPerson_id().intValue(), postLike.getPerson().getId());
        assertEquals(likeApi.getPost_id().intValue(), postLike.getPost().getId());
    }
}
