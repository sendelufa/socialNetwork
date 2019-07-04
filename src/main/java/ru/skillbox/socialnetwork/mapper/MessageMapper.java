package ru.skillbox.socialnetwork.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.skillbox.socialnetwork.api.response.MessageApi;
import ru.skillbox.socialnetwork.dao.PersonDAO;
import ru.skillbox.socialnetwork.model.Message;

import javax.annotation.PostConstruct;

@Component
public class MessageMapper extends Mapper<Message, MessageApi> {

    private final ModelMapper modelMapper;

    @Autowired
    private PersonDAO personDAO;

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
    }

    @Override
    void mapSpecificFieldsEA(Message source, MessageApi destination) {
        destination.setAuthor_id(source.getAuthor().getId());
        destination.setRecipient_id(source.getRecipient().getId());
    }
}
