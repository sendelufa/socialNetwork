package ru.skillbox.socialnetwork.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.skillbox.socialnetwork.api.response.FriendshipStatusApi;
import ru.skillbox.socialnetwork.model.FriendshipStatus;

import javax.annotation.PostConstruct;

@Component
public class FriendshipStatusMapper extends Mapper<FriendshipStatus, FriendshipStatusApi>
{
    @Autowired
    public FriendshipStatusMapper() {
        super(FriendshipStatus.class, FriendshipStatusApi.class);
    }
}
