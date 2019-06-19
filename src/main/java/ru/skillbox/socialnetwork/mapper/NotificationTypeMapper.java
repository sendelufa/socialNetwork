package ru.skillbox.socialnetwork.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.skillbox.socialnetwork.api.response.NotificationTypeApi;
import ru.skillbox.socialnetwork.model.NotificationType;

import javax.annotation.PostConstruct;

@Component
public class NotificationTypeMapper extends Mapper<NotificationType, NotificationTypeApi>
{
    @Autowired
    public NotificationTypeMapper() {
        super(NotificationType.class, NotificationTypeApi.class);
    }
}
