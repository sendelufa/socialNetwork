package ru.skillbox.socialnetwork.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.skillbox.socialnetwork.api.PersonApi;
import ru.skillbox.socialnetwork.model.Person;

import javax.annotation.PostConstruct;

@Component
public class PersonMapper extends Mapper<Person, PersonApi>
{
    private final ModelMapper modelMapper;

    @Autowired
    public PersonMapper(ModelMapper modelMapper) {
        super(Person.class, PersonApi.class);
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void setupMapper()
    {
        modelMapper.createTypeMap(Person.class, PersonApi.class)
                .addMappings(m -> m.skip(PersonApi::setId)).setPostConverter(toApiConverter());
        modelMapper.createTypeMap(PersonApi.class, Person.class)
                .addMappings(m -> m.skip(Person::setId)).setPostConverter(toEntityConverter());
    }
}
