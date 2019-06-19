package ru.skillbox.socialnetwork;

import org.junit.Assert;
import org.junit.Test;
import ru.skillbox.socialnetwork.api.response.BlockHistoryApi;
import ru.skillbox.socialnetwork.api.response.PersonApi;
import ru.skillbox.socialnetwork.mapper.BlockHistoryMapper;
import ru.skillbox.socialnetwork.mapper.PersonMapper;
import ru.skillbox.socialnetwork.model.BlockHistory;
import ru.skillbox.socialnetwork.model.Person;

public class MappersTests
{
    @Test
    public void mapperTest()
    {
        PersonMapper personMapper = new PersonMapper();
        Person person = new Person();
        person.setId(6);
        PersonApi personApi = personMapper.toApi(person);
        Assert.assertEquals(person.getId(), personApi.getId());
    }
}
