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

        Criteria criteria = getCurrentSession().createCriteria(PostLike.class);
        criteria.add(Restrictions.eq("post", id));

        return (List<PostLike>) criteria.list();
    }

    public PostLike getLikedPost(int userId, int postId){
        Criteria criteria = getCurrentSession().createCriteria(PostLike.class);
        criteria.add(Restrictions.eq("post", postId));
        criteria.add(Restrictions.eq("person", userId));

        return (PostLike) criteria.uniqueResult();
    }

//    public PostLike getPostLikeByPostId(int id) {
//        Criteria criteria = getCurrentSession().createCriteria(PostLike.class);
//        criteria.add(Restrictions.eq("post", id));
//
//        return (PostLike) criteria.uniqueResult();
//    }

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
