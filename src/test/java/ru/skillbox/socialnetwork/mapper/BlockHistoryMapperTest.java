package ru.skillbox.socialnetwork.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.skillbox.socialnetwork.api.response.BlockHistoryApi;
import ru.skillbox.socialnetwork.config.AppConfig;
import ru.skillbox.socialnetwork.config.HibernateConf;
import ru.skillbox.socialnetwork.dao.PersonDAO;
import ru.skillbox.socialnetwork.dao.PostDAO;
import ru.skillbox.socialnetwork.model.BlockHistory;
import ru.skillbox.socialnetwork.model.Person;
import ru.skillbox.socialnetwork.model.Post;
import ru.skillbox.socialnetwork.model.enumeration.ActionBlockHistory;

import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, BlockHistoryMapper.class, PersonDAO.class, PostDAO.class, HibernateConf.class})
@Transactional("platformTransactionManager")
public class BlockHistoryMapperTest {

    @Autowired
    private BlockHistoryMapper blockHistoryMapper;

    @Test
    public void testEntityToApi() {
        //заполняем поля
        BlockHistory blockHistory = new BlockHistory();
        blockHistory.setId(23452);
        blockHistory.setAction(ActionBlockHistory.BLOCK);
        blockHistory.setPerson(new Person());
        blockHistory.getPerson().setId(1);
        blockHistory.setPost(new Post());
        blockHistory.getPost().setId(1);


        GregorianCalendar calendar = new GregorianCalendar(2018, 12, 31);
        Date date = calendar.getTime();
        blockHistory.setTime(date);

        //мапим и сравниваем
        BlockHistoryApi blockHistoryApi = blockHistoryMapper.toApi(blockHistory);
        assertEquals(blockHistory.getId(), blockHistoryApi.getId());
        assertEquals(blockHistory.getAction().toString(), blockHistoryApi.getAction().toString());
        assertEquals(blockHistory.getTime().getTime(), blockHistoryApi.getTime());
        assertEquals(blockHistory.getPerson().getId(), blockHistoryApi.getPerson_id().intValue());
        assertEquals(blockHistory.getPost().getId(), blockHistoryApi.getPost_id().intValue());
    }

    @Test
    public void testApiToEntity() {
        //заполняем поля
        BlockHistoryApi blockHistoryApi = new BlockHistoryApi();
        blockHistoryApi.setAction(BlockHistoryApi.actions.UNBLOCK);
        blockHistoryApi.setComment_id(1);
        blockHistoryApi.setId(975);
        blockHistoryApi.setPerson_id(1);
        blockHistoryApi.setPost_id(1);
        blockHistoryApi.setTime(54564567);

        //мапим и сравниваем
        BlockHistory blockHistory = blockHistoryMapper.toEntity(blockHistoryApi);
        assertEquals(blockHistoryApi.getAction().toString(), blockHistory.getAction().toString());
        assertEquals(blockHistoryApi.getId(), blockHistory.getId());
        assertEquals(blockHistoryApi.getTime(), blockHistory.getTime().getTime());
        assertEquals(blockHistoryApi.getPerson_id().intValue(), blockHistory.getPerson().getId());
        assertEquals(blockHistoryApi.getPost_id().intValue(), blockHistory.getPost().getId());
    }
}
