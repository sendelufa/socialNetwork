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
    private final ModelMapper modelMapper;

    @Autowired
    public TagMapper(ModelMapper modelMapper) {
        super(Tag.class, TagApi.class);
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void setupMapper()
    {
        modelMapper.createTypeMap(Tag.class, TagApi.class)
                .addMappings(m -> m.skip(TagApi::setId)).setPostConverter(toApiConverter());
        modelMapper.createTypeMap(TagApi.class, Tag.class)
                .addMappings(m -> m.skip(Tag::setId)).setPostConverter(toEntityConverter());
    }
}
