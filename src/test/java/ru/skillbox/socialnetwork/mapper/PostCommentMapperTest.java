package ru.skillbox.socialnetwork.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.skillbox.socialnetwork.api.response.CommentApi;
import ru.skillbox.socialnetwork.config.AppConfig;
import ru.skillbox.socialnetwork.config.HibernateConf;
import ru.skillbox.socialnetwork.dao.PersonDAO;
import ru.skillbox.socialnetwork.dao.PostDAO;
import ru.skillbox.socialnetwork.model.Person;
import ru.skillbox.socialnetwork.model.Post;
import ru.skillbox.socialnetwork.model.PostComment;

import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, PostCommentMapper.class, PersonDAO.class, PostDAO.class, HibernateConf.class})
@Transactional("platformTransactionManager")
public class PostCommentMapperTest {

    @Autowired
    private PostCommentMapper postCommentMapper;

    @Test
    public void testEntityToApi() {
        //заполняем поля
        PostComment postComment = new PostComment();
        postComment.setId(45);
        postComment.setBlocked(false);
        postComment.setCommentText("good");
        postComment.setAuthor(new Person());
        postComment.getAuthor().setId(1);
        postComment.setParent_id(new PostComment());
        postComment.getParent_id().setId(1);
        postComment.setPost(new Post());
        postComment.getPost().setId(1);

        GregorianCalendar calendar = new GregorianCalendar(2019, 9, 27);
        Date date = calendar.getTime();
        postComment.setTime(date);

        //мапим и сравниваем
        CommentApi commentApi = postCommentMapper.toApi(postComment);
        assertEquals(postComment.getId(), commentApi.getId());
        assertEquals(postComment.getCommentText(), commentApi.getComment_text());
        assertEquals(postComment.getTime().getTime(), commentApi.getTime());
        assertEquals(postComment.getAuthor().getId(), commentApi.getAuthor_id().intValue());
        assertEquals(postComment.getParent_id().getId(), commentApi.getParent_id().intValue());
        assertEquals(postComment.getPost().getId(), Integer.parseInt(commentApi.getPost_id()));
    }

    @Test
    public void testApiToEntity() {
        //заполняем поля
        CommentApi commentApi = new CommentApi();
        commentApi.setId(90);
        commentApi.setAuthor_id(1);
        commentApi.setComment_text("hear");
        commentApi.setIs_blocked(true);
        commentApi.setParent_id(1);
        commentApi.setPost_id("1");
        commentApi.setTime(23522);

        //мапим и сравниваем
        PostComment postComment = postCommentMapper.toEntity(commentApi);
        assertEquals(commentApi.getId(), postComment.getId());
        assertEquals(commentApi.getComment_text(), postComment.getCommentText());
        assertEquals(commentApi.isIs_blocked(), postComment.isBlocked());
        assertEquals(commentApi.getTime(), postComment.getTime().getTime());
        assertEquals(commentApi.getAuthor_id().intValue(), postComment.getAuthor().getId());
        assertEquals(commentApi.getParent_id().intValue(), postComment.getParent_id().getId());
        assertEquals(Integer.parseInt(commentApi.getPost_id()), postComment.getPost().getId());
    }
}
