package ru.skillbox.socialnetwork.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.skillbox.socialnetwork.api.request.PostCommentApi;
import ru.skillbox.socialnetwork.api.response.AuthorApi;
import ru.skillbox.socialnetwork.api.response.CommentApi;
import ru.skillbox.socialnetwork.api.response.PostApi;
import ru.skillbox.socialnetwork.model.Person;
import ru.skillbox.socialnetwork.model.Post;
import ru.skillbox.socialnetwork.model.PostComment;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class PostMapper extends Mapper<Post, PostApi> {

    private final ModelMapper modelMapper;

    @Autowired
    private PostCommentMapper postCommentMapper;

    @Autowired
    public PostMapper(ModelMapper modelMapper) {
        super(Post.class, PostApi.class);
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void setupMapper() {
        modelMapper.createTypeMap(Post.class, PostApi.class)
                .addMappings(m -> m.skip(PostApi::setAuthor))
                .addMappings(m -> m.skip(PostApi::setComments))
                .setPostConverter(toApiConverter());
        modelMapper.createTypeMap(PostApi.class, Post.class)
                .addMappings(m -> m.skip(Post::setAuthor))
                .addMappings(m -> m.skip(Post::setPostComments))
                .setPostConverter(toEntityConverter());
    }

    @Override
    void mapSpecificFieldsEA(Post source, PostApi destination) {
        if (Objects.isNull(source)) {
            return;
        }

        if (!Objects.isNull(source.getAuthor())) {
            destination.setAuthor(modelMapper.map(source.getAuthor(), AuthorApi.class));
        }

        if (!Objects.isNull(source.getPostComments())) {
            List<CommentApi> commentApiList = new ArrayList<>();
            for (PostComment postComment : source.getPostComments()) {
                commentApiList.add(postCommentMapper.toApi(postComment));
            }
            destination.setComments(commentApiList);
        }
    }

    @Override
    void mapSpecificFieldsAE(PostApi source, Post destination) {
        if (Objects.isNull(source)) {
            return;
        }
        Person person = new Person();
        person.setId(source.getAuthor().getId());
        destination.setAuthor(person);

        if (!Objects.isNull(source.getComments())) {
            List<PostComment> postCommentList = new ArrayList<>();
            for (CommentApi commentApi : source.getComments()) {
                postCommentList.add(postCommentMapper.toEntity(commentApi));
            }
            destination.setPostComments(postCommentList);
        }


    }
}
