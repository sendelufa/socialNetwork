package ru.skillbox.socialnetwork.dao;

import org.hibernate.NonUniqueResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.skillbox.socialnetwork.model.CommentLike;
import ru.skillbox.socialnetwork.model.PostLike;
import java.util.List;

@Repository
@Transactional
public class LikeDAO {

    @Autowired
    SessionFactory sessionFactory;


    public List<PostLike> getPostLikesListByPostId(int id){

         String query = String.format("from PostLike likes where likes.post=%d", id);
         return (List<PostLike>) getCurrentSession().createQuery(query).list();
    }

    public PostLike getLikedPost(int userId, int postId){

        String query = String.format("from PostLike likes where likes.post=%d and likes.person=%d", postId,userId);

        try {
            return (PostLike) getCurrentSession().createQuery(query).uniqueResult();
        } catch(NonUniqueResultException e){
            return (PostLike) getCurrentSession().createQuery(query).list().get(0);
        }
    }


    public void addPostLike(PostLike postLike) {
        getCurrentSession().save(postLike);
    }

    public void updatePostLike(PostLike postLike){
        getCurrentSession().update(postLike);
    }

    public void deletePostLike(PostLike postLike) {
        getCurrentSession().delete(postLike);
    }

    public List<CommentLike> getCommentLikesListByCommentId(int id){

        String query = String.format("from CommentLike likes where likes.postComment=%d", id);
        return (List<CommentLike>) getCurrentSession().createQuery(query).list();
    }

    public CommentLike getLikedComment(int userId, int postCommentId){

        String query = String.format("from CommentLike likes where likes.postComment=%d and likes.person=%d", postCommentId,userId);

        try {
            return (CommentLike) getCurrentSession().createQuery(query).uniqueResult();
        } catch(NonUniqueResultException e){
            return (CommentLike) getCurrentSession().createQuery(query).list().get(0);
        }
    }


    public void addCommentLike(CommentLike postCommentLike) {
        getCurrentSession().save(postCommentLike);
    }

    public void updateCommentLike(CommentLike postCommentLike){
        getCurrentSession().update(postCommentLike);
    }

    public void deleteCommentLike(CommentLike postCommentLike) {
        getCurrentSession().delete(postCommentLike);
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
