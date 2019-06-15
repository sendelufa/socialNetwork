package ru.skillbox.socialnetwork.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.skillbox.socialnetwork.api.CommentApi;
import ru.skillbox.socialnetwork.model.PostComment;

import javax.annotation.PostConstruct;

@Component
public class CommentMapper extends Mapper<PostComment, CommentApi>
{
    private final ModelMapper modelMapper;

    @Autowired
    public CommentMapper(ModelMapper modelMapper) {
        super(PostComment.class, CommentApi.class);
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void setupMapper()
    {
        modelMapper.createTypeMap(PostComment.class, CommentApi.class)
                .addMappings(m -> m.skip(CommentApi::setId)).setPostConverter(toApiConverter());
        modelMapper.createTypeMap(CommentApi.class, PostComment.class)
                .addMappings(m -> m.skip(PostComment::setId)).setPostConverter(toEntityConverter());
    }
}
