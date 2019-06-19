package ru.skillbox.socialnetwork.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.skillbox.socialnetwork.api.response.FriendshipApi;
import ru.skillbox.socialnetwork.model.Friendship;

import javax.annotation.PostConstruct;

@Component
public class FriendshipMapper extends Mapper<Friendship, FriendshipApi>
{
    @Autowired
    public FriendshipMapper() {
        super(Friendship.class, FriendshipApi.class);
    }


}
