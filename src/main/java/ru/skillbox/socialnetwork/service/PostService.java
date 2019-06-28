package ru.skillbox.socialnetwork.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.skillbox.socialnetwork.dao.PostDAO;

public class PostService {

   @Autowired
   private PostDAO postDAO;

}
