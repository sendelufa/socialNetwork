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
    @Autowired
    public BlockHistoryMapper()
    {
        super(BlockHistory.class, BlockHistoryApi.class);
    }
}
