package ru.skillbox.socialnetwork.mapper.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.skillbox.socialnetwork.api.response.MessageApi;
import ru.skillbox.socialnetwork.config.AppConfig;
import ru.skillbox.socialnetwork.model.Message;
import ru.skillbox.socialnetwork.model.Person;
import ru.skillbox.socialnetwork.model.enumeration.ReadStatusMessage;

import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

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
        assertEquals(message.getMessageText(), messageApi.getMessage_text());
        assertEquals(message.getReadStatus().toString(), messageApi.getRead_status().toString());
        assertEquals(message.getTime().getTime(), messageApi.getTime());
        assertEquals(message.getAuthor().getId(), messageApi.getAuthor_id().intValue());
        assertEquals(message.getRecipient().getId(), messageApi.getRecipient_id().intValue());
    }

    @Test
    public void testApiToEntity()
    {
        MessageApi messageApi = new MessageApi();
        messageApi.setId(89);
        messageApi.setAuthor_id(1);
        messageApi.setMessage_text("rock");
        messageApi.setRead_status(MessageApi.statuses.READ);
        messageApi.setRecipient_id(2);
        messageApi.setTime(8902323);
        Message message = messageMapper.toEntity(messageApi);

        assertEquals(messageApi.getId(), message.getId());
        assertEquals(messageApi.getMessage_text(), message.getMessageText());
        assertEquals(messageApi.getRead_status().toString(), message.getReadStatus().toString());
        assertEquals(messageApi.getTime(), message.getTime().getTime());
        assertEquals(messageApi.getAuthor_id().intValue(), message.getAuthor().getId());
        assertEquals(messageApi.getRecipient_id().intValue(), message.getRecipient().getId());
    }
}
