package ru.skillbox.socialnetwork.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.skillbox.socialnetwork.api.response.UserApi;
import ru.skillbox.socialnetwork.model.User;

import javax.annotation.PostConstruct;

@Component
public class UserMapper extends Mapper<User, UserApi>
{
    private final ModelMapper modelMapper;

    @Autowired
    public UserMapper(ModelMapper modelMapper) {
        super(User.class, UserApi.class);
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void setupMapper()
    {
        modelMapper.createTypeMap(User.class, UserApi.class)
                .addMappings(m -> m.skip(UserApi::setId)).setPostConverter(toApiConverter());
        modelMapper.createTypeMap(UserApi.class, User.class)
                .addMappings(m -> m.skip(User::setId)).setPostConverter(toEntityConverter());
    }
}
