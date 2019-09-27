package ru.skillbox.socialnetwork.mapper.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.skillbox.socialnetwork.api.response.AuthorApi;
import ru.skillbox.socialnetwork.api.response.PostApi;
import ru.skillbox.socialnetwork.config.AppConfig;
import ru.skillbox.socialnetwork.mapper.PostCommentMapper;
import ru.skillbox.socialnetwork.mapper.PostMapper;
import ru.skillbox.socialnetwork.mapper.SubCommentMapper;
import ru.skillbox.socialnetwork.model.Person;
import ru.skillbox.socialnetwork.model.Post;
import ru.skillbox.socialnetwork.model.PostComment;

import java.util.*;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, PostMapper.class, PostCommentMapper.class, SubCommentMapper.class})
public class PostMapperTest {

    @Autowired
    private PostMapper postMapper;

    @Test
    public void testEntityToApi()
    {
        Post post = new Post();
        post.setId(4);
        post.setBlocked(true);
        post.setPostText("Hello, world!");
        post.setAuthor(new Person());
        post.getAuthor().setId(1);
        List<PostComment> postCommentList = new ArrayList<>();
        PostComment postComment1 = new PostComment();
        postComment1.setId(10);
        postComment1.setPost(new Post());
        postComment1.getPost().setId(1);
        postComment1.setCommentText("postComment1");
        List<PostComment> subPostCommentList = new ArrayList<>();
        PostComment subPostComment = new PostComment();
        subPostComment.setId(1);
        subPostComment.setCommentText("subPostComment");
        subPostCommentList.add(subPostComment);
        postComment1.setPostComments(subPostCommentList);
        PostComment postComment2 = new PostComment();
        postComment2.setId(20);
        postComment2.setPost(new Post());
        postComment2.getPost().setId(2);
        postComment2.setCommentText("postComment2");
        postCommentList.add(postComment1);
        postCommentList.add(postComment2);
        post.setPostComments(postCommentList);

        GregorianCalendar calendar = new GregorianCalendar(2019, Calendar.MAY, 5);
        Date time = calendar.getTime();
        post.setTime(time);
        post.setTitle("Java");
        PostApi postApi = postMapper.toApi(post);
        assertEquals(post.getId(), postApi.getId());
        assertEquals(post.getPostText(), postApi.getPostText());
        assertEquals(post.getTime().getTime(), postApi.getTime());
        assertEquals(post.getTitle(), postApi.getTitle());
        assertEquals(post.isBlocked(), postApi.isBlocked());
        assertEquals(post.getAuthor().getId(), postApi.getAuthor().getId().intValue());
        assertEquals(post.getPostComments().get(0).getId(), postApi.getComments().get(0).getId());
        assertEquals(post.getPostComments().get(0).getCommentText(), postApi.getComments().get(0).getCommentText());
        assertEquals(post.getPostComments().get(0).getPostComments().get(0).getId(), postApi.getComments().get(0).getSubComments().get(0).getId().intValue());
        assertEquals(post.getPostComments().get(0).getPostComments().get(0).getCommentText(), postApi.getComments().get(0).getSubComments().get(0).getCommentText());
        assertEquals(post.getPostComments().get(1).getId(), postApi.getComments().get(1).getId());
        assertEquals(post.getPostComments().get(1).getCommentText(), postApi.getComments().get(1).getCommentText());
    }

    @Test
    public void testApiToEntity()
    {
        PostApi postApi = new PostApi();
        postApi.setId(7);
        postApi.setTime(234238);
        postApi.setAuthor(new AuthorApi());
        postApi.getAuthor().setId(6);
        postApi.setTitle("Head");
        postApi.setPostText("Grand");
        postApi.setBlocked(false);

        //мапим и сравниваем
        Post post = postMapper.toEntity(postApi);
        assertEquals(postApi.getId(), post.getId());
        assertEquals(postApi.getTime(), post.getTime().getTime());
        assertEquals(postApi.getAuthor().getId().intValue(), post.getAuthor().getId());
        assertEquals(postApi.getTitle(), post.getTitle());
        assertEquals(postApi.getPostText(), post.getPostText());
        assertEquals(postApi.isBlocked(), post.isBlocked());
    }
}
