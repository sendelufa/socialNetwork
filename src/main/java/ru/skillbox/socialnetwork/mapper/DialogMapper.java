package ru.skillbox.socialnetwork.mapper;

import java.util.Objects;
import javax.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.skillbox.socialnetwork.api.response.DialogApi;
import ru.skillbox.socialnetwork.model.Dialog;

@Component
public class DialogMapper extends Mapper<Dialog, DialogApi> {
   private final ModelMapper modelMapper;

   @Autowired
   public DialogMapper(ModelMapper modelMapper) {
      super(Dialog.class, DialogApi.class);
      this.modelMapper = modelMapper;
   }

   @PostConstruct
   public void setupMapper() {
      modelMapper.createTypeMap(Dialog.class, DialogApi.class)
          .addMappings(m -> m.skip(DialogApi::setMessages))
          .addMappings(m -> m.skip(DialogApi::setUsers))
          .addMappings(m -> m.skip(DialogApi::setUnreadCount))
          .setPostConverter(toApiConverter());
      modelMapper.createTypeMap(DialogApi.class, Dialog.class)
          .addMappings(m -> m.skip(Dialog::setMessages))
          .addMappings(m -> m.skip(Dialog::setPersonList))
          .addMappings(m -> m.skip(Dialog::setUnreadCount))
          .setPostConverter(toEntityConverter());
   }

   @Override
   void mapSpecificFieldsEA(Dialog source, DialogApi destination) {
      if (Objects.isNull(source)) {
         return;
      }
      if (!Objects.isNull(source.getMessages())) {
         destination.setMessages(source.getMessages());
      }

      if (!Objects.isNull(source.getPersonList())) {
         destination.setUsers(source.getPersonList());
      }

      if (!Objects.isNull(source.getUnreadCount())) {
         destination.setUnreadCount(source.getUnreadCount());
      }
   }

   @Override
   void mapSpecificFieldsAE(DialogApi source, Dialog destination) {
      if (Objects.isNull(source)) {
         return;
      }
      if (!Objects.isNull(source.getMessages())) {
         destination.setMessages(source.getMessages());
      }

      if (!Objects.isNull(source.getUsers())) {
         destination.setPersonList(source.getUsers());
      }

      if (!Objects.isNull(source.getUnreadCount())) {
         destination.setUnreadCount(source.getUnreadCount());
      }
   }
}
