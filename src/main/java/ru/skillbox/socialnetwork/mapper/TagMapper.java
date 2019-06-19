package ru.skillbox.socialnetwork.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.skillbox.socialnetwork.api.response.TagApi;
import ru.skillbox.socialnetwork.model.Tag;

import javax.annotation.PostConstruct;

@Component
public class TagMapper extends Mapper<Tag, TagApi>
{
    @Autowired
    public TagMapper() {
        super(Tag.class, TagApi.class);
    }
}
