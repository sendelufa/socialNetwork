package ru.skillbox.socialnetwork.mapper.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.skillbox.socialnetwork.api.response.FriendshipStatusApi;
import ru.skillbox.socialnetwork.config.AppConfig;
import ru.skillbox.socialnetwork.model.FriendshipStatus;
import ru.skillbox.socialnetwork.model.enumeration.CodeFriendshipStatus;

import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= AppConfig.class)
public class FriendshipStatusMapperTest {

    @Autowired
    private ModelMapper mapper;

    @Test
    public void testEntityToApi()
    {
        FriendshipStatus friendshipStatus = new FriendshipStatus();
        friendshipStatus.setId(5677);
        friendshipStatus.setCode(CodeFriendshipStatus.REQUEST);
        friendshipStatus.setName("Serega");
        GregorianCalendar calendar = new GregorianCalendar(2019, 5, 8);
        Date date = calendar.getTime();
        friendshipStatus.setTime(date);
        FriendshipStatusApi friendshipStatusApi = mapper.map(friendshipStatus, FriendshipStatusApi.class);

        assertEquals(friendshipStatus.getId(), friendshipStatusApi.getId());
        assertEquals(friendshipStatus.getCode().getDescription(), friendshipStatusApi.getCode().toString());
        assertEquals(friendshipStatus.getName(), friendshipStatusApi.getName());
        assertEquals(friendshipStatus.getTime().getTime(), friendshipStatusApi.getTime());
    }

    @Test
    public void testApiToEntity()
    {
        FriendshipStatusApi friendshipStatusApi = new FriendshipStatusApi();
        friendshipStatusApi.setId(96786);
        friendshipStatusApi.setCode(FriendshipStatusApi.codes.BLOCKED);
        friendshipStatusApi.setName("Elena");
        friendshipStatusApi.setTime(89846373);
        FriendshipStatus friendshipStatus = mapper.map(friendshipStatusApi, FriendshipStatus.class);

        assertEquals(friendshipStatusApi.getId(), friendshipStatus.getId());
        assertEquals(friendshipStatusApi.getCode().toString(), friendshipStatus.getCode().getDescription());
        assertEquals(friendshipStatusApi.getName(), friendshipStatus.getName());
        assertEquals(friendshipStatusApi.getTime(), friendshipStatus.getTime().getTime());
    }
}
