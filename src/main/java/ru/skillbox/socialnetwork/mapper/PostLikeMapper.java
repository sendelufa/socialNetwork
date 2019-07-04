package ru.skillbox.socialnetwork.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.skillbox.socialnetwork.api.response.LikeApi;
import ru.skillbox.socialnetwork.api.response.PostApi;
import ru.skillbox.socialnetwork.dao.PersonDAO;
import ru.skillbox.socialnetwork.dao.PostDAO;
import ru.skillbox.socialnetwork.model.Post;
import ru.skillbox.socialnetwork.model.PostLike;

import javax.annotation.PostConstruct;

@Component
public class PostLikeMapper extends Mapper<PostLike, LikeApi> {

    private final ModelMapper modelMapper;

    @Autowired
    private PostDAO postDAO;

    @Autowired
    private PersonDAO personDAO;

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
    }

    @Override
    void mapSpecificFieldsEA(PostLike source, LikeApi destination) {
        destination.setPerson_id(source.getPerson().getId());
        destination.setPost_id(source.getPost().getId());
    }
}
