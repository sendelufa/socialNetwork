package ru.skillbox.socialnetwork.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.skillbox.socialnetwork.api.response.MessageApi;
import ru.skillbox.socialnetwork.model.Message;

import javax.annotation.PostConstruct;

@Component
public class MessageMapper extends Mapper<Message, MessageApi>
{
    private final ModelMapper modelMapper;

    @Autowired
    public MessageMapper(ModelMapper modelMapper) {
        super(Message.class, MessageApi.class);
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void setupMapper()
    {
        modelMapper.createTypeMap(Message.class, MessageApi.class)
                .addMappings(m -> m.skip(MessageApi::setId)).setPostConverter(toApiConverter());
        modelMapper.createTypeMap(MessageApi.class, Message.class)
                .addMappings(m -> m.skip(Message::setId)).setPostConverter(toEntityConverter());
    }
}
