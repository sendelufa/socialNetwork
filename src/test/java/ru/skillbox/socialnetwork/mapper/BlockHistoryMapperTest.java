package ru.skillbox.socialnetwork.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.skillbox.socialnetwork.api.response.BlockHistoryApi;
import ru.skillbox.socialnetwork.config.AppConfig;
import ru.skillbox.socialnetwork.model.BlockHistory;
import ru.skillbox.socialnetwork.model.enumeration.ActionBlockHistory;

import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= AppConfig.class)
public class BlockHistoryMapperTest {

    @Autowired
    private ModelMapper mapper;

    @Test
    public void testEntityToApi()
    {
        //заполняем поля
        BlockHistory blockHistory = new BlockHistory();
        blockHistory.setPerson_id(34);
        blockHistory.setId(23452);
        blockHistory.setAction(ActionBlockHistory.BLOCK);
        blockHistory.setCommentId(3453);
        blockHistory.setPostId(34534);

        GregorianCalendar calendar = new GregorianCalendar(2018, 12, 31);
        Date date = calendar.getTime();
        blockHistory.setTime(date);

        //мапим и сравниваем
        BlockHistoryApi blockHistoryApi = mapper.map(blockHistory, BlockHistoryApi.class);
        assertEquals(blockHistory.getId(), blockHistoryApi.getId());
        assertEquals(blockHistory.getAction().getDescription(), blockHistoryApi.getAction().toString());
        assertEquals(blockHistory.getCommentId(), blockHistoryApi.getComment_id());
        assertEquals(blockHistory.getPerson_id(), blockHistoryApi.getPerson_id());
        assertEquals(blockHistory.getPostId(), blockHistoryApi.getPost_id());
        assertEquals(blockHistory.getTime().getTime(), blockHistoryApi.getTime());
    }

    @Test
    public void testApiToEntity()
    {
        //заполняем поля
        BlockHistoryApi blockHistoryApi = new BlockHistoryApi();
        blockHistoryApi.setAction(BlockHistoryApi.actions.UNBLOCK);
        blockHistoryApi.setComment_id(3445);
        blockHistoryApi.setId(975);
        blockHistoryApi.setPerson_id(5676);
        blockHistoryApi.setPost_id(5467);
        blockHistoryApi.setTime(54564567);

        //мапим и сравниваем
        BlockHistory blockHistory = mapper.map(blockHistoryApi, BlockHistory.class);
        assertEquals(blockHistoryApi.getAction().toString(), blockHistory.getAction().getDescription());
        assertEquals(blockHistoryApi.getComment_id(), blockHistory.getCommentId());
        assertEquals(blockHistoryApi.getId(), blockHistory.getId());
        assertEquals(blockHistoryApi.getPost_id(), blockHistory.getPostId());
        assertEquals(blockHistoryApi.getPerson_id(), blockHistory.getPerson_id());
        assertEquals(blockHistoryApi.getTime(), blockHistory.getTime().getTime());
    }
}
