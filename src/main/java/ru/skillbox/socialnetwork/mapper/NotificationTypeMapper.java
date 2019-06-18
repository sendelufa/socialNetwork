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
    private final ModelMapper modelMapper;

    @Autowired
    public NotificationTypeMapper(ModelMapper modelMapper) {
        super(NotificationType.class, NotificationTypeApi.class);
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void setupMapper()
    {
        modelMapper.createTypeMap(NotificationType.class, NotificationTypeApi.class)
                .addMappings(m -> m.skip(NotificationTypeApi::setId)).setPostConverter(toApiConverter());
        modelMapper.createTypeMap(NotificationTypeApi.class, NotificationType.class)
                .addMappings(m -> m.skip(NotificationType::setId)).setPostConverter(toEntityConverter());
    }
}
