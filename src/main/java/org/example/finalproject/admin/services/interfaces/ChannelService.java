package org.example.finalproject.admin.services.interfaces;

import org.example.finalproject.admin.dtos.admin.channels.ChannelsDto;
import org.example.finalproject.admin.services.base_services.Addable;
import org.example.finalproject.admin.services.base_services.FindAll;
import org.example.finalproject.admin.services.base_services.Modifiable;
import org.example.finalproject.admin.services.base_services.Removable;

import java.nio.channels.Channels;

public interface ChannelService extends Addable<ChannelsDto>,
        FindAll<ChannelsDto>,
        Modifiable<ChannelsDto, Integer>,
        Removable<Integer> {
}
