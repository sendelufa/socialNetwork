package ru.skillbox.socialnetwork.mapper.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.skillbox.socialnetwork.api.response.FriendshipApi;
import ru.skillbox.socialnetwork.config.AppConfig;
import ru.skillbox.socialnetwork.mapper.FriendshipMapper;
import ru.skillbox.socialnetwork.model.Friendship;
import ru.skillbox.socialnetwork.model.FriendshipStatus;
import ru.skillbox.socialnetwork.model.Person;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {AppConfig.class, FriendshipMapper.class})
public class FriendshipMapperTest {

    @Autowired
    private FriendshipMapper friendshipMapper;

    @Test
    public void testEntityToApi()
    {
        Friendship friendship = new Friendship();
        friendship.setId(4564);
        FriendshipStatus status = new FriendshipStatus();
        status.setId(1);
        friendship.setFriendshipStatus(status);
        Person srcPerson = new Person();
        srcPerson.setId(3);
        friendship.setSrcPerson(srcPerson);
        Person dstPerson = new Person();
        dstPerson.setId(2);
        friendship.setDstPerson(dstPerson);

        FriendshipApi friendshipApi = friendshipMapper.toApi(friendship);

        assertEquals(friendship.getId(), friendshipApi.getId());
        assertEquals(friendship.getDstPerson().getId(), friendshipApi.getDst_person_id().intValue());
        assertEquals(friendship.getSrcPerson().getId(), friendshipApi.getSrc_person_id().intValue());
        assertEquals(friendship.getFriendshipStatus().getId(), friendshipApi.getStatus_id().intValue());
    }

    @Test
    public void testApiToEntity()
    {
        FriendshipApi friendshipApi = new FriendshipApi();
        friendshipApi.setId(454);
        friendshipApi.setDst_person_id(657);
        friendshipApi.setSrc_person_id(99045);
        friendshipApi.setStatus_id(5456);
        Friendship friendship = friendshipMapper.toEntity(friendshipApi);

        assertEquals(friendshipApi.getId(), friendship.getId());
        assertEquals(friendshipApi.getDst_person_id().intValue(), friendship.getDstPerson().getId());
        assertEquals(friendshipApi.getSrc_person_id().intValue(), friendship.getSrcPerson().getId());
        assertEquals(friendshipApi.getStatus_id().intValue(), friendship.getFriendshipStatus().getId());
    }
}
