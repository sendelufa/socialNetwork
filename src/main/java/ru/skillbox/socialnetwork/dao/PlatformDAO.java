package ru.skillbox.socialnetwork.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class PlatformDAO {

    @Autowired
    SessionFactory sessionFactory;
}
