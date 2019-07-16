package ru.skillbox.socialnetwork.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import ru.skillbox.socialnetwork.api.response.FriendshipApi;
import ru.skillbox.socialnetwork.model.Friendship;
import ru.skillbox.socialnetwork.model.FriendshipStatus;
import ru.skillbox.socialnetwork.model.Person;

import javax.annotation.PostConstruct;
import java.util.Objects;

public class FriendshipMapper extends Mapper<Friendship, FriendshipApi> {

    private final ModelMapper modelMapper;

    @Autowired
    public FriendshipMapper(ModelMapper modelMapper) {
        super(Friendship.class, FriendshipApi.class);
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void setupMapper() {
        modelMapper.createTypeMap(Friendship.class, FriendshipApi.class)
                .addMappings(m -> m.skip(FriendshipApi::setSrc_person_id))
                .addMappings(m -> m.skip(FriendshipApi::setDst_person_id))
                .addMappings(m -> m.skip(FriendshipApi::setStatus_id))
                .setPostConverter(toApiConverter());
        modelMapper.createTypeMap(FriendshipApi.class, Friendship.class)
                .addMappings(m -> m.skip(Friendship::setDstPerson))
                .addMappings(m -> m.skip(Friendship::setSrcPerson))
                .addMappings(m -> m.skip(Friendship::setFriendshipStatus))
                .setPostConverter(toEntityConverter());
    }

    @Override
    void mapSpecificFieldsEA(Friendship source, FriendshipApi destination) {
        if (Objects.isNull(source)) {
            return;
        }

        if (!Objects.isNull(source.getDstPerson())) {
            destination.setDst_person_id(source.getDstPerson().getId());
        }

        if (!Objects.isNull(source.getSrcPerson())) {
            destination.setSrc_person_id(source.getSrcPerson().getId());
        }

        if (!Objects.isNull(source.getFriendshipStatus())) {
            destination.setStatus_id(source.getFriendshipStatus().getId());
        }
    }

    @Override
    void mapSpecificFieldsAE(FriendshipApi source, Friendship destination) {
        if (Objects.isNull(source)) {
            return;
        }

        Person dstPerson = new Person();
        dstPerson.setId(source.getDst_person_id());
        destination.setDstPerson(dstPerson);

        Person srcPerson = new Person();
        srcPerson.setId(source.getSrc_person_id());
        destination.setSrcPerson(srcPerson);

        FriendshipStatus status = new FriendshipStatus();
        status.setId(source.getStatus_id());
        destination.setFriendshipStatus(status);
    }
}
