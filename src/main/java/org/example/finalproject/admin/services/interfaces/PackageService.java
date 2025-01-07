package org.example.finalproject.admin.services.interfaces;

import org.example.finalproject.admin.dtos.admin.packages.PackageRegistrationDto;
import org.example.finalproject.admin.models.admin.PackageEntity;
import org.example.finalproject.admin.services.base_services.*;

public interface PackageService extends
        FindAll<PackageEntity>,
        FindById<PackageEntity, Long>,
        Addable<PackageRegistrationDto>,
        Removable<Long>,
        Modifiable<PackageRegistrationDto, Long> {

}
