package ru.skillbox.socialnetwork.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.skillbox.socialnetwork.api.response.CommentApi;
import ru.skillbox.socialnetwork.dao.PersonDAO;
import ru.skillbox.socialnetwork.dao.PostDAO;
import ru.skillbox.socialnetwork.model.PostComment;

import javax.annotation.PostConstruct;

@Component
public class PostCommentMapper extends Mapper<PostComment, CommentApi> {

    private final ModelMapper modelMapper;

    @Autowired
    private PostDAO postDAO;

    @Autowired
    private PersonDAO personDAO;

    @Autowired
    public PostCommentMapper(ModelMapper modelMapper) {
        super(PostComment.class, CommentApi.class);
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void setupMapper() {
        modelMapper.createTypeMap(PostComment.class, CommentApi.class)
                .addMappings(m -> m.skip(CommentApi::setPost_id))
                .addMappings(m -> m.skip(CommentApi::setParent_id))
                .addMappings(m -> m.skip(CommentApi::setAuthor_id))
                .setPostConverter(toApiConverter());
        modelMapper.createTypeMap(CommentApi.class, PostComment.class)
                .addMappings(m -> m.skip(PostComment::setPost))
                .addMappings(m -> m.skip(PostComment::setParent_id))
                .addMappings(m -> m.skip(PostComment::setAuthor))
                .setPostConverter(toEntityConverter());
    }

    @Override
    void mapSpecificFieldsEA(PostComment source, CommentApi destination) {
        destination.setPost_id(String.valueOf(source.getPost().getId()));
        destination.setParent_id(source.getParent_id().getId());
        destination.setAuthor_id(source.getAuthor().getId());
    }

    @Override
    void mapSpecificFieldsAE(CommentApi source, PostComment destination) {
        destination.setPost(postDAO.getPostById(Integer.parseInt(source.getPost_id())));
        destination.setParent_id(postDAO.getCommentById(source.getParent_id()));
        destination.setAuthor(personDAO.getPersonById(source.getAuthor_id()));
    }
}
