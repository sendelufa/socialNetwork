package ru.skillbox.socialnetwork.mapper;

import java.util.Objects;
import javax.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.skillbox.socialnetwork.api.response.MessageApi;
import ru.skillbox.socialnetwork.model.Message;
import ru.skillbox.socialnetwork.model.Person;

@Component
public class MessageMapper extends Mapper<Message, MessageApi> {

    private final ModelMapper modelMapper;

    @Autowired
    public MessageMapper(ModelMapper modelMapper) {
        super(Message.class, MessageApi.class);
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void setupMapper() {
        modelMapper.createTypeMap(Message.class, MessageApi.class)
                .addMappings(m -> m.skip(MessageApi::setAuthorId))
                .addMappings(m -> m.skip(MessageApi::setRecipientId))
                .setPostConverter(toApiConverter());
        modelMapper.createTypeMap(MessageApi.class, Message.class)
                .addMappings(m -> m.skip(Message::setAuthor))
                .addMappings(m -> m.skip(Message::setRecipient))
                .setPostConverter(toEntityConverter());
    }

    @Override
    void mapSpecificFieldsEA(Message source, MessageApi destination) {
        if (Objects.isNull(source)) {
            return;
        }

        if (!Objects.isNull(source.getAuthor())) {
            destination.setAuthorId(source.getAuthor().getId());
        }

        if (!Objects.isNull(source.getRecipient())) {
            destination.setRecipientId(source.getRecipient().getId());
        }
    }

    @Override
    void mapSpecificFieldsAE(MessageApi source, Message destination) {
        if (Objects.isNull(source)) {
            return;
        }

        Person author = new Person();
        author.setId(source.getAuthorId());
        destination.setAuthor(author);

        Person recipient = new Person();
        recipient.setId(source.getRecipientId());
        destination.setRecipient(recipient);
    }
}
