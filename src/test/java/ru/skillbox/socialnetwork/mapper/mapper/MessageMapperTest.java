package ru.skillbox.socialnetwork.mapper.mapper;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.GregorianCalendar;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.skillbox.socialnetwork.api.response.MessageApi;
import ru.skillbox.socialnetwork.api.response.MessageApi.readStatuses;
import ru.skillbox.socialnetwork.config.AppConfig;
import ru.skillbox.socialnetwork.mapper.MessageMapper;
import ru.skillbox.socialnetwork.model.Message;
import ru.skillbox.socialnetwork.model.Person;
import ru.skillbox.socialnetwork.model.enumeration.ReadStatusMessage;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, MessageMapper.class})
public class MessageMapperTest {

    @Autowired
    private MessageMapper messageMapper;

    @Test
    public void testEntityToApi()
    {
        Message message = new Message();
        message.setId(843);
        message.setMessageText("pop");
        message.setReadStatus(ReadStatusMessage.SENT);
        message.setAuthor(new Person());
        message.setRecipient(new Person());
        message.getAuthor().setId(1);
        message.getRecipient().setId(2);

        GregorianCalendar calendar = new GregorianCalendar(2017, 9, 23);
        Date date = calendar.getTime();
        message.setTime(date);
        MessageApi messageApi = messageMapper.toApi(message);

        assertEquals(message.getId(), messageApi.getId());
        assertEquals(message.getMessageText(), messageApi.getMessageText());
        assertEquals(message.getReadStatus().toString(), messageApi.getReadStatus().toString());
        assertEquals(message.getTime().getTime(), messageApi.getTime());
        assertEquals(message.getAuthor().getId(), messageApi.getAuthorId().intValue());
        assertEquals(message.getRecipient().getId(), messageApi.getRecipientId().intValue());
    }

    @Test
    public void testApiToEntity()
    {
        MessageApi messageApi = new MessageApi();
        messageApi.setId(89);
        messageApi.setAuthorId(1);
        messageApi.setMessageText("rock");
        messageApi.setReadStatus(readStatuses.READ);
        messageApi.setRecipientId(2);
        messageApi.setTime(8902323);
        Message message = messageMapper.toEntity(messageApi);

        assertEquals(messageApi.getId(), message.getId());
        assertEquals(messageApi.getMessageText(), message.getMessageText());
        assertEquals(messageApi.getReadStatus().toString(), message.getReadStatus().toString());
        assertEquals(messageApi.getTime(), message.getTime().getTime());
        assertEquals(messageApi.getAuthorId().intValue(), message.getAuthor().getId());
        assertEquals(messageApi.getRecipientId().intValue(), message.getRecipient().getId());
    }
}
