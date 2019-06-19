package ru.skillbox.socialnetwork.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.skillbox.socialnetwork.api.response.CommentApi;
import ru.skillbox.socialnetwork.model.PostComment;

import javax.annotation.PostConstruct;

@Component
public class CommentMapper extends Mapper<PostComment, CommentApi>
{
    @Autowired
    public CommentMapper()
    {
        super(PostComment.class, CommentApi.class);
    }
}
