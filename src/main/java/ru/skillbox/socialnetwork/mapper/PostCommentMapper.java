package ru.skillbox.socialnetwork.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.skillbox.socialnetwork.api.response.AuthorApi;
import ru.skillbox.socialnetwork.api.response.CommentApi;
import ru.skillbox.socialnetwork.api.response.SubCommentApi;
import ru.skillbox.socialnetwork.model.Person;
import ru.skillbox.socialnetwork.model.Post;
import ru.skillbox.socialnetwork.model.PostComment;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class PostCommentMapper extends Mapper<PostComment, CommentApi> {

    private final ModelMapper modelMapper;

    @Autowired
    private SubCommentMapper subCommentMapper;

    @Autowired
    public PostCommentMapper(ModelMapper modelMapper) {
        super(PostComment.class, CommentApi.class);
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void setupMapper() {
        modelMapper.createTypeMap(PostComment.class, CommentApi.class)
                .addMappings(m -> m.skip(CommentApi::setPostId))
                .addMappings(m -> m.skip(CommentApi::setParentId))
                .addMappings(m -> m.skip(CommentApi::setAuthorId))
                .addMappings(m -> m.skip(CommentApi::setSubComments))
                .setPostConverter(toApiConverter());
        modelMapper.createTypeMap(CommentApi.class, PostComment.class)
                .addMappings(m -> m.skip(PostComment::setPost))
                .addMappings(m -> m.skip(PostComment::setParent_id))
                .addMappings(m -> m.skip(PostComment::setAuthor))
                .addMappings(m -> m.skip(PostComment::setPostComments))
                .setPostConverter(toEntityConverter());
    }

    @Override
    void mapSpecificFieldsEA(PostComment source, CommentApi destination) {
        if (Objects.isNull(source)) {
            return;
        }

        if (!Objects.isNull(source.getPost())) {
            destination.setPostId(String.valueOf(source.getPost().getId()));
        }

        if (!Objects.isNull(source.getParent())) {
            destination.setParentId(source.getParent().getId());
        }

        if (!Objects.isNull(source.getAuthor())) {
            destination.setAuthorId(source.getAuthor().getId());
            destination.setAuthor(modelMapper.map(source.getAuthor(), AuthorApi.class));
        }

        destination.setIs_deleted(source.isDeleted());

        if (!Objects.isNull(source.getPostComments())) {
            List<SubCommentApi> subCommentApiList = new ArrayList<>();
            for (PostComment postComment : source.getPostComments()) {
                subCommentApiList.add(subCommentMapper.toApi(postComment));
            }
            destination.setSubComments(subCommentApiList);
        }
    }

    @Override
    void mapSpecificFieldsAE(CommentApi source, PostComment destination) {
        if (Objects.isNull(source)) {
            return;
        }

        Post post = new Post();
        post.setId(Integer.parseInt(source.getPostId()));
        destination.setPost(post);

        PostComment postParent = new PostComment();
        postParent.setId(source.getParentId());
        destination.setParent_id(postParent);

        Person person = new Person();
        person.setId(source.getAuthorId());
        destination.setAuthor(person);

        if (!Objects.isNull(source.getSubComments())) {
            List<PostComment> postCommentList = new ArrayList<>();
            for (SubCommentApi subCommentApi : source.getSubComments()) {
                postCommentList.add(subCommentMapper.toEntity(subCommentApi));
            }
            destination.setPostComments(postCommentList);
        }
    }
}
