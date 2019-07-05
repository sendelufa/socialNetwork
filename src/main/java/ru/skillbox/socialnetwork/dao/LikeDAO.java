package ru.skillbox.socialnetwork.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
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

        return (PostLike) getCurrentSession().createQuery(query).uniqueResult();
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

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
