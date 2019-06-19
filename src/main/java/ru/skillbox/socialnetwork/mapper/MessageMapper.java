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
    @Autowired
    public MessageMapper() {
        super(Message.class, MessageApi.class);
    }
}
