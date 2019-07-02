package ru.skillbox.socialnetwork.mapper.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.skillbox.socialnetwork.api.response.Post2TagApi;
import ru.skillbox.socialnetwork.config.AppConfig;
import ru.skillbox.socialnetwork.model.Post2tag;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= AppConfig.class)
public class Post2tagMapperTest {

    @Autowired
    private ModelMapper mapper;

    @Test
    public void testEntityToApi()
    {
        Post2tag post2tag = new Post2tag();
        post2tag.setId(5);
        post2tag.setPostId(3);
        post2tag.setTagId(9);
        Post2TagApi post2TagApi = mapper.map(post2tag, Post2TagApi.class);

        assertEquals(post2tag.getId(), post2TagApi.getId());
        assertEquals(post2tag.getPostId(), post2TagApi.getPost_id());
        assertEquals(post2tag.getTagId(), post2TagApi.getTag_id());
    }

    @Test
    public void testApiToEntity()
    {
        Post2TagApi post2TagApi = new Post2TagApi();
        post2TagApi.setId(86);
        post2TagApi.setPost_id(233);
        post2TagApi.setTag_id(34521);
        Post2tag post2tag = mapper.map(post2TagApi, Post2tag.class);

        assertEquals(post2TagApi.getId(), post2tag.getId());
        assertEquals(post2TagApi.getPost_id(), post2tag.getPostId());
        assertEquals(post2TagApi.getTag_id(), post2tag.getTagId());
    }
}
