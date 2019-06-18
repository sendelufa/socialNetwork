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
    private final ModelMapper modelMapper;

    @Autowired
    public NotificationMapper(ModelMapper modelMapper) {
        super(Notification.class, NotificationApi.class);
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void setupMapper()
    {
        modelMapper.createTypeMap(Notification.class, NotificationApi.class)
                .addMappings(m -> m.skip(NotificationApi::setId)).setPostConverter(toApiConverter());
        modelMapper.createTypeMap(NotificationApi.class, Notification.class)
                .addMappings(m -> m.skip(Notification::setId)).setPostConverter(toEntityConverter());
    }
}
