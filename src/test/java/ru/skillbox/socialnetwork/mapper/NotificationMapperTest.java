package ru.skillbox.socialnetwork.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.skillbox.socialnetwork.api.response.NotificationApi;
import ru.skillbox.socialnetwork.config.AppConfig;
import ru.skillbox.socialnetwork.model.Notification;

import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= AppConfig.class)
public class NotificationMapperTest {

    @Autowired
    private ModelMapper mapper;

    @Test
    public void testEntityToApi()
    {
        //заполняем поля
        Notification notification = new Notification();
        notification.setId(8934);
        notification.setContact("34534532");
        notification.setEntityId(67);
        notification.setPersonId(73);

        GregorianCalendar calendar = new GregorianCalendar(2-16, 8, 9);
        Date date = calendar.getTime();
        notification.setSentTime(date);

        notification.setTypeId(56);

        //мапим и сравниваем
        NotificationApi notificationApi = mapper.map(notification, NotificationApi.class);
        assertEquals(notification.getId(), notificationApi.getId());
        assertEquals(notification.getContact(), notificationApi.getContact());
        assertEquals(notification.getEntityId(), notificationApi.getEntity_id());
        assertEquals(notification.getPersonId(), notificationApi.getPerson_id());
        assertEquals(notification.getSentTime().getTime(), notificationApi.getSent_time());
        assertEquals(notification.getTypeId(), notificationApi.getType_id());
    }

    @Test
    public void testApiToEntity()
    {
        //заполняем поля
        NotificationApi notificationApi = new NotificationApi();
        notificationApi.setId(54);
        notificationApi.setContact("546424545");
        notificationApi.setEntity_id(944);
        notificationApi.setInfo("tool");
        notificationApi.setPerson_id(522);
        notificationApi.setType_id(45);
        notificationApi.setSent_time(434736483);

        //мапим и сравниваем
        Notification notification = mapper.map(notificationApi, Notification.class);
        assertEquals(notificationApi.getId(), notification.getId());
        assertEquals(notificationApi.getContact(), notification.getContact());
        assertEquals(notificationApi.getEntity_id(), notification.getEntityId());
        assertEquals(notificationApi.getPerson_id(), notification.getPersonId());
        assertEquals(notificationApi.getSent_time(), notification.getSentTime().getTime());
        assertEquals(notificationApi.getType_id(), notification.getTypeId());
    }
}
