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
    private final ModelMapper modelMapper;

    @Autowired
    public PostMapper(ModelMapper modelMapper) {
        super(Post.class, PostApi.class);
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void setupMapper()
    {
        modelMapper.createTypeMap(Post.class, PostApi.class)
                .addMappings(m -> m.skip(PostApi::setId)).setPostConverter(toApiConverter());
        modelMapper.createTypeMap(PostApi.class, Post.class)
                .addMappings(m -> m.skip(Post::setId)).setPostConverter(toEntityConverter());
    }
}
