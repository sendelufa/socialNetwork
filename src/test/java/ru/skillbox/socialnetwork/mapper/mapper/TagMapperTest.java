package ru.skillbox.socialnetwork.mapper.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.skillbox.socialnetwork.api.response.TagApi;
import ru.skillbox.socialnetwork.config.AppConfig;
import ru.skillbox.socialnetwork.model.Tag;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= AppConfig.class)
public class TagMapperTest {

    @Autowired
    private ModelMapper mapper;

    @Test
    public void testEntityToApi()
    {
        Tag tag = new Tag();
        tag.setId(232);
        tag.setTag("#sochi");
        TagApi tagApi = mapper.map(tag, TagApi.class);

        assertEquals(tag.getId(), tagApi.getId());
        assertEquals(tag.getTag(), tagApi.getTag());
    }

    @Test
    public void testApiToEntity()
    {
        TagApi tagApi = new TagApi();
        tagApi.setId(2342);
        tagApi.setTag("#cool");
        Tag tag = mapper.map(tagApi, Tag.class);

        assertEquals(tagApi.getId(), tag.getId());
        assertEquals(tagApi.getTag(), tag.getTag());
    }
}
