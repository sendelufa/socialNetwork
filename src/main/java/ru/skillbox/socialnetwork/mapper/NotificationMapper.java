package ru.skillbox.socialnetwork.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.skillbox.socialnetwork.api.response.NotificationApi;
import ru.skillbox.socialnetwork.model.Notification;
import ru.skillbox.socialnetwork.model.NotificationType;
import ru.skillbox.socialnetwork.model.Person;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class NotificationMapper extends Mapper<Notification, NotificationApi> {

    private final ModelMapper modelMapper;

    @Autowired
    public NotificationMapper(ModelMapper modelMapper) {
        super(Notification.class, NotificationApi.class);
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void setupMapper() {
        modelMapper.createTypeMap(Notification.class, NotificationApi.class)
                .addMappings(m -> m.skip(NotificationApi::setType_id))
                .addMappings(m -> m.skip(NotificationApi::setPerson_id))
                .setPostConverter(toApiConverter());
        modelMapper.createTypeMap(NotificationApi.class, Notification.class)
                .addMappings(m -> m.skip(Notification::setNotificationType))
                .addMappings(m -> m.skip(Notification::setPerson))
                .setPostConverter(toEntityConverter());
    }

    @Override
    void mapSpecificFieldsEA(Notification source, NotificationApi destination) {
        if (Objects.isNull(source)) {
            return;
        }

        if (!Objects.isNull(source.getNotificationType())) {
            destination.setType_id(source.getNotificationType().getId());
        }

        if (!Objects.isNull(source.getPerson())) {
            destination.setPerson_id(source.getPerson().getId());
        }
    }

    @Override
    void mapSpecificFieldsAE(NotificationApi source, Notification destination) {
        if (Objects.isNull(source)) {
            return;
        }

        NotificationType notificationType = new NotificationType();
        notificationType.setId(source.getType_id());
        destination.setNotificationType(notificationType);

        Person person = new Person();
        person.setId(source.getPerson_id());
        destination.setPerson(person);
    }
}
