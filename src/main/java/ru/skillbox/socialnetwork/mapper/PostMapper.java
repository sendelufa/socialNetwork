package ru.skillbox.socialnetwork.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.skillbox.socialnetwork.api.response.PostApi;
import ru.skillbox.socialnetwork.model.Person;
import ru.skillbox.socialnetwork.model.Post;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class PostMapper extends Mapper<Post, PostApi> {

    private final ModelMapper modelMapper;

    @Autowired
    public PostMapper(ModelMapper modelMapper) {
        super(Post.class, PostApi.class);
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void setupMapper() {
        modelMapper.createTypeMap(Post.class, PostApi.class)
                .addMappings(m -> m.skip(PostApi::setAuthorId))
                .setPostConverter(toApiConverter());
        modelMapper.createTypeMap(PostApi.class, Post.class)
                .addMappings(m -> m.skip(Post::setAuthor))
                .setPostConverter(toEntityConverter());
    }

    @Override
    void mapSpecificFieldsEA(Post source, PostApi destination) {
        if (Objects.isNull(source)) {
            return;
        }

        if (!Objects.isNull(source.getAuthor())) {
            destination.setAuthorId(source.getAuthor().getId());
        }
    }

    @Override
    void mapSpecificFieldsAE(PostApi source, Post destination) {
        if (Objects.isNull(source)) {
            return;
        }
        Person person = new Person();
        person.setId(source.getAuthorId());
        destination.setAuthor(person);
    }
}
