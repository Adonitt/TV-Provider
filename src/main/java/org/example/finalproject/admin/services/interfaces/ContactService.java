package org.example.finalproject.admin.services.interfaces;

import org.example.finalproject.admin.dtos.admin.admins.AdminDetailsDto;
import org.example.finalproject.admin.dtos.admin.admins.AdminEditingDto;
import org.example.finalproject.admin.dtos.admin.admins.AdminListingDto;
import org.example.finalproject.admin.dtos.admin.admins.AdminRegistrationRequestDto;
import org.example.finalproject.admin.models.Contact;
import org.example.finalproject.admin.services.base_services.*;

public interface ContactService extends
        FindAll<Contact>,
        FindById<Contact, Long>,
        Addable<Contact>,
        Modifiable<Contact, Long>,
        Removable<Long> {

}
