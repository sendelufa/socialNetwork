package ru.skillbox.socialnetwork.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.skillbox.socialnetwork.api.response.MessageApi;
import ru.skillbox.socialnetwork.config.AppConfig;
import ru.skillbox.socialnetwork.model.Message;
import ru.skillbox.socialnetwork.model.enumeration.ReadStatusMessage;

import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= AppConfig.class)
public class MessageMapperTest {

    @Autowired
    private ModelMapper mapper;

    @Test
    public void testEntityToApi()
    {
        //заполняем поля
        Message message = new Message();
        message.setId(843);
        message.setAuthorId(4);
        message.setMessageText("pop");
        message.setReadStatus(ReadStatusMessage.SENT);
        message.setRecipientId(94);

        GregorianCalendar calendar = new GregorianCalendar(2017, 9, 23);
        Date date = calendar.getTime();
        message.setTime(date);

        //мапим и сравниваем
        MessageApi messageApi = mapper.map(message, MessageApi.class);
        assertEquals(message.getId(), messageApi.getId());
        assertEquals(message.getMessageText(), messageApi.getMessage_text());
        assertEquals(message.getAuthorId(), messageApi.getAuthor_id());
        assertEquals(message.getReadStatus().getDescription(), messageApi.getRead_status().toString());
        assertEquals(message.getRecipientId(), messageApi.getRecipient_id());
        assertEquals(message.getTime().getTime(), messageApi.getTime());
    }

    @Test
    public void testApiToEntity()
    {
        //заполняем поля
        MessageApi messageApi = new MessageApi();
        messageApi.setId(89);
        messageApi.setAuthor_id(46);
        messageApi.setMessage_text("rock");
        messageApi.setRead_status(MessageApi.statuses.READ);
        messageApi.setRecipient_id(90);
        messageApi.setTime(8902323);

        //мапим и сравниваем
        Message message = mapper.map(messageApi, Message.class);
        assertEquals(messageApi.getId(), message.getId());
        assertEquals(messageApi.getMessage_text(), message.getMessageText());
        assertEquals(messageApi.getAuthor_id(), message.getAuthorId());
        assertEquals(messageApi.getRead_status().toString(), message.getReadStatus().getDescription());
        assertEquals(messageApi.getRecipient_id(), message.getRecipientId());
        assertEquals(messageApi.getTime(), message.getTime().getTime());
    }
}
