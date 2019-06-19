package ru.skillbox.socialnetwork.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.skillbox.socialnetwork.api.response.Post2TagApi;
import ru.skillbox.socialnetwork.model.Post2tag;

import javax.annotation.PostConstruct;

@Component
public class Post2TagMapper extends Mapper<Post2tag, Post2TagApi>
{
    @Autowired
    public Post2TagMapper() {
        super(Post2tag.class, Post2TagApi.class);
    }
}
