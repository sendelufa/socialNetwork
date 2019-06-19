package ru.skillbox.socialnetwork.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.skillbox.socialnetwork.api.response.NotificationApi;
import ru.skillbox.socialnetwork.model.Notification;

import javax.annotation.PostConstruct;

@Component
public class NotificationMapper extends Mapper<Notification, NotificationApi>
{
    @Autowired
    public NotificationMapper() {
        super(Notification.class, NotificationApi.class);
    }
}
