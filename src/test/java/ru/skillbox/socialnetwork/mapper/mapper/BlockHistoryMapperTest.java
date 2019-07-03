package ru.skillbox.socialnetwork.mapper.mapper;

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
        BlockHistory blockHistory = new BlockHistory();
        blockHistory.setId(23452);
        blockHistory.setAction(ActionBlockHistory.BLOCK);
        GregorianCalendar calendar = new GregorianCalendar(2018, 12, 31);
        Date date = calendar.getTime();
        blockHistory.setTime(date);
        BlockHistoryApi blockHistoryApi = mapper.map(blockHistory, BlockHistoryApi.class);

        assertEquals(blockHistory.getId(), blockHistoryApi.getId());
        assertEquals(blockHistory.getAction().getDescription(), blockHistoryApi.getAction().toString());
        assertEquals(blockHistory.getTime().getTime(), blockHistoryApi.getTime());
    }

    @Test
    public void testApiToEntity()
    {
        BlockHistoryApi blockHistoryApi = new BlockHistoryApi();
        blockHistoryApi.setAction(BlockHistoryApi.actions.UNBLOCK);
        blockHistoryApi.setComment_id(3445);
        blockHistoryApi.setId(975);
        blockHistoryApi.setPerson_id(5676);
        blockHistoryApi.setPost_id(5467);
        blockHistoryApi.setTime(54564567);
        BlockHistory blockHistory = mapper.map(blockHistoryApi, BlockHistory.class);

        assertEquals(blockHistoryApi.getAction().toString(), blockHistory.getAction().getDescription());
        assertEquals(blockHistoryApi.getId(), blockHistory.getId());
        assertEquals(blockHistoryApi.getTime(), blockHistory.getTime().getTime());
    }
}