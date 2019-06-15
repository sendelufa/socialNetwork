package ru.skillbox.socialnetwork.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.skillbox.socialnetwork.api.FriendshipApi;
import ru.skillbox.socialnetwork.model.Friendship;

import javax.annotation.PostConstruct;

@Component
public class FriendshipMapper extends Mapper<Friendship, FriendshipApi>
{
    private final ModelMapper modelMapper;

    @Autowired
    public FriendshipMapper(ModelMapper modelMapper) {
        super(Friendship.class, FriendshipApi.class);
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void setupMapper()
    {
        modelMapper.createTypeMap(Friendship.class, FriendshipApi.class)
                .addMappings(m -> m.skip(FriendshipApi::setId)).setPostConverter(toApiConverter());
        modelMapper.createTypeMap(FriendshipApi.class, Friendship.class)
                .addMappings(m -> m.skip(Friendship::setId)).setPostConverter(toEntityConverter());
    }
}
