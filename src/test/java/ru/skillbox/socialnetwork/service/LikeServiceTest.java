package ru.skillbox.socialnetwork.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.skillbox.socialnetwork.api.request.RequestLikeApi;
import ru.skillbox.socialnetwork.api.response.AbstractResponse;
import ru.skillbox.socialnetwork.api.response.ErrorApi;
import ru.skillbox.socialnetwork.api.response.LikeApi;
import ru.skillbox.socialnetwork.api.response.ResponseApi;
import ru.skillbox.socialnetwork.dao.LikeDAO;
import ru.skillbox.socialnetwork.dao.PostDAO;
import ru.skillbox.socialnetwork.model.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;

public class LikeServiceTest{

    @InjectMocks
    private LikeService likeService;

    @Mock
    private AccountService accountService;

    @Mock
    private LikeDAO likeDAO;

    @Mock
    private PostDAO postDAO;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testIsPostLiked(){
        boolean actual = false;
        boolean expected = true;

        PostLike expectedPostLike = new PostLike();
        expectedPostLike.setId(1);
        Post post = new Post();
        post.setId(2);
        expectedPostLike.setPost(post);
        Person person = new Person();
        person.setId(2);
        expectedPostLike.setPerson(person);

        when(likeDAO.getLikedPost(2,2)).thenReturn(expectedPostLike);

        AbstractResponse response = likeService.isLiked(2,2,"Post");

        if(response.isSuccess()){
            ResponseApi result = (ResponseApi) response;
            LikeApi.Likes likeApi = (LikeApi.Likes) result.getAbstractResponse();

            actual = likeApi.getLikes();

        }
        assertEquals(expected,actual);
    }

    @Test
    public void testIfIsPostNotLiked(){
        boolean actual = false;
        boolean expected = true;

        when(likeDAO.getLikedPost(3,8)).thenReturn(null);

        AbstractResponse response = likeService.isLiked(3,8,"Post");

        if(response.isSuccess()){
            ResponseApi result = (ResponseApi) response;
            LikeApi.Likes likeApi = (LikeApi.Likes) result.getAbstractResponse();
            actual = likeApi.getLikes();
        }
        assertNotEquals(expected,actual);
    }

    @Test
    public void testIsCommentLiked(){
        boolean actual = false;
        boolean expected = true;

        CommentLike expectedCommentLike = new CommentLike();
        expectedCommentLike.setId(1);
        PostComment comment = new PostComment();
        comment.setId(2);
        expectedCommentLike.setPostComment(comment);
        Person person = new Person();
        person.setId(2);
        expectedCommentLike.setPerson(person);

        when(likeDAO.getLikedComment(2,2)).thenReturn(expectedCommentLike);

        AbstractResponse response = likeService.isLiked(2,2,"Comment");

        if(response.isSuccess()){
            ResponseApi result = (ResponseApi) response;
            LikeApi.Likes likeApi = (LikeApi.Likes) result.getAbstractResponse();
            actual = likeApi.getLikes();
        }
        assertEquals(expected,actual);
    }

    @Test
    public void testGetPostLikes(){
        int expectedCount = 2;
        int actualCount = 0;
        Set<Integer> expectedSet = new HashSet<>();
        expectedSet.add(10);
        expectedSet.add(9);
        Set<Integer> actualSet = new HashSet<>();

        List<PostLike> expectedList = new ArrayList<>();
        PostLike postLike1 = new PostLike();
        postLike1.setId(1);
        Post post = new Post();
        post.setId(3);
        postLike1.setPost(post);
        Person person = new Person();
        person.setId(10);
        postLike1.setPerson(person);

        PostLike postLike2 = new PostLike();
        postLike2.setId(1);
        Post post2 = new Post();
        post2.setId(3);
        postLike2.setPost(post2);
        Person person2 = new Person();
        person2.setId(9);
        postLike2.setPerson(person2);

        expectedList.add(postLike1);
        expectedList.add(postLike2);

        when(likeDAO.getPostLikesListByPostId(3)).thenReturn(expectedList);

        AbstractResponse response = likeService.getLikes(3, "Post");
        if(response.isSuccess()){
            ResponseApi result = (ResponseApi) response;
            LikeApi.BitLikes likeApi = (LikeApi.BitLikes) result.getAbstractResponse();
            actualCount = likeApi.getLikes();
            actualSet = likeApi.getUsers();
        }

        assertEquals(expectedCount,actualCount);
        assertEquals(expectedSet,actualSet);
    }

    @Test
    public void testGetCommentLikes(){
        int expectedCount = 2;
        int actualCount = 0;
        Set<Integer> expectedSet = new HashSet<>();
        expectedSet.add(10);
        expectedSet.add(9);
        Set<Integer> actualSet = new HashSet<>();

        List<CommentLike> expectedList = new ArrayList<>();
        CommentLike commentLike1 = new CommentLike();
        commentLike1.setId(1);
        PostComment postComment = new PostComment();
        postComment.setId(3);
        commentLike1.setPostComment(postComment);
        Person person = new Person();
        person.setId(10);
        commentLike1.setPerson(person);

        CommentLike commentLike2 = new CommentLike();
        commentLike2.setId(1);
        PostComment postComment2 = new PostComment();
        postComment2.setId(3);
        commentLike2.setPostComment(postComment2);
        Person person2 = new Person();
        person2.setId(9);
        commentLike2.setPerson(person2);

        expectedList.add(commentLike1);
        expectedList.add(commentLike2);

        when(likeDAO.getCommentLikesListByCommentId(3)).thenReturn(expectedList);

        AbstractResponse response = likeService.getLikes(3, "Comment");
        if(response.isSuccess()){
            ResponseApi result = (ResponseApi) response;
            LikeApi.BitLikes likeApi = (LikeApi.BitLikes) result.getAbstractResponse();

            actualCount = likeApi.getLikes();
            actualSet = likeApi.getUsers();
        }

        assertEquals(expectedCount,actualCount);
        assertEquals(expectedSet,actualSet);
    }


    @Test
    public void testLike(){
        int expected = 1;
        int actual = 0;

        Person currentPerson = new Person();
        currentPerson.setId(11);
        when(accountService.getCurrentUser()).thenReturn(currentPerson);

        Post post = new Post();
        post.setId(1);
        when(postDAO.getPostById(1)).thenReturn(post);
        RequestLikeApi likeApi = new RequestLikeApi();
        likeApi.setItem_id(1);
        likeApi.setType("Post");
        AbstractResponse response = likeService.like(likeApi);

        if(response.isSuccess()){
            ResponseApi result = (ResponseApi) response;
            LikeApi.BitLikes responseLikeApi = (LikeApi.BitLikes) result.getAbstractResponse();
            actual = responseLikeApi.getLikes();
        }
        assertEquals(expected,actual);
    }

    @Test
    public void testLikeAbsentPost(){
        String expected = "No such post exists";
        String actual = "";

        Person currentPerson = new Person();
        currentPerson.setId(11);
        when(accountService.getCurrentUser()).thenReturn(currentPerson);

        Post post = new Post();
        post.setId(99);
        when(postDAO.getPostById(99)).thenReturn(null);
        RequestLikeApi likeApi = new RequestLikeApi();
        likeApi.setItem_id(99);
        likeApi.setType("Post");
        AbstractResponse response = likeService.like(likeApi);

        if(!response.isSuccess()){
            actual = ((ErrorApi) response).getError_description();
        }
        assertEquals(expected,actual);
    }

    @Test
    public void testRemoveLike(){
        int expected = 1;
        int actual = 0;

        Person currentPerson = new Person();
        when(accountService.getCurrentUser()).thenReturn(currentPerson);

        PostLike expectedPostLike = new PostLike();
        when(likeDAO.getLikedPost(11, 1)).thenReturn(expectedPostLike);
        AbstractResponse response = likeService.removeLike(1,"Post");

        if(response.isSuccess()){
            ResponseApi result = (ResponseApi) response;
            LikeApi.BitLikes responseLikeApi = (LikeApi.BitLikes) result.getAbstractResponse();

            actual = responseLikeApi.getLikes();
        }
        assertEquals(expected,actual);
    }
}

