package ru.skillbox.socialnetwork.mapper.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.skillbox.socialnetwork.api.response.CommentApi;
import ru.skillbox.socialnetwork.config.AppConfig;
import ru.skillbox.socialnetwork.mapper.PostCommentMapper;
import ru.skillbox.socialnetwork.model.Person;
import ru.skillbox.socialnetwork.model.Post;
import ru.skillbox.socialnetwork.model.PostComment;

import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, PostCommentMapper.class})
public class PostCommentMapperTest {

    @Autowired
    private PostCommentMapper postCommentMapper;

    @Test
    public void testEntityToApi()
    {
        PostComment postComment = new PostComment();
        postComment.setId(45);
        postComment.setBlocked(false);
        postComment.setCommentText("good");
        postComment.setAuthor(new Person());
        postComment.getAuthor().setId(1);
        postComment.setParent_id(new PostComment());
        postComment.getParent().setId(1);
        postComment.setPost(new Post());
        postComment.getPost().setId(1);

        GregorianCalendar calendar = new GregorianCalendar(2019, 9, 27);
        Date date = calendar.getTime();
        postComment.setTime(date);
        CommentApi commentApi = postCommentMapper.toApi(postComment);

        assertEquals(postComment.getId(), commentApi.getId());
        assertEquals(postComment.getCommentText(), commentApi.getCommentText());
        assertEquals(postComment.getTime().getTime(), commentApi.getTime());
        assertEquals(postComment.getAuthor().getId(), commentApi.getAuthor().getId());
        assertEquals(postComment.getParent().getId(), commentApi.getParentId().intValue());
        assertEquals(postComment.getPost().getId(), Integer.parseInt(commentApi.getPostId()));
    }

    @Test
    public void testApiToEntity()
    {
        CommentApi commentApi = new CommentApi();
        commentApi.setId(90);
        commentApi.setCommentText("hear");
        commentApi.setBlocked(true);
        commentApi.setParentId(232);
        commentApi.setPostId("4564");
        commentApi.setTime(23522);
        PostComment postComment = postCommentMapper.toEntity(commentApi);

        assertEquals(commentApi.getId(), postComment.getId());
        assertEquals(commentApi.getCommentText(), postComment.getCommentText());
        assertEquals(commentApi.isBlocked(), postComment.isBlocked());
        assertEquals(commentApi.getTime(), postComment.getTime().getTime());
        assertEquals(commentApi.getAuthor().getId(), postComment.getAuthor().getId());
        assertEquals(commentApi.getParentId().intValue(), postComment.getParent().getId());
        assertEquals(Integer.parseInt(commentApi.getPostId()), postComment.getPost().getId());
    }
}
