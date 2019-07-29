package ru.skillbox.socialnetwork.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.skillbox.socialnetwork.model.Dialog;

import java.util.List;

@Component
public class DialogDao {

    @Autowired
    SessionFactory sessionFactory;

    public Dialog getDialogById(int id){
        return getCurrentSession().get(Dialog.class, id);
    }

    public List<Dialog> getDialogsWithParameters(String query, int offset, int itemPerPage){
        Query q = getCurrentSession().createQuery(query);
        q.setFirstResult(offset);
        q.setMaxResults(itemPerPage);

        return q.list();
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }


    public void updateDialog(Dialog dialog) {
        getCurrentSession().update(dialog);
    }

    public void addDialog(Dialog dialog) {
        getCurrentSession().save(dialog);
    }
}
