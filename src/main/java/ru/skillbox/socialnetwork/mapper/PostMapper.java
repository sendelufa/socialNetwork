package ru.skillbox.socialnetwork.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.skillbox.socialnetwork.api.response.PostApi;
import ru.skillbox.socialnetwork.model.Post;

import javax.annotation.PostConstruct;

@Component
public class PostMapper extends Mapper<Post, PostApi>
{
    @Autowired
    public PostMapper() {
        super(Post.class, PostApi.class);
    }
}
