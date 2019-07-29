package ru.skillbox.socialnetwork.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.skillbox.socialnetwork.api.response.DialogApi;
import ru.skillbox.socialnetwork.model.Dialog;

import javax.annotation.PostConstruct;
import java.util.Objects;

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
                .addMappings(m -> m.skip(DialogApi::setUnreadСount))
                .setPostConverter(toApiConverter());
        modelMapper.createTypeMap(DialogApi.class, Dialog.class)
                .addMappings(m -> m.skip(Dialog::setMessages))
               // .addMappings(m -> m.skip(Dialog::setUsers))
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

//        if (!Objects.isNull(source.getUsers())) {
//            destination.setUsers(source.getUsers());
//        }

        if (!Objects.isNull(source.getUnreadCount())) {
            destination.setUnreadСount(source.getUnreadCount());
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

//        if (!Objects.isNull(source.getUsers())) {
//            destination.setUsers(source.getUsers());
//        }

        if (!Objects.isNull(source.getUnreadСount())) {
            destination.setUnreadCount(source.getUnreadСount());
        }
    }
}
