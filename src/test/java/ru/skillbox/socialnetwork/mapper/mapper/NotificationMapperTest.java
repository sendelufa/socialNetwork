package ru.skillbox.socialnetwork.mapper.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.skillbox.socialnetwork.api.response.NotificationApi;
import ru.skillbox.socialnetwork.config.AppConfig;
import ru.skillbox.socialnetwork.mapper.NotificationMapper;
import ru.skillbox.socialnetwork.model.Notification;
import ru.skillbox.socialnetwork.model.NotificationType;
import ru.skillbox.socialnetwork.model.Person;

import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, NotificationMapper.class})
public class NotificationMapperTest {

    @Autowired
    private NotificationMapper notificationMapper;

    @Test
    public void testEntityToApi()
    {
        Notification notification = new Notification();
        notification.setId(8934);
        notification.setContact("34534532");
        notification.setEntityId(67);
        notification.setPerson(new Person());
        notification.getPerson().setId(1);
        notification.setNotificationType(new NotificationType());
        notification.getNotificationType().setId(1);

        GregorianCalendar calendar = new GregorianCalendar(2 - 16, 8, 9);
        Date date = calendar.getTime();
        notification.setSentTime(date);
        NotificationApi notificationApi = notificationMapper.toApi(notification);

        assertEquals(notification.getId(), notificationApi.getId());
        assertEquals(notification.getContact(), notificationApi.getContact());
        assertEquals(notification.getEntityId(), notificationApi.getEntity_id());
        assertEquals(notification.getSentTime().getTime(), notificationApi.getSent_time());
        assertEquals(notification.getPerson().getId(), notificationApi.getPerson_id().intValue());
        assertEquals(notification.getNotificationType().getId(), notificationApi.getType_id().intValue());
    }

    @Test
    public void testApiToEntity()
    {
        NotificationApi notificationApi = new NotificationApi();
        notificationApi.setId(54);
        notificationApi.setContact("546424545");
        notificationApi.setEntity_id(944);
        notificationApi.setInfo("tool");
        notificationApi.setPerson_id(1);
        notificationApi.setType_id(1);
        notificationApi.setSent_time(434736483);
        Notification notification = notificationMapper.toEntity(notificationApi);

        assertEquals(notificationApi.getId(), notification.getId());
        assertEquals(notificationApi.getContact(), notification.getContact());
        assertEquals(notificationApi.getEntity_id(), notification.getEntityId());
        assertEquals(notificationApi.getSent_time(), notification.getSentTime().getTime());
        assertEquals(notificationApi.getPerson_id().intValue(), notification.getPerson().getId());
        assertEquals(notificationApi.getType_id().intValue(), notification.getNotificationType().getId());
    }
}
