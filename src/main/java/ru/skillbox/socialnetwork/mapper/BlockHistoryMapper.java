package ru.skillbox.socialnetwork.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.skillbox.socialnetwork.api.response.BlockHistoryApi;
import ru.skillbox.socialnetwork.model.BlockHistory;
import ru.skillbox.socialnetwork.model.Person;
import ru.skillbox.socialnetwork.model.Post;
import ru.skillbox.socialnetwork.model.PostComment;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class BlockHistoryMapper extends Mapper<BlockHistory, BlockHistoryApi> {

    private final ModelMapper modelMapper;

    @Autowired
    public BlockHistoryMapper(ModelMapper modelMapper) {
        super(BlockHistory.class, BlockHistoryApi.class);
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void setupMapper() {
        modelMapper.createTypeMap(BlockHistory.class, BlockHistoryApi.class)
                .addMappings(m -> m.skip(BlockHistoryApi::setPerson_id))
                .addMappings(m -> m.skip(BlockHistoryApi::setPost_id))
                .addMappings(m -> m.skip(BlockHistoryApi::setComment_id))
                .setPostConverter(toApiConverter());
        modelMapper.createTypeMap(BlockHistoryApi.class, BlockHistory.class)
                .addMappings(m -> m.skip(BlockHistory::setPerson))
                .addMappings(m -> m.skip(BlockHistory::setPost))
                .addMappings(m -> m.skip(BlockHistory::setPostComment))
                .setPostConverter(toEntityConverter());
    }

    @Override
    void mapSpecificFieldsEA(BlockHistory source, BlockHistoryApi destination) {
        if (Objects.isNull(source)) {
            return;
        }
        if (!Objects.isNull(source.getPerson())) {
            destination.setPerson_id(source.getPerson().getId());
        }

        if (!Objects.isNull(source.getPost())) {
            destination.setPost_id(source.getPost().getId());
        }

        if (!Objects.isNull(source.getPostComment())) {
            destination.setComment_id(source.getPostComment().getId());
        }
    }

    @Override
    void mapSpecificFieldsAE(BlockHistoryApi source, BlockHistory destination) {
        if (Objects.isNull(source)) {
            return;
        }

        Person person = new Person();
        person.setId(source.getPerson_id());
        destination.setPerson(person);

        Post post = new Post();
        post.setId(source.getPost_id());
        destination.setPost(post);

        PostComment postComment = new PostComment();
        postComment.setId(source.getComment_id());
        destination.setPostComment(postComment);
    }
}
