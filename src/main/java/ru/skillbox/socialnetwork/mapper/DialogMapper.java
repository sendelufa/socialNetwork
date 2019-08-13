package ru.skillbox.socialnetwork.mapper;

import org.springframework.stereotype.Component;

@Component
public class DialogMapper //extends Mapper<Dialog, DialogApi>
{
   /*private final ModelMapper modelMapper;

   @Autowired
   public DialogMapper(ModelMapper modelMapper) {
      super(Dialog.class, DialogApi.class);
      this.modelMapper = modelMapper;
   }

   @PostConstruct
   public void setupMapper() {
      modelMapper.createTypeMap(Dialog.class, DialogApi.class)
          .addMappings(m -> m.skip(DialogApi::setId))
          .addMappings(m -> m.skip(DialogApi::setUnreadCount))
          .addMappings(m -> m.skip(DialogApi::setLastMessage))
          .setPostConverter(toApiConverter());
      modelMapper.createTypeMap(DialogApi.class, Dialog.class)
          .addMappings(m -> m.skip(Dialog::setId))
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
   }*/
}


