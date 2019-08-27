package ru.skillbox.socialnetwork.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skillbox.socialnetwork.api.response.AbstractResponse;
import ru.skillbox.socialnetwork.api.response.ErrorApi;
import ru.skillbox.socialnetwork.api.request.RequestLikeApi;
import ru.skillbox.socialnetwork.api.response.LikeApi;
import ru.skillbox.socialnetwork.api.response.ResponseApi;
import ru.skillbox.socialnetwork.dao.LikeDAO;
import ru.skillbox.socialnetwork.dao.PostDAO;
import ru.skillbox.socialnetwork.model.*;

import java.util.*;

@Service
public class LikeService {

    @Autowired
    private LikeDAO likeDAO;
    @Autowired
    private PostDAO postDAO;
    @Autowired
    private AccountService accountService;


    public AbstractResponse isLiked(int userId, int itemId, String type){

        AbstractResponse response;

        if(type.equals("Post")){
            if(likeDAO.getLikedPost(userId,itemId) == null){
                response = new ResponseApi("string", System.currentTimeMillis(), new LikeApi.Likes(false));
            } else {
                response = new ResponseApi("string", System.currentTimeMillis(), new LikeApi.Likes(true));
            }

            response.setSuccess(true);
            return response;
        } else if(type.equals("Comment")) {
            if(likeDAO.getLikedComment(userId,itemId) == null){
                response = new ResponseApi("string", System.currentTimeMillis(), new LikeApi.Likes(false));
            } else {
                response = new ResponseApi("string", System.currentTimeMillis(), new LikeApi.Likes(true));
            }

            response.setSuccess(true);
            return response;
        }

        response = new ErrorApi("invalid_request", "Wrong type of entity");
        response.setSuccess(false);
        return response;
    }

    public AbstractResponse getLikes(int itemId, String type){

        AbstractResponse response;
        Set<Integer> userList = new HashSet<>();

        if(type.equals("Post")){
            List<PostLike> postLikeList = likeDAO.getPostLikesListByPostId(itemId);

            for(PostLike like : postLikeList){
                userList.add(like.getPerson().getId());
            }

            return getOutputResponse(userList);
        } else if(type.equals("Comment")) {
            List<CommentLike> commentLikeList = likeDAO.getCommentLikesListByCommentId(itemId);

            for(CommentLike like : commentLikeList){
                userList.add(like.getPerson().getId());
            }

            return getOutputResponse(userList);
        }

        response = new ErrorApi("invalid_request", "Wrong type of entity");
        response.setSuccess(false);
        return response;
    }

    public AbstractResponse like(RequestLikeApi likeApi){

        AbstractResponse response;

        if(likeApi.getType().equals("Post")) {
            PostLike postLike = new PostLike();
            postLike.setTime(new Date());


            Person person = accountService.getCurrentUser();
            postLike.setPerson(person);

            Post post = postDAO.getPostById(likeApi.getItem_id());

            if(post == null){
                response = new ErrorApi("invalid_request", "No such post exists");
                response.setSuccess(false);
                return response;
            }
            postLike.setPost(post);

            List<PostLike> postLikes = likeDAO.getAllPostLikes();
            postLikes.forEach(like -> {
                if (likeApi.getItem_id() == like.getPost().getId() && person == like.getPerson()) {
                    likeDAO.deletePostLike(like);
                    likeDAO.addPostLike(postLike);
                } else {
                    likeDAO.addPostLike(postLike);
                }
            });

            response = new ResponseApi("string", System.currentTimeMillis(), new LikeApi.BitLikes(1));
            response.setSuccess(true);
            return response;
        }  else if(likeApi.getType().equals("Comment")) {
            CommentLike commentLike = new CommentLike();
            commentLike.setTime(new Date());

            Person person = accountService.getCurrentUser();
            commentLike.setPerson(person);

            PostComment postComment = postDAO.getCommentById(likeApi.getItem_id());

            if(postComment == null){
                response = new ErrorApi("invalid_request", "No such post exists");
                response.setSuccess(false);
                return response;
            }
            commentLike.setPostComment(postComment);

            likeDAO.addCommentLike(commentLike);

            response = new ResponseApi("string", System.currentTimeMillis(), new LikeApi.BitLikes(1));
            response.setSuccess(true);
            return response;
        }

        response = new ErrorApi("invalid_request", "Wrong type of entity");
        response.setSuccess(false);
        return response;
    }

    public AbstractResponse removeLike(int itemId, String type){

        AbstractResponse response;

        Person person = accountService.getCurrentUser();

        if(type.equals("Post")){
            PostLike postLike = likeDAO.getLikedPost(person.getId(), itemId);
            likeDAO.deletePostLike(postLike);
            response = new ResponseApi("string", System.currentTimeMillis(), new LikeApi.BitLikes(1));
            response.setSuccess(true);
            return response;
        } else if(type.equals("Comment")) {
            CommentLike commentLike = likeDAO.getLikedComment(person.getId(), itemId);
            likeDAO.deleteCommentLike(commentLike);
            response = new ResponseApi("string", System.currentTimeMillis(), new LikeApi.BitLikes(1));
            response.setSuccess(true);
            return response;
        }

        response = new ErrorApi("invalid_request", "Wrong type of entity");
        response.setSuccess(false);
        return response;
    }

    private AbstractResponse getOutputResponse(Set<Integer> userList){

        AbstractResponse response;

        if(!userList.isEmpty()){
            response = new ResponseApi("string", System.currentTimeMillis(), new LikeApi.BitLikes(userList.size(), userList));
            response.setSuccess(true);
            return response;
        } else {
            response = new ResponseApi("string", System.currentTimeMillis(), new LikeApi.BitLikes(0, userList));
            response.setSuccess(true);
            return response;
        }
    }

}
