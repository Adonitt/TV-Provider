package org.example.finalproject.admin.services.interfaces;

import org.example.finalproject.admin.dtos.admin.packages.PackageRegistrationDto;
import org.example.finalproject.admin.models.admin.Packages;
import org.example.finalproject.admin.services.base_services.*;

public interface PackageService extends
        FindAll<Packages>,
        FindById<Packages, Long>,
        Addable<PackageRegistrationDto>,
        Removable<Long>,
        Modifiable<PackageRegistrationDto, Long> {

}
