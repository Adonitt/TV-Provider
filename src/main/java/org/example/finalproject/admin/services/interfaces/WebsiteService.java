package org.example.finalproject.admin.services.interfaces;

import org.example.finalproject.admin.dtos.admin.WebsiteDto;
import org.example.finalproject.admin.models.WebsiteEntity;
import org.example.finalproject.admin.services.base_services.*;

public interface WebsiteService extends
        Addable<WebsiteDto>,
        FindById<WebsiteDto, Long>,
        Modifiable<WebsiteDto, Long>{
    WebsiteEntity fetchData();
}
