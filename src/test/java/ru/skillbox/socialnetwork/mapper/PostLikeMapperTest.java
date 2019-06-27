package ru.skillbox.socialnetwork.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.skillbox.socialnetwork.api.response.LikeApi;
import ru.skillbox.socialnetwork.config.AppConfig;
import ru.skillbox.socialnetwork.model.PostLike;

import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= AppConfig.class)
public class PostLikeMapperTest {

    @Autowired
    private ModelMapper mapper;

    @Test
    public void testEntityToApi()
    {
        //заполняем поля
        PostLike postLike = new PostLike();
        postLike.setId(232);

        GregorianCalendar calendar = new GregorianCalendar(1982, 11, 23);
        Date date = calendar.getTime();
        postLike.setTime(date);

        //мапим и сравниваем
        LikeApi likeApi = mapper.map(postLike, LikeApi.class);
        assertEquals(postLike.getId(), likeApi.getId());
        assertEquals(postLike.getTime().getTime(), likeApi.getTime());
    }

    @Test
    public void testApiToEntity()
    {
        //заполняем поля
        LikeApi likeApi = new LikeApi();
        likeApi.setId(232423);
        likeApi.setPerson_id(2342);
        likeApi.setPost_id(2421);
        likeApi.setTime(23113242);

        //мапим и сравниваем
        PostLike postLike = mapper.map(likeApi, PostLike.class);
        assertEquals(likeApi.getId(), postLike.getId());
        assertEquals(likeApi.getTime(), postLike.getTime().getTime());
    }
}
