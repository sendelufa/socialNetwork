package ru.skillbox.socialnetwork.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.skillbox.socialnetwork.api.response.MessageApi;
import ru.skillbox.socialnetwork.model.Message;
import ru.skillbox.socialnetwork.model.Person;

import javax.annotation.PostConstruct;
import java.util.Objects;

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
                .addMappings(m -> m.skip(MessageApi::setAuthor_id))
                .addMappings(m -> m.skip(MessageApi::setRecipient_id))
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
            destination.setAuthor_id(source.getAuthor().getId());
        }

        if (!Objects.isNull(source.getRecipient())) {
            destination.setRecipient_id(source.getRecipient().getId());
        }
    }

    @Override
    void mapSpecificFieldsAE(MessageApi source, Message destination) {
        if (Objects.isNull(source)) {
            return;
        }

        Person author = new Person();
        author.setId(source.getAuthor_id());
        destination.setAuthor(author);

        Person recipient = new Person();
        recipient.setId(source.getRecipient_id());
        destination.setRecipient(recipient);
    }
}
