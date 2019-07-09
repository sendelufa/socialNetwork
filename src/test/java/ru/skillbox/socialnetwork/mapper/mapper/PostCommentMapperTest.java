package ru.skillbox.socialnetwork.mapper.mapper;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.GregorianCalendar;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.skillbox.socialnetwork.api.response.CommentApi;
import ru.skillbox.socialnetwork.config.AppConfig;
import ru.skillbox.socialnetwork.model.PostComment;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= AppConfig.class)
public class PostCommentMapperTest {

    @Autowired
    private ModelMapper mapper;

    @Test
    public void testEntityToApi()
    {
        PostComment postComment = new PostComment();
        postComment.setId(45);
        postComment.setBlocked(false);
        postComment.setCommentText("good");
        GregorianCalendar calendar = new GregorianCalendar(2019, 9, 27);
        Date date = calendar.getTime();
        postComment.setTime(date);
        CommentApi commentApi = mapper.map(postComment, CommentApi.class);

        assertEquals(postComment.getId(), commentApi.getId());
        assertEquals(postComment.getCommentText(), commentApi.getCommentText());
        assertEquals(postComment.getTime().getTime(), commentApi.getTime());
    }

    @Test
    public void testApiToEntity()
    {
        CommentApi commentApi = new CommentApi();
        commentApi.setId(90);
        commentApi.setAuthorId(234);
        commentApi.setCommentText("hear");
        commentApi.setBlocked(true);
        commentApi.setParentId(232);
        commentApi.setPostId("4564");
        commentApi.setTime(23522);
        PostComment postComment = mapper.map(commentApi, PostComment.class);

        assertEquals(commentApi.getId(), postComment.getId());
        assertEquals(commentApi.getCommentText(), postComment.getCommentText());
        assertEquals(commentApi.isBlocked(), postComment.isBlocked());
        assertEquals(commentApi.getTime(), postComment.getTime().getTime());
    }
}
