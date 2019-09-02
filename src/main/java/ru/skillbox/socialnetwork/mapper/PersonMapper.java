package ru.skillbox.socialnetwork.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.skillbox.socialnetwork.api.response.*;
import ru.skillbox.socialnetwork.model.Person;
import ru.skillbox.socialnetwork.model.Post;
import ru.skillbox.socialnetwork.model.PostComment;
import ru.skillbox.socialnetwork.model.Tag;
import ru.skillbox.socialnetwork.model.enumeration.TypePost;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

//TODO: заглушка, чтобы отображались найденные польозователи
@Component
public class PersonMapper extends Mapper<Person, PersonApi> {

   private final ModelMapper modelMapper;

   @Autowired
   private PostCommentMapper postCommentMapper;

   @Autowired
   public PersonMapper(ModelMapper modelMapper) {
      super(Person.class, PersonApi.class);
      this.modelMapper = modelMapper;
   }

   @PostConstruct
   public void setupMapper() {
      modelMapper.createTypeMap(Person.class, PersonApi.class)
          .addMappings(m -> m.skip(PersonApi::setCity))
          .addMappings(m -> m.skip(PersonApi::setCountry))
          .addMappings(m -> m.skip(PersonApi::setPhoto))
          .setPostConverter(toApiConverter());
      modelMapper.createTypeMap(PersonApi.class, Person.class)
          .addMappings(m -> m.skip(Person::setCity))
          .addMappings(m -> m.skip(Person::setCountry))
          .addMappings(m -> m.skip(Person::setCountry))
          .addMappings(m -> m.skip(Person::setPhoto))
          .setPostConverter(toEntityConverter());
   }

   @Override
   void mapSpecificFieldsEA(Person source, PersonApi destination) {
      if (Objects.isNull(source)) {
         return;
      }

      if (!Objects.isNull(source.getCity())) {
         CityApi cityApi = new CityApi();
         cityApi.setId(100);
         cityApi.setTitle(source.getCity());
         destination.setCity(cityApi);
      }

      if (!Objects.isNull(source.getCountry())) {
         CountryApi countryApi = new CountryApi();
         countryApi.setId(100);
         countryApi.setTitle(source.getCountry());
         destination.setCountry(countryApi);
      }

      if(!Objects.isNull(source.getPhoto())) {
         destination.setPhoto(source.getPhoto());
      } else {
         destination.setPhoto("http://res.cloudinary.com/ryker/image/upload/v1567446640/ksqjb1wrhmg5uyid8h04.jpg");
      }
   }

   @Override
   void mapSpecificFieldsAE(PersonApi source, Person destination) {
      if (Objects.isNull(source)) {
         return;
      }
      destination.setCity(source.getCity().getTitle());
      destination.setCountry(source.getCountry().getTitle());
   }
}
