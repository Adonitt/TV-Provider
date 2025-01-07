package org.example.finalproject.admin.mappers;

import org.example.finalproject.admin.dtos.admin.channels.ChannelsDto;
import org.example.finalproject.admin.models.admin.ChannelEntity;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Primary;

import java.util.List;

@Primary
@Mapper(componentModel = "spring")
public interface ChannelsMapper extends SimpleMapper<ChannelEntity, ChannelsDto> {
    List<ChannelsDto> toChannelsDtoList(List<ChannelEntity> channels);

}
