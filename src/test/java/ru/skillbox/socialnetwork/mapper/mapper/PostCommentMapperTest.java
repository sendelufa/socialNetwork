package ru.skillbox.socialnetwork.mapper.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.skillbox.socialnetwork.api.response.CommentApi;
import ru.skillbox.socialnetwork.config.AppConfig;
import ru.skillbox.socialnetwork.model.PostComment;

import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

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
        assertEquals(postComment.getCommentText(), commentApi.getComment_text());
        assertEquals(postComment.getTime().getTime(), commentApi.getTime());
    }

    @Test
    public void testApiToEntity()
    {
        CommentApi commentApi = new CommentApi();
        commentApi.setId(90);
        commentApi.setAuthor_id(234);
        commentApi.setComment_text("hear");
        commentApi.setIs_blocked(true);
        commentApi.setParent_id(232);
        commentApi.setPost_id("4564");
        commentApi.setTime(23522);
        PostComment postComment = mapper.map(commentApi, PostComment.class);

        assertEquals(commentApi.getId(), postComment.getId());
        assertEquals(commentApi.getComment_text(), postComment.getCommentText());
        assertEquals(commentApi.isIs_blocked(), postComment.isBlocked());
        assertEquals(commentApi.getTime(), postComment.getTime().getTime());
    }
}
