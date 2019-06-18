package ru.skillbox.socialnetwork.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.skillbox.socialnetwork.api.response.BlockHistoryApi;
import ru.skillbox.socialnetwork.model.BlockHistory;

import javax.annotation.PostConstruct;

@Component
public class BlockHistoryMapper extends Mapper<BlockHistory, BlockHistoryApi>
{
    private final ModelMapper modelMapper;

    @Autowired
    public BlockHistoryMapper(ModelMapper modelMapper) {
        super(BlockHistory.class, BlockHistoryApi.class);
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void setupMapper()
    {
        modelMapper.createTypeMap(BlockHistory.class, BlockHistoryApi.class)
                .addMappings(m -> m.skip(BlockHistoryApi::setId)).setPostConverter(toApiConverter());
        modelMapper.createTypeMap(BlockHistoryApi.class, BlockHistory.class)
                .addMappings(m -> m.skip(BlockHistory::setId)).setPostConverter(toEntityConverter());
    }
}
