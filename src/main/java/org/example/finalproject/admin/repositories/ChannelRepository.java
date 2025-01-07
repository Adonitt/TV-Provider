package org.example.finalproject.admin.repositories;

import org.example.finalproject.admin.models.admin.ChannelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChannelRepository extends JpaRepository<ChannelEntity, Integer> {
    ChannelEntity findByName(String name);

}
