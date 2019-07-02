package ru.skillbox.socialnetwork.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.skillbox.socialnetwork.api.response.PostApi;
import ru.skillbox.socialnetwork.model.Post;

import java.util.Objects;

public class PostMapper extends Mapper<Post, PostApi> {

    public PostMapper(ModelMapper mapper) {
        super(Post.class, PostApi.class, mapper);
        this.mapper = mapper;
    }

    @Override
    public PostApi toApi(Post entity) {
        PostApi result = super.toApi(entity);
        result.getData().setAuthorId(Objects.isNull(entity) || Objects.isNull(entity.getAuthor())
                ? null
                : entity.getAuthor().getId());
        return result;
    }

    // TODO: Здесь Надо как-то переопределять, вроде нам нигде не требуется переводить api в entity?
    @Override
    public Post toEntity(PostApi api) {
        return super.toEntity(api);
    }
}
