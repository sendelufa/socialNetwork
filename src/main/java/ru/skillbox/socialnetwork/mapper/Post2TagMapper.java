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
    private final ModelMapper modelMapper;

    @Autowired
    public Post2TagMapper(ModelMapper modelMapper) {
        super(Post2tag.class, Post2TagApi.class);
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void setupMapper()
    {
        modelMapper.createTypeMap(Post2tag.class, Post2TagApi.class)
                .addMappings(m -> m.skip(Post2TagApi::setId)).setPostConverter(toApiConverter());
        modelMapper.createTypeMap(Post2TagApi.class, Post2tag.class)
                .addMappings(m -> m.skip(Post2tag::setId)).setPostConverter(toEntityConverter());
    }
}
