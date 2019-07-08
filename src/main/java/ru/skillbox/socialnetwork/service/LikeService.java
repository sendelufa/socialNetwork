package ru.skillbox.socialnetwork.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.skillbox.socialnetwork.api.response.AbstractResponse;
import ru.skillbox.socialnetwork.api.response.ErrorApi;
import ru.skillbox.socialnetwork.api.request.RequestLikeApi;
import ru.skillbox.socialnetwork.api.response.LikeApi;
import ru.skillbox.socialnetwork.api.response.ResponseApi;
import ru.skillbox.socialnetwork.dao.LikeDAO;
import ru.skillbox.socialnetwork.dao.PersonDAO;
import ru.skillbox.socialnetwork.dao.PostDAO;
import ru.skillbox.socialnetwork.model.Person;
import ru.skillbox.socialnetwork.model.Post;
import ru.skillbox.socialnetwork.model.PostLike;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class LikeService {

    @Autowired
    private PersonDAO personDAO;
    @Autowired
    private LikeDAO likeDAO;
    @Autowired
    private PostDAO postDAO;


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
            //TODO добавить секцию для Лайка Комментариев
        }

        response = new ErrorApi("invalid_request", "Wrong type of entity");
        response.setSuccess(false);
        return response;
    }

    public AbstractResponse getLikes(int itemId, String type){


        AbstractResponse response;
        List<Integer> userList = new ArrayList<>();

        if(type.equals("Post")){
            List<PostLike> postLikeList = likeDAO.getPostLikesListByPostId(itemId);

            for(PostLike like : postLikeList){
                userList.add(like.getPerson().getId());
            }

            if(!userList.isEmpty()){
                response = new ResponseApi("string", System.currentTimeMillis(), new LikeApi.BitLikes(1, userList));
                response.setSuccess(true);
                return response;
            } else {
                response = new ResponseApi("string", System.currentTimeMillis(), new LikeApi.BitLikes(0, userList));
                response.setSuccess(true);
                return response;
            }
        } else if(type.equals("Comment")) {
            //TODO добавить секцию для Лайка Комментариев
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

            Person person = getCurrentPersonFromSecurityContext();
            postLike.setPerson(person);

            Post post = postDAO.getPostById(likeApi.getItem_id());

            if(post == null){
                response = new ErrorApi("invalid_request", "No such post exists");
                response.setSuccess(false);
                return response;
            }
            postLike.setPost(post);

            likeDAO.addPostLike(postLike);

            response = new ResponseApi("string", System.currentTimeMillis(), new LikeApi.BitLikes(1));
            response.setSuccess(true);
            return response;
        }  else if(likeApi.getType().equals("Comment")) {
            //TODO добавить секцию для Лайка Комментариев
        }

        response = new ErrorApi("invalid_request", "Wrong type of entity");
        response.setSuccess(false);
        return response;
    }

    public AbstractResponse removeLike (int itemId, String type){

        AbstractResponse response;

        Person person = getCurrentPersonFromSecurityContext();

        PostLike postLike = likeDAO.getLikedPost(person.getId(), itemId);

        if(type.equals("Post")){

            likeDAO.deletePostLike(postLike);
            response = new ResponseApi("string", System.currentTimeMillis(), new LikeApi.BitLikes(1));
            response.setSuccess(true);
            return response;
        } else if(type.equals("Comment")) {
            //TODO добавить секцию для Лайка Комментариев
        }


        response = new ErrorApi("invalid_request", "Wrong type of entity");
        response.setSuccess(false);
        return response;
    }

    private Person getCurrentPersonFromSecurityContext(){

        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return personDAO.getPersonByEmail(user.getUsername());

    }

}
