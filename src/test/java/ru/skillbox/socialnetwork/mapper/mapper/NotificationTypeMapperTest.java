package ru.skillbox.socialnetwork.mapper.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.skillbox.socialnetwork.api.response.NotificationTypeApi;
import ru.skillbox.socialnetwork.config.AppConfig;
import ru.skillbox.socialnetwork.model.NotificationType;
import ru.skillbox.socialnetwork.model.enumeration.NameNotificationType;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= AppConfig.class)
public class NotificationTypeMapperTest {

    @Autowired
    ModelMapper mapper;

    @Test
    public void testEntityToApi() {
        NotificationType notificationType = new NotificationType();
        notificationType.setId(7);
        notificationType.setCode(101);
        notificationType.setName(NameNotificationType.POST);
        NotificationTypeApi notificationTypeApi = mapper.map(notificationType, NotificationTypeApi.class);

        assertEquals(notificationType.getId(), notificationTypeApi.getId());
        assertEquals(notificationType.getName().getDescription(), notificationTypeApi.getName().toString());
        assertEquals(String.valueOf(notificationType.getCode()), notificationTypeApi.getCode());
    }
//    @Test
//    public void testApiToEntity()
//    {
//        NotificationTypeApi notificationTypeApi = new NotificationTypeApi();
//        notificationTypeApi.setName(NotificationTypeApi.codes.COMMENT_COMMENT);
//        notificationTypeApi.setId(5);
//        notificationTypeApi.setCode("103");
//        NotificationType notificationType = mapper.map(notificationTypeApi, NotificationType.class);

//        assertEquals(notificationTypeApi.getId(), notificationType.getId());
//        assertEquals(notificationTypeApi.getCode(), String.valueOf(notificationType.getCode()));
//        assertEquals(notificationTypeApi.getName().toString(), notificationType.getName().getDescription());
//    }
}
