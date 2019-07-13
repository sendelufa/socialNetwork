package ru.skillbox.socialnetwork.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.skillbox.socialnetwork.api.response.LikeApi;
import ru.skillbox.socialnetwork.model.Person;
import ru.skillbox.socialnetwork.model.Post;
import ru.skillbox.socialnetwork.model.PostLike;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class PostLikeMapper extends Mapper<PostLike, LikeApi> {

    private final ModelMapper modelMapper;

    @Autowired
    public PostLikeMapper(ModelMapper modelMapper) {
        super(PostLike.class, LikeApi.class);
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void setupMapper() {
        modelMapper.createTypeMap(PostLike.class, LikeApi.class)
                .addMappings(m -> m.skip(LikeApi::setPerson_id))
                .addMappings(m -> m.skip(LikeApi::setPost_id))
                .setPostConverter(toApiConverter());
        modelMapper.createTypeMap(LikeApi.class, PostLike.class)
                .addMappings(m -> m.skip(PostLike::setPerson))
                .addMappings(m -> m.skip(PostLike::setPost))
                .setPostConverter(toEntityConverter());
    }

    @Override
    void mapSpecificFieldsEA(PostLike source, LikeApi destination) {
        if (Objects.isNull(source)) {
            return;
        }

        if (!Objects.isNull(source.getPerson())) {
            destination.setPerson_id(source.getPerson().getId());
        }

        if (!Objects.isNull(source.getPost())) {
            destination.setPost_id(source.getPost().getId());
        }
    }

    @Override
    void mapSpecificFieldsAE(LikeApi source, PostLike destination) {
        if (Objects.isNull(source)) {
            return;
        }
        Person person = new Person();
        person.setId(source.getPerson_id());
        destination.setPerson(person);

        Post post = new Post();
        post.setId(source.getPost_id());
        destination.setPost(post);
    }
}
