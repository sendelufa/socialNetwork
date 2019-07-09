package ru.skillbox.socialnetwork.mapper.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.skillbox.socialnetwork.api.response.FriendshipApi;
import ru.skillbox.socialnetwork.config.AppConfig;
import ru.skillbox.socialnetwork.model.Friendship;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= AppConfig.class)
public class FriendshipMapperTest {

    @Autowired
    private ModelMapper mapper;

    @Test
    public void testEntityToApi()
    {
        Friendship friendship = new Friendship();
        friendship.setId(4564);
        FriendshipApi friendshipApi = mapper.map(friendship, FriendshipApi.class);

        assertEquals(friendship.getId(), friendshipApi.getId());
    }

    @Test
    public void testApiToEntity()
    {
        FriendshipApi friendshipApi = new FriendshipApi();
        friendshipApi.setId(454);
        friendshipApi.setDst_person_id(657);
        friendshipApi.setSrc_person_id(99045);
        friendshipApi.setStatus_id(5456);
        Friendship friendship = mapper.map(friendshipApi, Friendship.class);

        assertEquals(friendshipApi.getId(), friendship.getId());
        assertEquals(friendshipApi.getDst_person_id(), friendshipApi.getDst_person_id());
    }
}
