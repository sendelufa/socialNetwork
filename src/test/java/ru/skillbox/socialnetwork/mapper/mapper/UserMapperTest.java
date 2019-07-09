package ru.skillbox.socialnetwork.mapper.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.skillbox.socialnetwork.api.response.UserApi;
import ru.skillbox.socialnetwork.config.AppConfig;
import ru.skillbox.socialnetwork.model.User;
import ru.skillbox.socialnetwork.model.enumeration.UserType;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= AppConfig.class)
public class UserMapperTest {

    @Autowired
    private ModelMapper mapper;

    @Test
    public void testEntityToApi()
    {
        User user = new User();
        user.setId(2342);
        user.setName("Henry");
        user.setPassword("234125");
        user.setType(UserType.ADMIN);
        user.setEmail("nasasd@mail.ru");
        UserApi userApi = mapper.map(user, UserApi.class);

        assertEquals(user.getName(), userApi.getName());
        assertEquals(user.getType().getType(), userApi.getType().toString());
        assertEquals(user.getEmail(), userApi.getEmail());
        assertEquals(user.getId(), userApi.getId());
    }

    @Test
    public void testApiToEntity()
    {
        UserApi userApi = new UserApi();
        userApi.setEmail("sdfwefvs@mail.ru");
        userApi.setId(2323);
        userApi.setName("Rudik");
        userApi.setType(UserApi.types.MODERATOR);
        User user = mapper.map(userApi, User.class);

        assertEquals(userApi.getId(), user.getId());
        assertEquals(userApi.getEmail(), user.getEmail());
        assertEquals(userApi.getName(), user.getName());
        assertEquals(userApi.getType().toString(), user.getType().getType());
    }
}
