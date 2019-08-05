package ru.skillbox.socialnetwork.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.skillbox.socialnetwork.api.response.AuthorApi;
import ru.skillbox.socialnetwork.api.response.SubCommentApi;
import ru.skillbox.socialnetwork.model.Person;
import ru.skillbox.socialnetwork.model.PostComment;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class SubCommentMapper extends Mapper<PostComment, SubCommentApi> {

    private final ModelMapper modelMapper;

    @Autowired
    public SubCommentMapper(ModelMapper modelMapper) {
        super(PostComment.class, SubCommentApi.class);
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void setupMapper() {
        modelMapper.createTypeMap(PostComment.class, SubCommentApi.class)
                .addMappings(m -> m.skip(SubCommentApi::setParentId))
                .addMappings(m -> m.skip(SubCommentApi::setAuthor))
                .setPostConverter(toApiConverter());
        modelMapper.createTypeMap(SubCommentApi.class, PostComment.class)
                .addMappings(m -> m.skip(PostComment::setPost))
                .addMappings(m -> m.skip(PostComment::setParent_id))
                .addMappings(m -> m.skip(PostComment::setAuthor))
                .addMappings(m -> m.skip(PostComment::setPostComments))
                .setPostConverter(toEntityConverter());
    }

    @Override
    void mapSpecificFieldsEA(PostComment source, SubCommentApi destination) {
        if (Objects.isNull(source)) {
            return;
        }

        if (!Objects.isNull(source.getParent())) {
            destination.setParentId(source.getParent().getId());
        }

        if (!Objects.isNull(source.getAuthor())) {
            destination.setAuthor(modelMapper.map(source.getAuthor(), AuthorApi.class));
        }
    }

    @Override
    void mapSpecificFieldsAE(SubCommentApi source, PostComment destination) {
        if (!Objects.isNull(source)) {
            return;
        }

        PostComment postParent = new PostComment();
        postParent.setId(source.getParentId());
        destination.setParent_id(postParent);

        Person person = new Person();
        person.setId(source.getAuthor().getId());
        destination.setAuthor(person);
    }
}
