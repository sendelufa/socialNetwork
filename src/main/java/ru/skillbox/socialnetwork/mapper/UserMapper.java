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
    @Autowired
    public UserMapper() {
        super(User.class, UserApi.class);
    }
}
