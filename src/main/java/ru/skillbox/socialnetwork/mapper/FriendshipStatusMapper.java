package ru.skillbox.socialnetwork.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.skillbox.socialnetwork.api.FriendshipStatusApi;
import ru.skillbox.socialnetwork.model.FriendshipStatus;

import javax.annotation.PostConstruct;

@Component
public class FriendshipStatusMapper extends Mapper<FriendshipStatus, FriendshipStatusApi>
{
    private final ModelMapper modelMapper;

    @Autowired
    public FriendshipStatusMapper(ModelMapper modelMapper) {
        super(FriendshipStatus.class, FriendshipStatusApi.class);
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void setupMapper()
    {
        modelMapper.createTypeMap(FriendshipStatus.class, FriendshipStatusApi.class)
                .addMappings(m -> m.skip(FriendshipStatusApi::setId)).setPostConverter(toApiConverter());
        modelMapper.createTypeMap(FriendshipStatusApi.class, FriendshipStatus.class)
                .addMappings(m -> m.skip(FriendshipStatus::setId)).setPostConverter(toEntityConverter());
    }
}
